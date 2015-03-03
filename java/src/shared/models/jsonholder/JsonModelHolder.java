package shared.models.jsonholder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.models.Bank;
import shared.models.CatanMap;
import shared.models.DevCard;
import shared.models.Game;
import shared.models.HexTile;
import shared.models.MessageLine;
import shared.models.Piece;
import shared.models.Player;
import shared.models.Port;
import shared.models.ResCard;
import shared.models.TradeOffer;
import shared.models.TurnTracker;

public class JsonModelHolder {
	private Deck deck;
	private JsonMap map;
	private List<JsonPlayer> players;
	private Log log;
	private Chat chat;
	private JsonBank bank;
	private JsonTurnTracker turnTracker;
	private int winner;
	private int version;
	private JsonTradeOffer tradeOffer;

	public Game buildCatanGame() {
		Game game = new Game();

		game.setWinner(winner);

		game.setMap(createMap());
		game.setPlayers(createPlayerList());
		game.setBank(new Bank(createBankResCards(), createBankDevCards()));
		game.setLogs(createLog());
		game.setChats(createChat());
		if (tradeOffer != null) {
			game.setTradeOffer(new TradeOffer(tradeOffer.getSender(),
					tradeOffer.getReceiver(), tradeOffer.getOffer()));
		}
		game.setVersion(version);
		TurnTracker.getInstance().setStatus(turnTracker.getStatus());
		TurnTracker.getInstance().setCurrentTurn(turnTracker.getCurrentTurn());
		TurnTracker.getInstance().setLongestRoad(turnTracker.getLongestRoad());
		TurnTracker.getInstance().setLargestArmy(turnTracker.getLargestArmy());
		return game;
	}

	private CatanMap createMap() {
		CatanMap mapModel = new CatanMap();
		int radius = map.getRadius();
		mapModel.setRadius(radius);
		HexTile[][] hexArray = new HexTile[radius * 2 - 1][radius * 2 - 1];
		HashMap<EdgeLocation, Port> ports = new HashMap<EdgeLocation, Port>();
		for (Hex thisHex : map.getHexes()) {
			int x = thisHex.getLocation().getX();
			int y = thisHex.getLocation().getY();
			HexType type = null;
			if (thisHex.getResource() != null) {
				type = stringToHexType(thisHex.getResource());
			} else {
				type = HexType.DESERT;
			}
			int number = thisHex.getNumber();
			HexTile thisHextile = new HexTile(x, y, type, number);
			hexArray[thisHextile.getLocation().getX() + radius - 1][thisHextile
					.getLocation().getY() + radius - 1] = thisHextile;
		}
		for (Road thisRoad : map.getRoads()) {
			int x = thisRoad.getLocation().getX();
			int y = thisRoad.getLocation().getY();
			EdgeDirection thisDirection = stringToEdgeDirection(thisRoad
					.getLocation().getDirection());
			Piece road = new Piece(PieceType.ROAD, thisRoad.getOwner());
			hexArray[x + radius - 1][y + radius - 1].getEdgeAt(thisDirection)
					.setRoad(road);
			hexArray[x + radius - 1][y + radius - 1].getEdgeAt(thisDirection)
					.setHasRoad(true);
		}
		for (City thisCity : map.getCities()) {
			int x = thisCity.getLocation().getX();
			int y = thisCity.getLocation().getY();
			VertexDirection thisDirection = stringToVertexDirection(thisCity
					.getLocation().getDirection());
			Piece city = new Piece(PieceType.CITY, thisCity.getOwner());
			hexArray[x + radius - 1][y + radius - 1].getVertexAt(thisDirection)
					.setSettlement(city);
			hexArray[x + radius - 1][y + radius - 1].getVertexAt(thisDirection)
					.setHasSettlement(true);
		}
		for (Settlement thisSettlement : map.getSettlements()) {
			int x = thisSettlement.getLocation().getX();
			int y = thisSettlement.getLocation().getY();
			VertexDirection thisDirection = stringToVertexDirection(thisSettlement
					.getLocation().getDirection());
			Piece city = new Piece(PieceType.SETTLEMENT,
					thisSettlement.getOwner());
			hexArray[x + radius - 1][y + radius - 1].getVertexAt(thisDirection)
					.setSettlement(city);
			hexArray[x + radius - 1][y + radius - 1].getVertexAt(thisDirection)
					.setHasSettlement(true);
		}
		for (JsonPort thisPort : map.getPorts()) {
			int x = thisPort.getLocation().getX();
			int y = thisPort.getLocation().getY();
			PortType type = null;
			if (thisPort.getResource() != null) {
				type = stringToPortType(thisPort.getResource());
			}
			else {
				type = PortType.THREE;
			}
			EdgeDirection thisDirection = stringToEdgeDirection(thisPort
					.getDirection());
			HexLocation hexLoc = new HexLocation(x, y);
			EdgeLocation thisLocation = new EdgeLocation(hexLoc, thisDirection);
			Port port = new Port(thisPort.getRatio(), type, thisLocation);
			ports.put(thisLocation, port);
		}
		mapModel.setPorts(ports);
		mapModel.setHexTiles(hexArray);
		Robber thisRobber = map.getRobber();
		int x = thisRobber.getX();
		int y = thisRobber.getY();
		HexLocation hexLoc = new HexLocation(x, y);
		Piece robber = new Piece(PieceType.ROBBER, -1);
		mapModel.setRobberLocation(hexLoc);
		hexArray[x + radius - 1][y + radius - 1].setRobber(robber);

		return mapModel;
	}

	private Player[] createPlayerList() {
		Player[] players = new Player[4];
		for (JsonPlayer thisPlayer : this.players) {
			Player player = new Player();
			player.setColor(CatanColor.getCatanColor(thisPlayer.getColor()));
			player.setNumMonumentPlayed(thisPlayer.getMonuments());
			player.setNumSoldierPlayed(thisPlayer.getSoldiers());
			player.setPlayerId(thisPlayer.getPlayerID());
			player.setVictoryPoint(thisPlayer.getVictoryPoints());
			player.setPlayedDevCard(thisPlayer.isPlayedDevCard());
			player.setDiscarded(thisPlayer.isDiscarded());
			player.setName(thisPlayer.getName());
			List<ResCard> resCards = new ArrayList<ResCard>();
			List<DevCard> devCards = new ArrayList<DevCard>();
			List<Piece> availablePieces = new ArrayList<Piece>();
			for (int i = 0; i < thisPlayer.getResources().getBrick(); i++) {
				ResCard thisCard = new ResCard(ResourceType.BRICK);
				resCards.add(thisCard);
			}
			for (int i = 0; i < thisPlayer.getResources().getOre(); i++) {
				ResCard thisCard = new ResCard(ResourceType.ORE);
				resCards.add(thisCard);
			}
			for (int i = 0; i < thisPlayer.getResources().getSheep(); i++) {
				ResCard thisCard = new ResCard(ResourceType.SHEEP);
				resCards.add(thisCard);
			}
			for (int i = 0; i < thisPlayer.getResources().getWheat(); i++) {
				ResCard thisCard = new ResCard(ResourceType.WHEAT);
				resCards.add(thisCard);
			}
			for (int i = 0; i < thisPlayer.getResources().getWood(); i++) {
				ResCard thisCard = new ResCard(ResourceType.WOOD);
				resCards.add(thisCard);
			}
			for (int i = 0; i < thisPlayer.getNewDevCards().getMonopoly(); i++) {
				DevCard thisCard = new DevCard(DevCardType.MONOPOLY, false);
				devCards.add(thisCard);
			}
			for (int i = 0; i < thisPlayer.getNewDevCards().getMonument(); i++) {
				DevCard thisCard = new DevCard(DevCardType.MONUMENT, false);
				devCards.add(thisCard);
			}
			for (int i = 0; i < thisPlayer.getNewDevCards().getRoadBuilding(); i++) {
				DevCard thisCard = new DevCard(DevCardType.ROAD_BUILD, false);
				devCards.add(thisCard);
			}
			for (int i = 0; i < thisPlayer.getNewDevCards().getSoldier(); i++) {
				DevCard thisCard = new DevCard(DevCardType.SOLDIER, false);
				devCards.add(thisCard);
			}
			for (int i = 0; i < thisPlayer.getNewDevCards().getYearOfPlenty(); i++) {
				DevCard thisCard = new DevCard(DevCardType.YEAR_OF_PLENTY,
						false);
				devCards.add(thisCard);
			}
			for (int i = 0; i < thisPlayer.getOldDevCards().getMonopoly(); i++) {
				DevCard thisCard = new DevCard(DevCardType.MONOPOLY, true);
				devCards.add(thisCard);
			}
			for (int i = 0; i < thisPlayer.getOldDevCards().getMonument(); i++) {
				DevCard thisCard = new DevCard(DevCardType.MONUMENT, true);
				devCards.add(thisCard);
			}
			for (int i = 0; i < thisPlayer.getOldDevCards().getRoadBuilding(); i++) {
				DevCard thisCard = new DevCard(DevCardType.ROAD_BUILD, true);
				devCards.add(thisCard);
			}
			for (int i = 0; i < thisPlayer.getOldDevCards().getSoldier(); i++) {
				DevCard thisCard = new DevCard(DevCardType.SOLDIER, true);
				devCards.add(thisCard);
			}
			for (int i = 0; i < thisPlayer.getOldDevCards().getYearOfPlenty(); i++) {
				DevCard thisCard = new DevCard(DevCardType.YEAR_OF_PLENTY, true);
				devCards.add(thisCard);
			}
			for (int i = 0; i < thisPlayer.getRoads(); i++) {
				Piece thisRoad = new Piece(PieceType.ROAD,
						thisPlayer.getPlayerID());
				availablePieces.add(thisRoad);
			}
			for (int i = 0; i < thisPlayer.getCities(); i++) {
				Piece thisCity = new Piece(PieceType.CITY,
						thisPlayer.getPlayerID());
				availablePieces.add(thisCity);
			}
			for (int i = 0; i < thisPlayer.getSettlements(); i++) {
				Piece thisSettlement = new Piece(PieceType.SETTLEMENT,
						thisPlayer.getPlayerID());
				availablePieces.add(thisSettlement);
			}
			player.setDevCards(devCards);
			player.setResCards(resCards);
			player.setAvailablePieces(availablePieces);
			players[thisPlayer.getPlayerIndex()] = player;
		}
		return players;
	}

	private List<DevCard> createBankDevCards() {
		List<DevCard> devCards = new ArrayList<DevCard>();
		for (int i = 0; i < deck.getMonopoly(); i++) {
			DevCard thisCard = new DevCard(DevCardType.MONOPOLY, false);
			devCards.add(thisCard);
		}
		for (int i = 0; i < deck.getMonument(); i++) {
			DevCard thisCard = new DevCard(DevCardType.MONUMENT, false);
			devCards.add(thisCard);
		}
		for (int i = 0; i < deck.getRoadBuilding(); i++) {
			DevCard thisCard = new DevCard(DevCardType.ROAD_BUILD, false);
			devCards.add(thisCard);
		}
		for (int i = 0; i < deck.getSoldier(); i++) {
			DevCard thisCard = new DevCard(DevCardType.SOLDIER, false);
			devCards.add(thisCard);
		}
		for (int i = 0; i < deck.getYearOfPlenty(); i++) {
			DevCard thisCard = new DevCard(DevCardType.YEAR_OF_PLENTY, false);
			devCards.add(thisCard);
		}
		return devCards;
	}

	private List<ResCard> createBankResCards() {
		List<ResCard> resCards = new ArrayList<ResCard>();
		for (int i = 0; i < bank.getBrick(); i++) {
			ResCard thisCard = new ResCard(ResourceType.BRICK);
			resCards.add(thisCard);
		}
		for (int i = 0; i < bank.getOre(); i++) {
			ResCard thisCard = new ResCard(ResourceType.ORE);
			resCards.add(thisCard);
		}
		for (int i = 0; i < bank.getSheep(); i++) {
			ResCard thisCard = new ResCard(ResourceType.SHEEP);
			resCards.add(thisCard);
		}
		for (int i = 0; i < bank.getWheat(); i++) {
			ResCard thisCard = new ResCard(ResourceType.WHEAT);
			resCards.add(thisCard);
		}
		for (int i = 0; i < bank.getWood(); i++) {
			ResCard thisCard = new ResCard(ResourceType.WOOD);
			resCards.add(thisCard);
		}
		return resCards;
	}

	private List<MessageLine> createLog() {
		List<MessageLine> catanLog = new ArrayList<MessageLine>();
		if (log.getLines() != null) {
			for (Line thisLine : log.getLines()) {
				MessageLine messageLine = new MessageLine(thisLine.getSource(),
						thisLine.getMessage());
				catanLog.add(messageLine);
			}
		}
		return catanLog;
	}

	private List<MessageLine> createChat() {
		List<Line> lines = this.chat.getLines();
		List<MessageLine> chatReturn = new ArrayList<MessageLine>();
		if (lines != null) {
			for (Line thisLine : lines) {
				System.out.println("Should be chats here after something is sent");
				MessageLine messageLine = new MessageLine(thisLine.getSource(),
						thisLine.getMessage());
				chatReturn.add(messageLine);
			}
		}
		return chatReturn;
	}

	public EdgeDirection stringToEdgeDirection(String direction) {
		EdgeDirection edgeDirection = null;
		switch (direction) {
		case "N":
			edgeDirection = EdgeDirection.North;
			break;
		case "NE":
			edgeDirection = EdgeDirection.NorthEast;
			break;
		case "NW":
			edgeDirection = EdgeDirection.NorthWest;
			break;
		case "S":
			edgeDirection = EdgeDirection.South;
			break;
		case "SE":
			edgeDirection = EdgeDirection.SouthEast;
			break;
		case "SW":
			edgeDirection = EdgeDirection.SouthWest;
			break;
		default:
			break;
		}
		return edgeDirection;
	}

	public VertexDirection stringToVertexDirection(String direction) {
		VertexDirection vertexDirection = null;
		switch (direction) {
		case "E":
			vertexDirection = VertexDirection.East;
			break;
		case "NE":
			vertexDirection = VertexDirection.NorthEast;
			break;
		case "NW":
			vertexDirection = VertexDirection.NorthWest;
			break;
		case "SE":
			vertexDirection = VertexDirection.SouthEast;
			break;
		case "SW":
			vertexDirection = VertexDirection.SouthWest;
			break;
		case "W":
			vertexDirection = VertexDirection.West;
			break;
		default:
			break;
		}
		return vertexDirection;
	}

//	public ResourceType stringToResource(String resource) {
//		ResourceType resourceType = null;
//		switch (resource) {
//		case "brick":
//			resourceType = ResourceType.BRICK;
//			break;
//		case "ore":
//			resourceType = ResourceType.ORE;
//			break;
//		case "sheep":
//			resourceType = ResourceType.SHEEP;
//			break;
//		case "wood":
//			resourceType = ResourceType.WOOD;
//			break;
//		case "wheat":
//			resourceType = ResourceType.WHEAT;
//			break;
//		default:
//			break;
//		}
//		return resourceType;
//	}
	
	public PortType stringToPortType(String type) {
		PortType portType = null;
		switch (type) {
		case "brick":
			portType = PortType.BRICK;
			break;
		case "ore":
			portType = PortType.ORE;
			break;
		case "sheep":
			portType = PortType.SHEEP;
			break;
		case "wood":
			portType = PortType.WOOD;
			break;
		case "wheat":
			portType = PortType.WHEAT;
			break;
		default:
			break;
		}
		return portType;
	}

	public HexType stringToHexType(String type) {
		HexType resourceType = null;
		switch (type) {
		case "brick":
			resourceType = HexType.BRICK;
			break;
		case "ore":
			resourceType = HexType.ORE;
			break;
		case "sheep":
			resourceType = HexType.SHEEP;
			break;
		case "wood":
			resourceType = HexType.WOOD;
			break;
		case "wheat":
			resourceType = HexType.WHEAT;
			break;
		case "desert":
			resourceType = HexType.DESERT;
			break;
		case "water":
			resourceType = HexType.WATER;
			break;
		default:
			break;
		}
		return resourceType;
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

	public List<JsonPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(List<JsonPlayer> players) {
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

	public JsonBank getBank() {
		return bank;
	}

	public void setBank(JsonBank bank) {
		this.bank = bank;
	}

	public JsonTurnTracker getTurnTracker() {
		return turnTracker;
	}

	public void setTurnTracker(JsonTurnTracker turnTracker) {
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

	public JsonTradeOffer getTradeOffer() {
		return tradeOffer;
	}

	public void setTradeOffer(JsonTradeOffer tradeOffer) {
		this.tradeOffer = tradeOffer;
	}
}
