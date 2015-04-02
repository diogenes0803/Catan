package shared.model;

import shared.definitions.CatanColor;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

import java.util.Collection;
import java.util.List;
import java.util.Set;


public interface IGameModelFacade {

    public IGame getGame();

    public void setGame(IGame game);

    IPlayer getLocalPlayer();

    void setLocalPlayer(IPlayer player);


    public boolean canPlaceRoad(EdgeLocation edge);


    public boolean canPlaceSettlement(VertexLocation vertex);


    public boolean canPlaceCity(VertexLocation vertex);


    public boolean canBuyCity();


    public boolean canBuyRoad();


    public boolean canBuySettlement();


    public boolean canBuyDevCard();


    public boolean canAcceptTrade();


    public boolean canPlayDevCard();


    public boolean canPlayMonopoly();


    public boolean canPlaySoldier(HexLocation destination);


    public boolean canPlayYearOfPlenty(ResourceType r1, ResourceType r2);


    public boolean canPlayMonument();


    public boolean canPlayRoadBuilding(EdgeLocation edge1, EdgeLocation edge2);


    public Collection<IPlayer> getRobbablePlayers(HexLocation location);



    public IPlayer getCurrentPlayer();


    public IResourceBank getLocalPlayerResources();


    public Set<PortType> getLocalPlayerPorts();


    public ILog getChatHistory();


    public ILog getMoveHistory();

    boolean playerHasLongestRoad(IPlayer player);

    boolean playerHasLargestArmy(IPlayer player);

    // this method is just for determining from the GameState if it is a free round
    public boolean isFreeRound();


    public CatanColor getLocalColor();


    public void newGame();


    public List<IPlayer> getNonLocalPlayers();


    public boolean localPlayerIsPlaying();
    public boolean localPlayerIsDiscarding();
    public boolean localPlayerIsRolling();
    public boolean localPlayerPlayerIsRobbing();
    public boolean localPlayerIsPlacingInitialPieces();

    public boolean localPlayerIsOfferingTrade();
    public boolean localPlayerIsBeingOfferedTrade();

    public boolean isLocalPlayersTurn();

}
