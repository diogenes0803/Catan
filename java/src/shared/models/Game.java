package shared.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import shared.communicator.AcceptTradeParams;
import shared.communicator.BuildCityParams;
import shared.communicator.BuildRoadParams;
import shared.communicator.BuildSettlementParams;
import shared.communicator.BuyDevCardParams;
import shared.communicator.DiscardCardsParams;
import shared.communicator.FinishTurnParams;
import shared.communicator.MaritimeTradeParams;
import shared.communicator.MonopolyParams;
import shared.communicator.MonumentParams;
import shared.communicator.OfferTradeParams;
import shared.communicator.PlaySoldierParams;
import shared.communicator.RoadBuildingParams;
import shared.communicator.RobPlayerParams;
import shared.communicator.RollNumberParams;
import shared.communicator.SendChatParams;
import shared.communicator.YearOfPlentyParams;
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
import shared.locations.VertexLocation;
import shared.models.jsonholder.Chat;
import shared.models.jsonholder.City;
import shared.models.jsonholder.Deck;
import shared.models.jsonholder.Hex;
import shared.models.jsonholder.JsonBank;
import shared.models.jsonholder.JsonMap;
import shared.models.jsonholder.JsonModelHolder;
import shared.models.jsonholder.JsonPlayer;
import shared.models.jsonholder.JsonPort;
import shared.models.jsonholder.JsonTurnTracker;
import shared.models.jsonholder.Line;
import shared.models.jsonholder.Location;
import shared.models.jsonholder.Log;
import shared.models.jsonholder.Resources;
import shared.models.jsonholder.Road;
import shared.models.jsonholder.Robber;
import shared.models.jsonholder.Settlement;
import client.communication.ServerProxy;
import client.data.RobPlayerInfo;

/**
 * Model for on going game
 *
 * @author HojuneYoo
 */

public class Game {

    private String gameTitle;
    private Player[] players = new Player[4];
    private boolean isStarted;
    private CatanMap map;
    private Bank bank;
    private TurnTracker turnTracker;
    private List<MessageLine> logs;
    private List<MessageLine> chats;
    private int dice;
    private int winner;
    private int gameId;
    private TradeOffer tradeOffer;
    private int version = -1;
    private int numberOfPlayers = 0;
    
    public Game() {
    	gameTitle = "";
    	logs = new ArrayList<MessageLine>();
    	chats = new ArrayList<MessageLine>();
    	turnTracker = new TurnTracker();
		this.setWinner(-1);
		this.setVersion(0);
    	
    }

    public boolean canBuildRoad(EdgeLocation location) {
        Player thisPlayer = players[ServerProxy.getInstance().getlocalPlayer().getPlayerIndex()];
        if (thisPlayer.canBuildRoad(turnTracker) && map.canBuildRoadAt(thisPlayer.getPlayerId(), location, turnTracker))
            return true;
        else
            return false;
    }


    public boolean canPlayDevCard() {
        Player thisPlayer = players[turnTracker.getCurrentTurn()];
        if (thisPlayer.getDevCards().size() == 0) {
            return false;
        } else {
            if ((thisPlayer.hasOldDevCard() && !thisPlayer.hasPlayedDevCard()) || thisPlayer.hasMonument()) {
                return true;
            }
        }

        return false;
    }

    public boolean canOfferTrade(ResourceList offer) {
        Player thisPlayer = players[turnTracker.getCurrentTurn()];
        int brick = offer.getBricks();
        int ore = offer.getOres();
        int sheep = offer.getSheep();
        int wheat = offer.getWheat();
        int wood = offer.getWood();

        // If you offer more than you have, returns false
        if (brick < 0) {

            // If your offer (negative number) added to your resource count is less than 0, fails
            if (0 > brick + thisPlayer.getResCount(ResourceType.BRICK))
                return false;
        }
        if (ore < 0) {
            if (0 > ore + thisPlayer.getResCount(ResourceType.ORE))
                return false;
        }
        if (sheep < 0) {
            if (0 > sheep + thisPlayer.getResCount(ResourceType.SHEEP))
                return false;
        }
        if (wheat < 0) {
            if (0 > wheat + thisPlayer.getResCount(ResourceType.WHEAT))
                return false;
        }
        if (wood < 0) {
            if (0 > wood + thisPlayer.getResCount(ResourceType.WOOD))
                return false;
        }
        return true;
    }

    public boolean canAcceptTrade(int playerIndex) {
        Player thisPlayer = players[playerIndex];
        if (tradeOffer == null) {
            return false;
        }
        ResourceList offer = tradeOffer.getOffer();

        int brick = offer.getBricks();
        int ore = offer.getOres();
        int sheep = offer.getSheep();
        int wheat = offer.getWheat();
        int wood = offer.getWood();
        if (brick < 0) {
            if (0 > thisPlayer.getResCount(ResourceType.BRICK) + brick)
                return false;
        }
        if (ore < 0) {
            if (0 > thisPlayer.getResCount(ResourceType.ORE) + ore)
                return false;
        }
        if (sheep < 0) {
            if (0 > thisPlayer.getResCount(ResourceType.SHEEP) + sheep)
                return false;
        }
        if (wheat < 0) {
            if (0 > thisPlayer.getResCount(ResourceType.WHEAT) + wheat)
                return false;
        }
        if (wood < 0) {
            if (0 > thisPlayer.getResCount(ResourceType.WOOD) + wood)
                return false;
        }
        return true;
    }

    public boolean canRollDice() {
        if (Objects.equals(turnTracker.getStatus(), "Rolling")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canBuildSettlement(VertexLocation location) {
        Player thisPlayer = players[turnTracker.getCurrentTurn()];
        if (thisPlayer.canBuildSettlement() && map.canBuildSettlementAt(thisPlayer.getPlayerId(), location)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canUpgradeToCity(VertexLocation location) {
        Player thisPlayer = players[turnTracker.getCurrentTurn()];
        if (thisPlayer.canBuildCity() && map.canUpgradeSettlementAt(thisPlayer.getPlayerId(), location)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canBuyDevCard() {
        if (bank.getDevCards().size() == 0) {
            return false;
        }

        Player thisPlayer = players[turnTracker.getCurrentTurn()];
        if (thisPlayer.canBuyDevCard()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canMoveRobber(HexLocation destination) {
        if (dice == 7) {
            HexLocation from = map.getRobberLocation();
            if (from.getX() == destination.getX() && from.getY() == destination.getY()) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean canRobPlayer(HexTile h, Player p) {
        //Vertex [] v = (Vertex[]) h.getVertices().values().toArray();
        Vertex[] v = new Vertex[]{h.getVertexAt(VertexDirection.West),
                h.getVertexAt(VertexDirection.East),
                h.getVertexAt(VertexDirection.NorthEast),
                h.getVertexAt(VertexDirection.NorthWest),
                h.getVertexAt(VertexDirection.SouthEast),
                h.getVertexAt(VertexDirection.SouthWest)};
        /**
         *
         * While we should check if a player has cards, it is important to first see if the player even exists at that tile.
         * It does not matter if they have cards, they cannot be selected to be robbed.
         *
         * If the player does have a settlement there we can then check if they have a resource card.
         *
         * TODO: Set this canDo in a try-catch block statement to determine what error message must be displayed
         */

        for (int i = 0; i < 6; i++) {
            try {   //NullPointerException: if Settlement does not exist we cannot access PlayerID
                if (v[i].getSettlement().getOwnerPlayerIndex() == p.getPlayerId())   // We confirm that the target player owns this settlement
                    if (p.getResCards().size() > 0) // We can now check if they have available resource cards to steal
                        return true;
                    else
                        return false;   // No cards
            } catch (Exception e) {
                //Nothing. Continue loop
            }

        }
        // No settlement owned by player exists here
        return false;
    }

    public boolean canFinishTurn() {
        if (Objects.equals(turnTracker.getStatus(), "Playing")) {
            return true;
        } else {
            return false;
        }
    }

    public JsonModelHolder toJsonModel() {
        JsonModelHolder jsonModel = new JsonModelHolder();
        jsonModel.setDeck(createJsonDeck());
        jsonModel.setMap(createJsonMap());
        jsonModel.setPlayers(createJsonPlayers());
        jsonModel.setChat(createChat());
        jsonModel.setLog(createLog());
        jsonModel.setBank(createJsonBank());
        jsonModel.setTurnTracker(createJsonTurnTracker());
        jsonModel.setWinner(winner);

        return jsonModel;
    }

    private JsonTurnTracker createJsonTurnTracker() {
        JsonTurnTracker jsonTurnTracker = new JsonTurnTracker();
        jsonTurnTracker.setCurrentTurn(turnTracker.getCurrentTurn());
        jsonTurnTracker.setLargestArmy(turnTracker.getLargestArmy());
        jsonTurnTracker.setLongestRoad(turnTracker.getLongestRoad());
        jsonTurnTracker.setStatus(turnTracker.getStatus());
        return jsonTurnTracker;
    }

    private JsonBank createJsonBank() {
        int brick = 0;
        int ore = 0;
        int sheep = 0;
        int wheat = 0;
        int wood = 0;
        for (ResCard card : bank.getResCards()) {
            switch (card.getType()) {
                case BRICK:
                    brick++;
                    break;
                case ORE:
                    ore++;
                    break;
                case SHEEP:
                    sheep++;
                    break;
                case WHEAT:
                    wheat++;
                    break;
                case WOOD:
                    wood++;
                    break;
            }
        }
        return new JsonBank(brick, wood, sheep, wheat, ore);
    }

    private Log createLog() {
        Log log = new Log();
        List<Line> lines = new ArrayList<Line>();
        for (MessageLine thisLine : logs) {
            Line line = new Line();
            line.setMessage(thisLine.getMessage());
            line.setSource(thisLine.getSource());
            lines.add(line);
        }
        log.setLines(lines);
        return log;
    }

    private Chat createChat() {
        Chat chat = new Chat();
        List<Line> lines = new ArrayList<Line>();
        for (MessageLine thisLine : chats) {
            Line line = new Line();
            line.setMessage(thisLine.getMessage());
            line.setSource(thisLine.getSource());
            lines.add(line);
        }
        chat.setLines(lines);
        return chat;
    }

    private JsonMap createJsonMap() {
        JsonMap jsonMap = new JsonMap();
        HexTile[][] hexArray = map.getHexTiles();
        List<Hex> hexes = new ArrayList<Hex>();
        List<Road> roads = new ArrayList<Road>();
        List<City> cities = new ArrayList<City>();
        List<Settlement> settlements = new ArrayList<Settlement>();
        List<JsonPort> ports = new ArrayList<JsonPort>();
        for (int i = 0; i < hexArray.length; i++) {
            for (int j = 0; j < hexArray[i].length; j++) {
                if (hexArray[i][j] != null) {
                    HexTile thisTile = hexArray[i][j];
                    int x = thisTile.getLocation().getX();
                    int y = thisTile.getLocation().getY();
                    if(thisTile.getHexType() != HexType.WATER) {
	                    Hex hex = new Hex();
	                    if (thisTile.getHexType() != null && thisTile.getHexType() != HexType.DESERT) {
	                        hex.setResource(hexTypeToString(thisTile.getHexType()));
	                    }
	                    if (thisTile.getToken() != -1) {
	                        hex.setNumber(thisTile.getToken());
	                    }
	                    else {
	                    	hex.setNumber(-1);
	                    }
	                    hex.setLocation(new Location(x, y));
	                    hexes.add(hex);
                    }
                    for (Map.Entry<EdgeDirection, Edge> entry : thisTile.getEdges().entrySet()) {
                        EdgeDirection key = entry.getKey();
                        Edge value = entry.getValue();
                        if (value.getRoad() != null) {
                            Road road = new Road();
                            road.setOwner(value.getRoad().getOwnerPlayerIndex());
                            road.setLocation(new Location(x, y, edgeDirectionToString(key)));
                        }
                    }
                    for (Map.Entry<VertexDirection, Vertex> entry : thisTile.getVertices().entrySet()) {
                        VertexDirection key = entry.getKey();
                        Vertex value = entry.getValue();
                        if (value.getHasSettlement()) {
                            if (value.getSettlement().getType() == PieceType.CITY) {
                                City city = new City();
                                city.setOwner(value.getSettlement().getOwnerPlayerIndex());
                                city.setLocation(new Location(x, y, vertexDirectionToString(key)));
                                cities.add(city);

                            } else {
                                Settlement settlement = new Settlement();
                                settlement.setOwner(value.getSettlement().getOwnerPlayerIndex());
                                settlement.setLocation(new Location(x, y, vertexDirectionToString(key)));
                                settlements.add(settlement);
                            }
                        }
                    }
                }
            }

        }

        for (Map.Entry<EdgeLocation, Port> entry : map.getPorts().entrySet()) {
            EdgeLocation key = entry.getKey();
            Port value = entry.getValue();
            JsonPort port = new JsonPort();
            port.setDirection(edgeDirectionToString(key.getDir()));
            port.setLocation(new Location(key.getHexLoc().getX(), key.getHexLoc().getY()));
            port.setRatio(value.getRatio());
            if (value.getType() != PortType.THREE) {
                port.setResource(portTypeToString(value.getType()));
            }
            ports.add(port);
        }

        Robber robber = new Robber();
        robber.setX(map.getRobberLocation().getX());
        robber.setY(map.getRobberLocation().getY());

        jsonMap.setCities(cities);
        jsonMap.setHexes(hexes);
        jsonMap.setPorts(ports);
        jsonMap.setRadius(map.getRadius());
        jsonMap.setRobber(robber);
        jsonMap.setRoads(roads);
        jsonMap.setSettlements(settlements);

        return jsonMap;
    }

    private String edgeDirectionToString(EdgeDirection direction) {
        switch (direction) {
            case North:
                return "N";
            case NorthEast:
                return "NE";
            case NorthWest:
                return "NW";
            case South:
                return "S";
            case SouthEast:
                return "SE";
            case SouthWest:
                return "SW";
            default:
                return null;
        }
    }

    private String vertexDirectionToString(VertexDirection direction) {
        switch (direction) {
            case NorthEast:
                return "NE";
            case East:
                return "E";
            case SouthEast:
                return "SE";
            case SouthWest:
                return "SW";
            case West:
                return "W";
            case NorthWest:
                return "NW";
            default:
                return null;
        }
    }

    //	private String resourceToString(ResourceType type) {
//		switch(type) {
//			case BRICK:
//				return "brick";
//			case WOOD:
//				return "wood";
//			case SHEEP:
//				return "sheep";
//			case WHEAT:
//				return "wheat";
//			case ORE:
//				return "ore";
//			default:
//				return null;
//		}
//	}
    private String portTypeToString(PortType type) {
        switch (type) {
            case BRICK:
                return "brick";
            case WOOD:
                return "wood";
            case SHEEP:
                return "sheep";
            case WHEAT:
                return "wheat";
            case ORE:
                return "ore";
            default:
                return null;
        }
    }

    private String hexTypeToString(HexType type) {
        switch (type) {
            case BRICK:
                return "brick";
            case WOOD:
                return "wood";
            case SHEEP:
                return "sheep";
            case WHEAT:
                return "wheat";
            case ORE:
                return "ore";
            case DESERT:
                return "desert";
            case WATER:
                return "water";
            default:
                return null;
        }
    }


    private Deck createJsonDeck() {
        Deck thisDeck = new Deck();
        int monopoly = 0;
        int monument = 0;
        int roadBuilding = 0;
        int soldier = 0;
        int yearOfPlenty = 0;
        for (DevCard card : bank.getDevCards()) {
            switch (card.getType()) {
                case MONOPOLY:
                    monopoly++;
                    break;
                case MONUMENT:
                    monument++;
                    break;
                case ROAD_BUILD:
                    roadBuilding++;
                    break;
                case SOLDIER:
                    soldier++;
                    break;
                case YEAR_OF_PLENTY:
                    yearOfPlenty++;
                    break;
            }
        }
        thisDeck.setMonopoly(monopoly);
        thisDeck.setMonument(monument);
        thisDeck.setRoadBuilding(roadBuilding);
        thisDeck.setSoldier(soldier);
        thisDeck.setYearOfPlenty(yearOfPlenty);
        return thisDeck;
    }

    private JsonPlayer[] createJsonPlayers() {
        JsonPlayer[] jsonPlayers = new JsonPlayer[4];
        for (int i = 0; i < players.length; i++) {
        	if(players[i] == null)
        		continue;
            Player thisPlayer = players[i];
            JsonPlayer player = new JsonPlayer();
            int brick = 0;
            int ore = 0;
            int sheep = 0;
            int wheat = 0;
            int wood = 0;
            for (ResCard card : thisPlayer.getResCards()) {
                switch (card.getType()) {
                    case BRICK:
                        brick++;
                        break;
                    case ORE:
                        ore++;
                        break;
                    case SHEEP:
                        sheep++;
                        break;
                    case WHEAT:
                        wheat++;
                        break;
                    case WOOD:
                        wood++;
                        break;
                }
            }
            Resources resources = new Resources(brick, wood, sheep, wheat, ore);
            int newMonopoly = 0;
            int newMonument = 0;
            int newRoadBuilding = 0;
            int newSoldier = 0;
            int newYearOfPlenty = 0;
            int oldMonopoly = 0;
            int oldMonument = 0;
            int oldRoadBuilding = 0;
            int oldSoldier = 0;
            int oldYearOfPlenty = 0;
            for (DevCard card : thisPlayer.getDevCards()) {
                switch (card.getType()) {
                    case MONOPOLY:
                        if (!card.isOld())
                            newMonopoly++;
                        else
                            oldMonopoly++;
                        break;
                    case MONUMENT:
                        if (!card.isOld())
                            newMonument++;
                        else
                            oldMonument++;
                        break;
                    case ROAD_BUILD:
                        if (!card.isOld())
                            newRoadBuilding++;
                        else
                            oldRoadBuilding++;
                        break;
                    case SOLDIER:
                        if (!card.isOld())
                            newSoldier++;
                        else
                            oldSoldier++;
                        break;
                    case YEAR_OF_PLENTY:
                        if (!card.isOld())
                            newYearOfPlenty++;
                        else
                            oldYearOfPlenty++;
                        break;
                }
            }
            Deck oldDevCards = new Deck(oldMonopoly, oldMonument, oldRoadBuilding, oldSoldier, oldYearOfPlenty);
            Deck newDevCards = new Deck(newMonopoly, newMonument, newRoadBuilding, newSoldier, newYearOfPlenty);
            int roads = 0;
            int cities = 0;
            int settlements = 0;
            for (Piece thisPiece : thisPlayer.getAvailablePieces()) {
                switch (thisPiece.getType()) {
                    case CITY:
                        cities++;
                        break;
                    case ROAD:
                        roads++;
                        break;
                    case SETTLEMENT:
                        settlements++;
                        break;
                    default:
                        break;
                }
            }
            player.setCities(cities);
            player.setRoads(roads);
            player.setSettlements(settlements);
            player.setSoldiers(thisPlayer.getNumSoldierPlayed());
            player.setVictoryPoints(thisPlayer.getVictoryPoint());
            player.setMonuments(thisPlayer.getNumMonumentPlayed());
            player.setPlayedDevCard(thisPlayer.hasPlayedDevCard());
            player.setDiscarded(thisPlayer.isDiscarded());
            player.setPlayerID(thisPlayer.getPlayerId());
            player.setPlayerIndex(i);
            player.setResources(resources);
            player.setOldDevCards(oldDevCards);
            player.setNewDevCards(newDevCards);
            player.setColor(CatanColor.getStringColor(thisPlayer.getColor()));
            player.setName(thisPlayer.getName());
            jsonPlayers[i] = player;
        }
        return jsonPlayers;
    }

    public int getPlayerIndexByPlayerId(int playerId) {
        for (int i = 0; i < players.length; i++) {
            if (players[i].getPlayerId() == playerId) {
                return i;
            }
        }
        return -1;
    }

    public CatanColor getPlayerColorByPlayerName(String playerName) {
        for (int i = 0; i < players.length; i++) {
            if (players[i].getName().equals(playerName)) {
                return players[i].getColor();
            }
        }

        return null;
    }
    
    /**
     * Server updates model with received parameters
     * @param params
     */
    public void sendChat(SendChatParams params) {
    	String chat = params.getContent();
    	String name = players[params.getPlayerIndex()].getName();
    	MessageLine message = new MessageLine(name, chat);

    	chats.add(message);
    	version++;
    }
    
    /**
     * Server updates model with received parameters
     * @param params
     */
    public void rollNumber(RollNumberParams params) {
    	dice = params.getNumber();
    	version++;
    	
    }
    
    /**
     * Server updates model with received parameters
     * @param params
     */
    public void robPlayer(RobPlayerParams params) {
    	List<ResCard> cards = players[params.getVictimIndex()].getResCards();

    	boolean cardFound = false;
    	int index = 0;
    	
    	if (cards.size() != 0)
    	{
	    	while (!cardFound)
	    	{
	    		if (cards.get(index).getType().name() == params.getType())
	    		{
	    			cards.remove(index);
	    			cardFound = true;
	    		}
	    		index++;
	    	}
    	}
    	
    	players[params.getPlayerIndex()].setResCards(cards);
    	
    	version++;
    }
    
    /**
     * Server updates model with received parameters
     * @param params
     */
    public void finishTurn(FinishTurnParams params) {
    	
    	if (params.getPlayerIndex() < 3)
    	{
    		turnTracker.setCurrentTurn(params.getPlayerIndex()+1);
    	}
    	else
    	{
    		turnTracker.setCurrentTurn(0);
    	}
    	
    	version++;
    }
    
    /**
     * Server updates model with received parameters
     * @param params
     */
    public void buyDevCard(BuyDevCardParams params) {
    	
    	List<ResCard> cards = players[params.getPlayerIndex()].getResCards();

    	ResCard cardOre = new ResCard();
    	cardOre.setType(ResourceType.ORE);
    	
    	ResCard cardSheep = new ResCard();
    	cardOre.setType(ResourceType.SHEEP);
    	
    	ResCard cardGrain = new ResCard();
    	cardOre.setType(ResourceType.WHEAT);
    	
    	if (cards.contains(cardOre))
    	{
    		cards.remove(cards.indexOf(cardOre));
    	}
    	if (cards.contains(cardSheep))
    	{
    		cards.remove(cards.indexOf(cardSheep));
    	}
    	if (cards.contains(cardGrain))
    	{
    		cards.remove(cards.indexOf(cardGrain));
    	}
    	
    	List<DevCard> devCards = players[params.getPlayerIndex()].getDevCards();
    	
    	devCards.add(bank.getDevCards().get(0));
    	
    	bank.getResCards().add(cardOre);
    	bank.getResCards().add(cardGrain);
    	bank.getResCards().add(cardSheep);
    	
    	
    	players[params.getPlayerIndex()].setResCards(cards);
    	players[params.getPlayerIndex()].setDevCards(devCards);
    	
    	version++;
    }
    
    /**
     * Server updates model with received parameters
     * @param params
     */
    public void yearOfPlenty(YearOfPlentyParams params) {
    	
    	ResCard card1 = new ResCard(params.getResource1());
    	ResCard card2 = new ResCard(params.getResource2());
    	
    	bank.getResCards().remove(card1);
    	bank.getResCards().remove(card2);
    	
    	players[params.getPlayerIndex()].getResCards().add(card1);
    	players[params.getPlayerIndex()].getResCards().add(card2);
    	
    	version++;
    	
    }
    
    /**
     * Server updates model with received parameters
     * @param params
     */
    public void roadBuilding(RoadBuildingParams params) {
    	
    	bank.getDevCards().remove(DevCardType.ROAD_BUILD);
    	
    	params.getSpot1();
    	params.getSpot2();
    	
    	//params.g
    	
    	version++;
    	
    }
    
    /**
     * Server updates model with received parameters
     * @param params
     */
    public void soldier(PlaySoldierParams params) {
    	
    }
    
    /**
     * Server updates model with received parameters
     * @param params
     */
    public void monopoly(MonopolyParams params) {
    	
    }
    
    /**
     * Server updates model with received parameters
     * @param params
     */
    public void monument(MonumentParams params) {
    	
    }
    
    /**
     * Server updates model with received parameters
     * @param params
     */
    public void buildRoad(BuildRoadParams params) {
    	
    }
    
    /**
     * Server updates model with received parameters
     * @param params
     */
    public void buildSettlement(BuildSettlementParams params) {
    	
    }
    
    /**
     * Server updates model with received parameters
     * @param params
     */
    public void buildCity(BuildCityParams params) {
    	
    }
    
    /**
     * Server updates model with received parameters
     * @param params
     */
    public void offerTrade(OfferTradeParams params) {
    	
    }
    
    /**
     * Server updates model with received parameters
     * @param params
     */
    public void acceptTrade(AcceptTradeParams params) {
    	
    }
    
    /**
     * Server updates model with received parameters
     * @param params
     */
    public void maritimeTrade(MaritimeTradeParams params) {
    	
    }
    
    /**
     * Server updates model with received parameters
     * @param params
     */
    public void discardCards(DiscardCardsParams params) {
    	
    }


    public List<MessageLine> getLogs() {
        return logs;
    }

    public void setLogs(List<MessageLine> logs) {
        this.logs = logs;
    }

    public List<MessageLine> getChats() {
        return chats;
    }

    public void setChats(List<MessageLine> chats) {
        this.chats = chats;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean isStarted) {
        this.isStarted = isStarted;
    }

    public CatanMap getMap() {
        return map;
    }

    public void setMap(CatanMap map) {
        this.map = map;
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

    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }

    public TradeOffer getTradeOffer() {
        return tradeOffer;
    }

    public void setTradeOffer(TradeOffer tradeOffer) {
        this.tradeOffer = tradeOffer;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }


	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}


	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public RobPlayerInfo[] getRobberVictims(HexLocation hexLoc) {
		ArrayList<RobPlayerInfo> robVictims = new ArrayList<RobPlayerInfo>();
		
		HexTile tile = map.getHexTileAt(hexLoc);
		VertexDirection[] values = VertexDirection.values();
		for (VertexDirection vertDir : values) {
			Vertex vertex = tile.getVertexAt(vertDir);
			VertexLocation vLoc = vertex.getLocation();
			if (map.getSettlementAt(vLoc) != null) {
				Piece settlement = map.getSettlementAt(vLoc);
				RobPlayerInfo playerInfo = new RobPlayerInfo();
				
				int playerIndex = settlement.getOwnerPlayerIndex();
				int playerID = players[playerIndex].getPlayerId();
				String playerName = players[playerIndex].getName();
				CatanColor color = players[playerIndex].getColor();
				int numResCards = 0;
				for (ResourceType cardType : ResourceType.values()) {
					numResCards += players[playerIndex].getResCount(cardType);
				}
				
				playerInfo.setPlayerIndex(playerIndex);
				playerInfo.setId(playerID);
				playerInfo.setColor(CatanColor.getStringColor(color));
				playerInfo.setName(playerName);
				playerInfo.setNumCards(numResCards);
				
				//Don't add someone twice
				boolean alreadyAdded = false;
				for (int i = 0; i < robVictims.size(); i++) {
					if (robVictims.get(i).getPlayerIndex() == playerIndex) {
						alreadyAdded = true;
					}
				}
				
				//Don't add self to robVictims
				if (playerIndex != ServerProxy.getInstance().getlocalPlayer().getPlayerIndex() && !alreadyAdded) {
					robVictims.add(playerInfo);
				}
			}
			
		}
		
		int arraySize = robVictims.size();
		RobPlayerInfo[] robPlayerInfo = new RobPlayerInfo[arraySize];
		for (int i = 0; i < arraySize; i++) {
			robPlayerInfo[i] = robVictims.get(i);
		}
		
		return robPlayerInfo;
	}
	
	/**
	 * A Method that returns the player who has the most roads and a minimum of (4)?
	 * @return
	 */
	public Player getMostRoads()
	{
		return null;
	}
	
	/**
	 * A Method that returns the player who has the most armies and a minimum of (4)?
	 * @return
	 */
	public Player getMostArmies()
	{
		return null;
	}

}
