package client.map;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Observable;

import shared.definitions.CatanColor;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.PortType;
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
import shared.models.Vertex;
import client.base.Controller;
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
		
		initFromModel();
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
		
		Game game = CatanModel.getInstance().getGameManager().getGame();
		
		for (int x = 0; x <= game.getMap().getRadius(); ++x) {
			
			int maxY = 3 - x;			
			for (int y = -3; y <= maxY; ++y) {			
				HexTile thisTile = game.getMap().getHexTiles()[x][y];
				int token = thisTile.getToken();
				getView().addNumber(thisTile.getLocation(), token);
				HexType hexType = thisTile.getHexType();
				HexLocation hexLoc = thisTile.getLocation();
				getView().addHex(hexLoc, hexType);
				Iterator<Entry<EdgeDirection, Edge>> itEdge = thisTile.getEdges().entrySet().iterator();
				while(itEdge.hasNext()) {
					Edge thisEdge = itEdge.next().getValue();
					if (thisEdge.getHasRoad()) {
						Piece thisRoad = thisEdge.getRoad();
						getView().placeRoad(thisEdge.getLocation(), game.getPlayers()[thisRoad.getOwnerPlayerIndex()].getColor());
					}
				}
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
			}
		}
		
		Iterator<Entry<EdgeLocation, Port>> itPort = game.getMap().getPorts().entrySet().iterator();
		while(itPort.hasNext()) {
			Entry<EdgeLocation, Port> thisEntry = itPort.next();
			Port thisPort = thisEntry.getValue();
			EdgeLocation thisLoc = thisEntry.getKey();
			getView().addPort(thisLoc, thisPort.getType());
		}
		
		getView().placeRobber(game.getMap().getRobberLocation());
		
		//</temp>
	}

	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		return true;
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		
		return true;
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {
		
		return true;
	}

	public boolean canPlaceRobber(HexLocation hexLoc) {
		
		return true;
	}

	public void placeRoad(EdgeLocation edgeLoc) {
		
		getView().placeRoad(edgeLoc, CatanColor.ORANGE);
	}

	public void placeSettlement(VertexLocation vertLoc) {
		
		getView().placeSettlement(vertLoc, CatanColor.ORANGE);
	}

	public void placeCity(VertexLocation vertLoc) {
		
		getView().placeCity(vertLoc, CatanColor.ORANGE);
	}

	public void placeRobber(HexLocation hexLoc) {
		
		getView().placeRobber(hexLoc);
		
		getRobView().showModal();
	}
	
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	
		
		getView().startDrop(pieceType, CatanColor.ORANGE, true);
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
		// TODO Auto-generated method stub
		
	}

	public IState getState() {
		return state;
	}

	public void setState(IState state) {
		this.state = state;
	}
	
}

