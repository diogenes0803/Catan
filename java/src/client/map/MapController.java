package client.map;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Observable;

import shared.communicator.BuildRoadParams;
import shared.communicator.BuildSettlementParams;
import shared.communicator.DiscardCardsParams;
import shared.communicator.FinishTurnParams;
import shared.communicator.RobPlayerParams;
import shared.communicator.RollNumberParams;
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
import shared.models.ResourceList;
import shared.models.TurnTracker;
import shared.models.Vertex;
import client.base.Controller;
import client.communication.ServerProxy;
import client.data.PlayerInfo;
import client.data.RobPlayerInfo;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController {
	
	private IRobView robView;
	public static IState state;
	private boolean setup1Initiated;
	private boolean setup1Finished;
	private boolean setup2Initiated;
	private boolean setup2Finished;
	private boolean robbingInitiated;
	private HexLocation robberLocation;
	
	public MapController(IMapView view, IRobView robView) {
		
		super(view);
		
		setRobView(robView);
		
		setup1Initiated = false;
		setup1Finished = false;
		setup2Initiated = false;
		setup2Finished = false;
		robbingInitiated = false;
		state = null;
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
	
	
	private void createRoads(Game game)
	{
		for (int x = -game.getMap().getRadius(); x <= game.getMap().getRadius(); x++) 
		{
			int maxY = game.getMap().getRadius() - x;			
			for (int y = -game.getMap().getRadius(); y <= maxY; y++) 
			{			
				HexTile thisTile = game.getMap().getHexTileAt(new HexLocation(x, y));
				if (thisTile != null) 
				{
					Iterator<Entry<EdgeDirection, Edge>> itEdge = thisTile.getEdges().entrySet().iterator();
					while(itEdge.hasNext()) 
					{
						Edge thisEdge = itEdge.next().getValue();
						if (thisEdge.getHasRoad()) 
						{
							Piece thisRoad = thisEdge.getRoad();
							getView().placeRoad(thisEdge.getLocation(), game.getPlayers()[thisRoad.getOwnerPlayerIndex()].getColor());
						}
					}
				}
			}
		}
	}

	private void createCities(Game game)
	{
		for (int x = -game.getMap().getRadius(); x <= game.getMap().getRadius(); x++) 
		{
			int maxY = game.getMap().getRadius() - x;			
			for (int y = -game.getMap().getRadius(); y <= maxY; y++) 
			{			
				HexTile thisTile = game.getMap().getHexTileAt(new HexLocation(x, y));
				if (thisTile != null) 
				{
					Iterator<Entry<VertexDirection, Vertex>> itVertex = thisTile.getVertices().entrySet().iterator();
					while(itVertex.hasNext()) 
					{
						Vertex thisVertex = itVertex.next().getValue();
						if (thisVertex.getHasSettlement()) 
						{
							Piece settlement = thisVertex.getSettlement();
							getView().placeCity(thisVertex.getLocation(), game.getPlayers()[settlement.getOwnerPlayerIndex()].getColor());
						}
					}
				}
			}
		}
	}

	private void createSettlements(Game game)
	{
		for (int x = -game.getMap().getRadius(); x <= game.getMap().getRadius(); x++) 
		{
			int maxY = game.getMap().getRadius() - x;			
			for (int y = -game.getMap().getRadius(); y <= maxY; y++) 
			{			
				HexTile thisTile = game.getMap().getHexTileAt(new HexLocation(x, y));
				if (thisTile != null) 
				{
					Iterator<Entry<VertexDirection, Vertex>> itVertex = thisTile.getVertices().entrySet().iterator();
					while(itVertex.hasNext()) 
					{
						Vertex thisVertex = itVertex.next().getValue();
						if (thisVertex.getHasSettlement()) 
						{
							Piece settlement = thisVertex.getSettlement();
							if(settlement.getType() == PieceType.SETTLEMENT)
							getView().placeSettlement(thisVertex.getLocation(), game.getPlayers()[settlement.getOwnerPlayerIndex()].getColor());
						}
					}
				}
			}
		}
	}
	
	private void createTiles(Game game){
		
		for (int x = -game.getMap().getRadius(); x <= game.getMap().getRadius()+2; x++) {
			int maxY = game.getMap().getRadius() - x + 2;
			for (int y = -game.getMap().getRadius(); y <= maxY; y++) {		
				HexTile thisTile = game.getMap().getHexTileAt(new HexLocation(x, y));
				if (thisTile != null) {
					HexType hexType = thisTile.getHexType();
					HexLocation hexLoc = thisTile.getLocation();
					getView().addHex(hexLoc, hexType);
				}
			}
		}
	}
		
	private void createTokens(Game game)
	{
		for (int x = -game.getMap().getRadius(); x <= game.getMap().getRadius(); x++) 
		{
					
			int maxY = game.getMap().getRadius() - x;			
			for (int y = -game.getMap().getRadius(); y <= maxY; y++) {			
				HexTile thisTile = game.getMap().getHexTileAt(new HexLocation(x, y));
				if (thisTile != null) {
					int token = thisTile.getToken();
					if(token != 0) {
						getView().addNumber(thisTile.getLocation(), token);
					}
				}
			}
		}
	}

	private void createHarbors(Game game)
	{
		Iterator<Entry<EdgeLocation, Port>> itPort = game.getMap().getPorts().entrySet().iterator();
		while(itPort.hasNext()) {
			Entry<EdgeLocation, Port> thisEntry = itPort.next();
			Port thisPort = thisEntry.getValue();
			EdgeLocation thisLoc = thisEntry.getKey();
			getView().addPort(thisLoc, thisPort.getType());
		}
	}

	private void createRobber(Game game)
	{
		getView().placeRobber(game.getMap().getRobberLocation());
	}

	private void initFromModel() {
		if (CatanModel.getInstance().getGameManager() == null) {
			return;
		}
		Game game = CatanModel.getInstance().getGameManager().getGame();
		//System.out.println(state.toString());

		if (state == null || !state.toString().equals(TurnTracker.getInstance().getStatus())) {
			setStateString(TurnTracker.getInstance().getStatus());
			CatanModel.getInstance().getGameManager().changed();
			CatanModel.getInstance().getGameManager().notifyObservers(game);
		}
		
		createTiles(game);
		createTokens(game);
		createCities(game);
		createSettlements(game);
		createRoads(game);
		createHarbors(game);
		createRobber(game);
		
		if (!setup1Initiated) {
			startGame();
		}
		
		if (setup1Finished && !setup2Initiated) {
			startSetup2();
		}
		
		if (state.equals(RobbingState.singleton) && !robbingInitiated) {
			PlayerInfo playerInfo = ServerProxy.getInstance().getlocalPlayer();
			robbingInitiated = true;
			getView().startDrop(PieceType.ROBBER, playerInfo.getColor(), false);
		}
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
		int playerIndex = ServerProxy.getInstance().getlocalPlayer().getPlayerIndex();
		CatanColor thisColor = game.getPlayers()[game.getPlayerIndexByPlayerId(playerId)].getColor();
		getView().placeRoad(edgeLoc, thisColor);
		
		BuildRoadParams params = new BuildRoadParams(playerIndex, edgeLoc, true);
		state.buildRoad(this, params);
	}

	public void placeSettlement(VertexLocation vertLoc) {
		Game game = CatanModel.getInstance().getGameManager().getGame();
		int playerId = ServerProxy.getInstance().getlocalPlayer().getId();
		int playerIndex = ServerProxy.getInstance().getlocalPlayer().getPlayerIndex();
		CatanColor thisColor = game.getPlayers()[game.getPlayerIndexByPlayerId(playerId)].getColor();
		getView().placeSettlement(vertLoc, thisColor);
		
		BuildSettlementParams params = new BuildSettlementParams(playerIndex, vertLoc, true);
		state.buildSettlement(this, params);
	}

	public void placeCity(VertexLocation vertLoc) {
		Game game = CatanModel.getInstance().getGameManager().getGame();
		int playerId = ServerProxy.getInstance().getlocalPlayer().getId();
		CatanColor thisColor = game.getPlayers()[game.getPlayerIndexByPlayerId(playerId)].getColor();
		getView().placeCity(vertLoc, thisColor);
	}

	public void placeRobber(HexLocation hexLoc) {
		
		getView().placeRobber(hexLoc);
		robberLocation = hexLoc;
		
		RobPlayerInfo[] candidateVictims = CatanModel.getInstance().getGameManager().getGame().getRobberVictims(hexLoc);
		System.out.println(candidateVictims.length);
		getRobView().setPlayers(candidateVictims);
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
		int playerIndex = ServerProxy.getInstance().getlocalPlayer().getPlayerIndex();
		int victimIndex = victim.getPlayerIndex();

		RobPlayerParams params = new RobPlayerParams(playerIndex, victimIndex, robberLocation);
		state.robPlayer(this, params);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Game) {
			int playerID = ServerProxy.getInstance().getlocalPlayer().getId();
			int playerIndex = CatanModel.getInstance().getGameManager().getGame().getPlayerIndexByPlayerId(playerID);
			ServerProxy.getInstance().getlocalPlayer().setPlayerIndex(playerIndex);
			initFromModel();
		}
		
	}

	public static IState getState() {
		return state;
	}

	public static void setState(IState state) {
		MapController.state = state;
	}
	
	public static void rollNumber(int number) {
		
		int playerIndex = ServerProxy.getInstance().getlocalPlayer().getPlayerIndex();
		
		RollNumberParams params = new RollNumberParams(playerIndex, number);
		state.rollNumber(null, params);
	}
	
	public static void discardCards(int playerIndex, ResourceList resourceList) {
		DiscardCardsParams params = new DiscardCardsParams(playerIndex, resourceList);
		
		state.discardCards(null, params);
	}
	
	public static void finishTurn(int playerIndex) {
		FinishTurnParams params = new FinishTurnParams(playerIndex);
		
		state.finishTurn(null, params);
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
	
	public void startGame() {
		int currentTurn = TurnTracker.getInstance().getCurrentTurn();
		
		if (ServerProxy.getInstance().getlocalPlayer().getPlayerIndex() != currentTurn) {
			return;
		}
		else if (state.equals(Setup1State.singleton)) {
			PlayerInfo playerInfo = ServerProxy.getInstance().getlocalPlayer();
			
			setup1Initiated = true;
			this.getView().startDrop(PieceType.ROAD,playerInfo.getColor() , false);
		}
	}
	
	public void startSetup2() {
		int currentTurn = TurnTracker.getInstance().getCurrentTurn();
		
		if (ServerProxy.getInstance().getlocalPlayer().getPlayerIndex() != currentTurn) {
			return;
		}
		else if (state.equals(Setup2State.singleton)) {
			PlayerInfo playerInfo = ServerProxy.getInstance().getlocalPlayer();
			
			setup2Initiated = true;
			this.getView().startDrop(PieceType.ROAD, playerInfo.getColor(), false);
		}
	}

	public boolean isSetup1Finished() {
		return setup1Finished;
	}

	public void setSetup1Finished(boolean setup1Finished) {
		this.setup1Finished = setup1Finished;
	}

	public boolean isSetup2Finished() {
		return setup2Finished;
	}

	public void setSetup2Finished(boolean setup2Finished) {
		this.setup2Finished = setup2Finished;
	}
}

