package client.map;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Observable;

import shared.definitions.CatanColor;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.models.CatanModel;
import shared.models.Edge;
import shared.models.Game;
import shared.models.HexTile;
import shared.models.Piece;
import shared.models.Port;
import shared.models.TurnTracker;
import shared.models.Vertex;
import client.base.Controller;
import client.communication.ServerProxy;
import client.data.RobPlayerInfo;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController {
	
	private IRobView robView;
	private IState state;
	
	public MapController(IMapView view, IRobView robView) {
		
		super(view);
		
		setRobView(robView);
	}
	
	public IMapView getView() {
		
		return (IMapView)super.getView();
	}
	
	private IRobView getRobView() {
		return robView;
	}
	private void setRobView(IRobView robView) {
		this.robView = robView;
	}
	
	protected void initFromModel() {
		
		if (CatanModel.getInstance().getGameManager() == null) {
			return;
		}
		Game game = CatanModel.getInstance().getGameManager().getGame();
		setStateString(TurnTracker.getInstance().getStatus());
		
		for (int x = -game.getMap().getRadius(); x <= game.getMap().getRadius(); x++) {
			
			int maxY = game.getMap().getRadius() - x;			
			for (int y = -game.getMap().getRadius(); y <= maxY; y++) {			
				HexTile thisTile = game.getMap().getHexTileAt(new HexLocation(x, y));
				if (thisTile != null) {
					int token = thisTile.getToken();
					if(token != 0) {
						getView().addNumber(thisTile.getLocation(), token);
					}
					System.out.println("Step 1");
					HexType hexType = thisTile.getHexType();
					HexLocation hexLoc = thisTile.getLocation();
					getView().addHex(hexLoc, hexType);
					System.out.println("Step 2");
					Iterator<Entry<EdgeDirection, Edge>> itEdge = thisTile.getEdges().entrySet().iterator();
					while(itEdge.hasNext()) {
						Edge thisEdge = itEdge.next().getValue();
						if (thisEdge.getHasRoad()) {
							Piece thisRoad = thisEdge.getRoad();
							getView().placeRoad(thisEdge.getLocation(), game.getPlayers()[thisRoad.getOwnerPlayerIndex()].getColor());
						}
					}
					System.out.println("Step 3");
					Iterator<Entry<VertexDirection, Vertex>> itVertex = thisTile.getVertices().entrySet().iterator();
					while(itVertex.hasNext()) {
						Vertex thisVertex = itVertex.next().getValue();
						if (thisVertex.getHasSettlement()) {
							Piece settlement = thisVertex.getSettlement();
							if(settlement.getType() == PieceType.SETTLEMENT) {
								getView().placeSettlement(thisVertex.getLocation(), game.getPlayers()[settlement.getOwnerPlayerIndex()].getColor());
							}
							else {
								getView().placeCity(thisVertex.getLocation(), game.getPlayers()[settlement.getOwnerPlayerIndex()].getColor());
							}
						}
					}
					System.out.println("Step 4");
				}
			}
		}
		
		Iterator<Entry<EdgeLocation, Port>> itPort = game.getMap().getPorts().entrySet().iterator();
		while(itPort.hasNext()) {
			Entry<EdgeLocation, Port> thisEntry = itPort.next();
			Port thisPort = thisEntry.getValue();
			EdgeLocation thisLoc = thisEntry.getKey();
			getView().addPort(thisLoc, thisPort.getType());
		}
		System.out.println("Step 5");
		
		getView().placeRobber(game.getMap().getRobberLocation());
		System.out.println("Step 6");
		
		//</temp>
	}

	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		int playerId = ServerProxy.getInstance().getlocalPlayer().getId();
		Game game = CatanModel.getInstance().getGameManager().getGame();
		if(game.getMap().canBuildRoadAt(game.getPlayerIndexByPlayerId(playerId), edgeLoc)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		int playerId = ServerProxy.getInstance().getlocalPlayer().getId();
		Game game = CatanModel.getInstance().getGameManager().getGame();
		if(game.getMap().canBuildSettlementAt(game.getPlayerIndexByPlayerId(playerId), vertLoc)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {
		int playerId = ServerProxy.getInstance().getlocalPlayer().getId();
		Game game = CatanModel.getInstance().getGameManager().getGame();
		if(game.getMap().canUpgradeSettlementAt(game.getPlayerIndexByPlayerId(playerId), vertLoc)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean canPlaceRobber(HexLocation hexLoc) {
		Game game = CatanModel.getInstance().getGameManager().getGame();
		if(game.getMap().canMoveRobber(hexLoc)) {
			return true;
		}
		else {
			return false;
		}
	}

	public void placeRoad(EdgeLocation edgeLoc) {
		Game game = CatanModel.getInstance().getGameManager().getGame();
		int playerId = ServerProxy.getInstance().getlocalPlayer().getId();
		CatanColor thisColor = game.getPlayers()[game.getPlayerIndexByPlayerId(playerId)].getColor();
		getView().placeRoad(edgeLoc, thisColor);
	}

	public void placeSettlement(VertexLocation vertLoc) {
		Game game = CatanModel.getInstance().getGameManager().getGame();
		int playerId = ServerProxy.getInstance().getlocalPlayer().getId();
		CatanColor thisColor = game.getPlayers()[game.getPlayerIndexByPlayerId(playerId)].getColor();
		getView().placeSettlement(vertLoc, thisColor);
	}

	public void placeCity(VertexLocation vertLoc) {
		Game game = CatanModel.getInstance().getGameManager().getGame();
		int playerId = ServerProxy.getInstance().getlocalPlayer().getId();
		CatanColor thisColor = game.getPlayers()[game.getPlayerIndexByPlayerId(playerId)].getColor();
		getView().placeCity(vertLoc, thisColor);
	}

	public void placeRobber(HexLocation hexLoc) {
		
		getView().placeRobber(hexLoc);
		
		getRobView().showModal();
	}
	
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	
		Game game = CatanModel.getInstance().getGameManager().getGame();
		int playerId = ServerProxy.getInstance().getlocalPlayer().getId();
		CatanColor thisColor = game.getPlayers()[game.getPlayerIndexByPlayerId(playerId)].getColor();
		getView().startDrop(pieceType, thisColor, true);
	}
	
	public void cancelMove() {
		
	}
	
	public void playSoldierCard() {	
		
	}
	
	public void playRoadBuildingCard() {	
		
	}
	
	public void robPlayer(RobPlayerInfo victim) {	
		
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Updated");
		initFromModel();
		
	}

	public IState getState() {
		return state;
	}

	public void setState(IState state) {
		this.state = state;
	}
	
	public void setStateString(String status) {
		switch (status) {
		case "Rolling":	state = RollingState.singleton;
			break;
		case "Robbing":	state = RobbingState.singleton;
			break;
		case "Playing":	state = PlayingState.singleton;
			break;
		case "Discarding":	state = DiscardingState.singleton;
			break;
		case "FirstRound":	state = Setup1State.singleton;
			break;
		case "SecondRound":	state = Setup2State.singleton;
			break;
		default:	state = Setup1State.singleton;
			break;
		
		}
	}
	
}

