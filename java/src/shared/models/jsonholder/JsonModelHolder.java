package shared.models.jsonholder;

import java.util.List;

import shared.definitions.PieceType;
import shared.locations.EdgeDirection;
import shared.locations.VertexDirection;
import shared.models.Game;
import shared.models.HexTile;
import shared.models.Map;
import shared.models.Piece;

public class JsonModelHolder {
	private Deck deck;
	private JsonMap map;
	private List<Player> players;
	private Log log;
	private Chat chat;
	private Bank bank;
	private TurnTracker turnTracker;
	private int winner;
	private int version;
	
	public Game buildCatanModel() {
		Game game = new Game();
	    
		game.setWinner(winner);
		game.setVersion(version);
		
		
		
		return null;
	}
	
	
	
	private Map createMap() {
		Map mapModel = new Map();
		HexTile[][] hexArray = new HexTile[5][5];
		for(Hex thisHex : map.getHexes()) {
			HexTile thisHextile = new HexTile(thisHex.getLocation().getX(), thisHex.getLocation().getY(), thisHex.getResource(), thisHex.getNumber());
			hexArray[thisHextile.getLocation().getX()-2][thisHextile.getLocation().getY()-2] = thisHextile;
		}
		for(Road thisRoad : map.getRoads()) {
			int x = thisRoad.getLocation().getX();
			int y = thisRoad.getLocation().getY();
			EdgeDirection thisDirection = stringToEdgeDirection(thisRoad.getLocation().getDirection());
			Piece road = new Piece(PieceType.ROAD, thisRoad.getOwner());
			hexArray[x+2][y+2].getEdgeAt(thisDirection).setRoad(road);
		}
		for(City thisCity : map.getCities()) {
			int x = thisCity.getLocation().getX();
			int y = thisCity.getLocation().getY();
			VertexDirection thisDirection = stringToVertexDirection(thisCity.getLocation().getDirection());
			Piece city = new Piece(PieceType.CITY, thisCity.getOwner());
			hexArray[x+2][y+2].getVertexAt(thisDirection).setSettlement(city);
		}
		for(Settlement thisSettlement : map.getSettlements()) {
			int x = thisSettlement.getLocation().getX();
			int y = thisSettlement.getLocation().getY();
			VertexDirection thisDirection = stringToVertexDirection(thisSettlement.getLocation().getDirection());
			Piece city = new Piece(PieceType.CITY, thisSettlement.getOwner());
			hexArray[x+2][y+2].getVertexAt(thisDirection).setSettlement(city);
		}
		for()
		return mapModel;
	}
	
	private EdgeDirection stringToEdgeDirection(String direction) {
		EdgeDirection edgeDirection = null;
		switch(direction) {
			case"N":
				edgeDirection = EdgeDirection.North;
				break;
			case"NE":
				edgeDirection = EdgeDirection.NorthEast;
				break;
			case"NW":
				edgeDirection = EdgeDirection.NorthWest;
				break;
			case"S":
				edgeDirection = EdgeDirection.South;
				break;
			case"SE":
				edgeDirection = EdgeDirection.SouthEast;
				break;
			case"SW":
				edgeDirection = EdgeDirection.SouthWest;
				break;
		}
		return edgeDirection;
	}
	
	private VertexDirection stringToVertexDirection(String direction) {
		VertexDirection vertexDirection = null;
		switch(direction) {
			case"E":
				vertexDirection = VertexDirection.East;
				break;
			case"NE":
				vertexDirection = VertexDirection.NorthEast;
				break;
			case"NW":
				vertexDirection = VertexDirection.NorthWest;
				break;
			case"SE":
				vertexDirection = VertexDirection.SouthEast;
				break;
			case"SW":
				vertexDirection = VertexDirection.SouthWest;
				break;
			case"W":
				vertexDirection = VertexDirection.West;
				break;
		}
		return vertexDirection;
	}
	public JsonMap getMap() {
		return map;
	}
	public void setMap(JsonMap map) {
		this.map = map;
	}
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public Log getLog() {
		return log;
	}
	public void setLog(Log log) {
		this.log = log;
	}
	public Chat getChat() {
		return chat;
	}
	public void setChat(Chat chat) {
		this.chat = chat;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public TurnTracker getTurnTracker() {
		return turnTracker;
	}
	public void setTurnTracker(TurnTracker turnTracker) {
		this.turnTracker = turnTracker;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
}
