package shared.model;

import shared.definitions.CatanColor;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


public class GameModelFacade implements IGameModelFacade {
    private static GameModelFacade m_theFacade = null;

    // The one and only game pointer
    private IGame m_game;
    private IPlayer m_localPlayer;


    private GameModelFacade() {
        m_game = new Game();
    }


    public static GameModelFacade instance() {
        if (m_theFacade == null) {
            m_theFacade = new GameModelFacade();
        }
        return m_theFacade;
    }

    @Override
    public IGame getGame() {
        assert m_game != null : "Attempted to get a null Game object!";
        return m_game;
    }

    @Override
    public void setGame(IGame game) {
        m_game = game;
    }

    @Override
    public IPlayer getLocalPlayer() {
        return m_localPlayer;
    }

    @Override
    public void setLocalPlayer(IPlayer player) {
        m_localPlayer = player;
    }


    @Override
    public boolean canPlaceRoad(EdgeLocation edge) {
        return m_game.canPlaceRoad(getLocalPlayer(), edge);
    }


    @Override
    public boolean canPlaceSettlement(VertexLocation vertex) {
        return m_game.canPlaceSettlement(getLocalPlayer(), vertex);
    }


    @Override
    public boolean canPlaceCity(VertexLocation vertex) {
        return m_game.canBuildCity(getLocalPlayer(), vertex);
    }


    @Override
    public IPlayer getCurrentPlayer() {
        return m_game.getCurrentPlayer();
    }


    @Override
    public Collection<IPlayer> getRobbablePlayers(HexLocation location) {
        return m_game.getRobbablePlayers(getLocalPlayer(), location);
    }


    @Override
    public IResourceBank getLocalPlayerResources() {
        return getLocalPlayer().getResources();
    }


    @Override
    public Set<PortType> getLocalPlayerPorts() {
        return m_game.getPlayerPorts(getLocalPlayer());
    }


    @Override
    public ILog getChatHistory() {
        return m_game.getChatHistory();
    }


    @Override
    public ILog getMoveHistory() {
        return m_game.getGameplayLog();
    }


    @Override
    public boolean canBuyCity() {
        return m_game.canBuyCity(getLocalPlayer());
    }


    @Override
    public boolean canBuyRoad() {
        return m_game.canBuyRoad(getLocalPlayer());
    }


    @Override
    public boolean canBuySettlement() {
        return m_game.canBuySettlement(getLocalPlayer());
    }


    @Override
    public boolean canBuyDevCard() {
        return m_game.canBuyDevCard(getLocalPlayer());
    }


    @Override
    public boolean canAcceptTrade() {
        return m_game.canAcceptTrade(getLocalPlayer());
    }


    @Override
    public boolean canPlayDevCard() {
        return m_game.canPlayDevCard(getLocalPlayer());
    }


    @Override
    public boolean canPlayMonopoly() {
        return m_game.canPlayMonopoly(getLocalPlayer());
    }


    @Override
    public boolean canPlaySoldier(HexLocation robberDestination) {
        return m_game.canPlaySoldier(robberDestination, getLocalPlayer());
    }


    @Override
    public boolean canPlayYearOfPlenty(ResourceType r1, ResourceType r2) {
        return m_game.canPlayYearOfPlenty(getLocalPlayer(), r1, r2);
    }


    @Override
    public boolean canPlayMonument() {
        return m_game.canPlayMonument(getLocalPlayer());
    }


    @Override
    public boolean canPlayRoadBuilding(EdgeLocation edge1, EdgeLocation edge2) {
        return m_game.canPlayRoadBuilding(getLocalPlayer(), edge1, edge2);
    }


    @Override
    public CatanColor getLocalColor() {
        return getLocalPlayer().getColor();
    }

    @Override
    public boolean playerHasLongestRoad(IPlayer player) {
        return m_game.hasLongestRoad(player);
    }

    @Override
    public boolean playerHasLargestArmy(IPlayer player) {
        return m_game.hasLargestArmy(player);
    }


    @Override
    public void newGame() {
        m_game = new Game();
    }

    // this method is just for determining from the GameState if it is a free round
    @Override
    public boolean isFreeRound() {
        return m_game.isFreeRound();
    }

    @Override
    public List<IPlayer> getNonLocalPlayers() {
        List<IPlayer> nonLocalPlayers = new ArrayList<>(getGame().getPlayers().size() - 1);

        for (IPlayer player : getGame().getPlayers()) {
            if (!player.equals(getLocalPlayer())) {
                nonLocalPlayers.add(player);
            }
        }

        assert nonLocalPlayers.size() == 3 : "There should be 3 non-local players, but there are not!";

        return nonLocalPlayers;
    }


    @Override
    public boolean localPlayerIsPlaying() {
        return getGame().isPlaying(getLocalPlayer());
    }

    @Override
    public boolean localPlayerIsDiscarding() {
        return getGame().isDiscarding(getLocalPlayer());
    }

    @Override
    public boolean localPlayerIsRolling() {
        return getGame().isRolling(getLocalPlayer());
    }

    @Override
    public boolean localPlayerPlayerIsRobbing() {
        return getGame().isRobbing(getLocalPlayer());
    }

    @Override
    public boolean localPlayerIsPlacingInitialPieces() {
        return getGame().isPlacingInitialPieces(getLocalPlayer());
    }

    @Override
    public boolean localPlayerIsOfferingTrade() {
        return getGame().isOfferingTrade(getLocalPlayer());
    }

    @Override
    public boolean localPlayerIsBeingOfferedTrade() {
        return getGame().isBeingOfferedTrade(getLocalPlayer());
    }

    @Override
    public boolean isLocalPlayersTurn() {
        return getGame().isPlayersTurn(getLocalPlayer());
    }
}
