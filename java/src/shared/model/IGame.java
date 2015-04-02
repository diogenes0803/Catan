package shared.model;

import shared.definitions.CatanColor;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

import java.io.Serializable;
import java.util.*;


public interface IGame extends Serializable {

    public String getName();


    public Integer getID();


    public Random getRandom();


    public int getModelVersion();


    public void reset();


    public boolean isNotInitialized();


    public IDevCardHand getDevCards();


    public void setDevCards(IDevCardHand m_devCards);


    public GameState getGameState();


    public void setGameState(GameState state);


    public IPlayer getCurrentPlayer();


    public void setCurrentPlayer(IPlayer currentPlayer);


    public List<IPlayer> getPlayers();


    public IPlayer getPlayerByIndex(int index) throws ModelException;


    public IPlayer getPlayerByID(int id) throws ModelException;


    public void setPlayers(List<IPlayer> players);


    public IResourceBank getResourceBank();


    public void setResourceBank(IResourceBank bank);


    public ICatanMap getMap();


    public void setMap(ICatanMap map);

    public IPlayer getLongestRoad();

    public void setLongestRoad(IPlayer longestRoad);

    public IPlayer getLargestArmy();

    public void setLargestArmy(IPlayer largestArmy);

    public ITradeOffer getTradeOffer();

    public void setTradeOffer(ITradeOffer tradeOffer);


    public ILog getGameplayLog();

    public void setGameplayLog(ILog gameplayLog);


    public ILog getChatHistory();

    public void setChatHistory(ILog chatHistory);

    public void setVersion(int version);

    public int getVersion();

    public void incrementVersion();

    public void setWinner(IPlayer winner);

    public IPlayer getWinner();

    // Game state checking methods

    boolean isOfferingTrade(IPlayer player);

    boolean isBeingOfferedTrade(IPlayer player);


    public boolean isPlaying(IPlayer player);

    public boolean isDiscarding(IPlayer player);

    public boolean isRolling(IPlayer player);

    public boolean isRobbing(IPlayer player);

    public boolean isPlacingInitialPieces(IPlayer player);


    public boolean gameHasStarted();

    boolean canPlaceRoad(IPlayer player, EdgeLocation edge);

    boolean canPlaceSettlement(IPlayer player, VertexLocation vertex);

    boolean canBuildCity(IPlayer player, VertexLocation vertex);

    Collection<IPlayer> getRobbablePlayers(IPlayer player, HexLocation location);

    Set<PortType> getPlayerPorts(IPlayer player);

    boolean canBuyCity(IPlayer player);

    boolean canBuyRoad(IPlayer player);

    boolean canBuySettlement(IPlayer player);

    boolean canBuyDevCard(IPlayer player);

    boolean canAcceptTrade(IPlayer player);

    boolean canPlayDevCard(IPlayer player);

    boolean canPlayMonopoly(IPlayer player);

    boolean canPlaySoldier(HexLocation robberDestination, IPlayer player);

    boolean canPlayYearOfPlenty(IPlayer player, ResourceType r1, ResourceType r2);

    boolean canPlayMonument(IPlayer player);

    boolean canPlayRoadBuilding(IPlayer player, EdgeLocation edge1, EdgeLocation edge2);

    boolean canMaritimeTrade(IPlayer player, ResourceType give, ResourceType receive, int ratio);

    boolean hasLongestRoad(IPlayer player);

    boolean hasLargestArmy(IPlayer player);

    boolean isPlayersTurn(IPlayer player);

    // this method is just for determining from the GameState if it is a free round
    boolean isFreeRound();

    public void rollNumber(int number);


    public void updateComplete();

    public void addObserver(Observer o);

    public void joinGame(IUser user, CatanColor playerColor);

    public boolean canJoinGame(IUser user, CatanColor playerColor);

    public void robPlayer(IPlayer player, IPlayer victim, HexLocation hexLocation);

    public void finishTurn();

    public void calculateVictoryPoints();

    public void discardCards(IPlayer player, IResourceBank cards);

    public boolean verifyResourceAmount();
}