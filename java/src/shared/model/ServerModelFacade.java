package shared.model;

import client.network.HttpCommunicator;
import client.network.IServerProxy;
import client.network.NetworkException;
import client.network.ServerProxy;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

import java.util.Random;
import java.util.logging.Logger;


public class ServerModelFacade implements IServerModelFacade {
    private final static Logger logger = Logger.getLogger("catan");

    private IServerProxy m_theProxy;
    private static ServerModelFacade m_theFacade = null;


    private ServerModelFacade(IServerProxy theProxy) {
        setServerProxy(theProxy);
    }


    public static ServerModelFacade getInstance() {
        if(m_theFacade == null)
            m_theFacade = new ServerModelFacade(new ServerProxy(new HttpCommunicator()));
        return m_theFacade;
    }


    @Override
    public void setServerProxy(IServerProxy theProxy) {
        assert theProxy != null;
        m_theProxy = theProxy;
    }


    @Override
    public void sendChat(String message) throws ModelException {
        assert message != null;

        try {
            String clientModel = m_theProxy.sendChat(GameModelFacade.instance().getLocalPlayer().getIndex(), message);
            new ModelInitializer().initializeClientModel(clientModel, m_theProxy.getPlayerId());
        } catch (NetworkException e) {
            throw new ModelException(e);
        }
    }


    @Override
    public void placeRoad(EdgeLocation edge) throws ModelException {
        assert edge != null;

        if (!GameModelFacade.instance().canPlaceRoad(edge)) {
            throw new ModelException("Preconditions for action not met.");
        }

        try {
            String clientModel =  m_theProxy.buildRoad(GameModelFacade.instance().getLocalPlayer().getIndex(), edge, GameModelFacade.instance().isFreeRound());
            new ModelInitializer().initializeClientModel(clientModel, m_theProxy.getPlayerId());
        } catch (NetworkException e) {
            throw new ModelException(e);
        }
    }



    @Override
    public void placeSettlement(VertexLocation vertex) throws ModelException {
        assert vertex != null;
        if (!GameModelFacade.instance().canPlaceSettlement(vertex)) {
            throw new ModelException("Preconditions for action not met.");
        }

        try {
            String clientModel = m_theProxy.buildSettlement(GameModelFacade.instance().getLocalPlayer().getIndex(), vertex, GameModelFacade.instance().isFreeRound());
            new ModelInitializer().initializeClientModel(clientModel, m_theProxy.getPlayerId());
        } catch (NetworkException e) {
            throw new ModelException(e);
        }
    }


    @Override
    public void placeCity(VertexLocation vertex) throws ModelException {
        assert vertex != null;
        if (!GameModelFacade.instance().canPlaceCity(vertex)) {
            throw new ModelException("Preconditions for action not met.");
        }

        try {
            String clientModel = m_theProxy.buildCity(GameModelFacade.instance().getLocalPlayer().getIndex(), vertex);
            new ModelInitializer().initializeClientModel(clientModel, m_theProxy.getPlayerId());
        } catch (NetworkException e) {
            throw new ModelException(e);
        }
    }


    @Override
    public void buyDevCard() throws ModelException {
        if (!GameModelFacade.instance().canBuyDevCard()) {
            throw new ModelException("Preconditions for action not met.");
        }

        IPlayer curPlayer = GameModelFacade.instance().getLocalPlayer();
        try {
            String clientModel = m_theProxy.buyDevCard(curPlayer.getIndex());
            new ModelInitializer().initializeClientModel(clientModel, m_theProxy.getPlayerId());
        } catch (NetworkException e) {
            throw new ModelException(e);
        }
    }


    @Override
    public void playSoldier(HexLocation hex, int victimIndex) throws ModelException {
        assert hex != null;

        if (!GameModelFacade.instance().canPlaySoldier(hex)) {
            throw new ModelException("Preconditions for action not met.");
        }

        try {
            String clientModel = m_theProxy.playSoldier(GameModelFacade.instance().getLocalPlayer().getIndex(), hex, victimIndex);
            new ModelInitializer().initializeClientModel(clientModel, m_theProxy.getPlayerId());
        } catch (NetworkException e) {
            throw new ModelException(e);
        }
    }


    @Override
    public void playYearOfPlenty(ResourceType r1, ResourceType r2) throws ModelException {
        if (!GameModelFacade.instance().canPlayYearOfPlenty(r1, r2)) {
            throw new ModelException("Preconditions for action not met.");
        }

        try {
            String clientModel = m_theProxy.playYearOfPlenty(GameModelFacade.instance().getLocalPlayer().getIndex(), r1, r2);
            new ModelInitializer().initializeClientModel(clientModel, m_theProxy.getPlayerId());
        } catch (NetworkException e) {
            throw new ModelException(e);
        }
    }


    @Override
    public void playRoadBuilding(EdgeLocation e1, EdgeLocation e2) throws ModelException {
        if (!GameModelFacade.instance().canPlayRoadBuilding(e1, e2)) {
            throw new ModelException("Preconditions for action not met.");
        }

        try {
            String clientModel = m_theProxy.playRoadBuilding(GameModelFacade.instance().getLocalPlayer().getIndex(), e1, e2);
            new ModelInitializer().initializeClientModel(clientModel, m_theProxy.getPlayerId());
        } catch (NetworkException e) {
            throw new ModelException(e);
        }
    }


    @Override
    public void playMonopoly(ResourceType rType) throws ModelException {
        if (!GameModelFacade.instance().canPlayMonopoly()) {
            throw new ModelException("Preconditions for action not met.");
        }

        try {
            String clientModel = m_theProxy.playMonopoly(GameModelFacade.instance().getLocalPlayer().getIndex(), rType);
            new ModelInitializer().initializeClientModel(clientModel, m_theProxy.getPlayerId());
        } catch (NetworkException e) {
            throw new ModelException(e);
        }
    }


    @Override
    public void playMonument() throws ModelException {
        if (!GameModelFacade.instance().canPlayMonument()) {
            throw new ModelException("Preconditions for action not met.");
        }

        try {
            String clientModel = m_theProxy.playMonument(GameModelFacade.instance().getLocalPlayer().getIndex());
            new ModelInitializer().initializeClientModel(clientModel, m_theProxy.getPlayerId());
        } catch (NetworkException e) {
            throw new ModelException(e);
        }
    }


    @Override
    public void robPlayer(HexLocation hex, int victimIndex) throws ModelException {
        assert hex != null;

        // TODO: check conditions?

        try {
            String clientModel = m_theProxy.robPlayer(GameModelFacade.instance().getLocalPlayer().getIndex(), victimIndex, hex);
            new ModelInitializer().initializeClientModel(clientModel, m_theProxy.getPlayerId());
        } catch (NetworkException e) {
            throw new ModelException(e);
        }
    }


    @Override
    public void acceptTrade(boolean willAccept) throws ModelException {
        if (willAccept && !GameModelFacade.instance().canAcceptTrade()) {
            throw new ModelException("Preconditions for action not met.");
        }

        IPlayer p = GameModelFacade.instance().getLocalPlayer();
        assert GameModelFacade.instance().getGame().getTradeOffer().getReceiver().equals(p);

        try {
            String clientModel = m_theProxy.acceptTrade(p.getIndex(), willAccept);
            new ModelInitializer().initializeClientModel(clientModel, m_theProxy.getPlayerId());
        } catch (NetworkException e) {
            throw new ModelException(e);
        }
    }


    @Override
    public void offerTrade(IResourceBank offer, int recipientPlayerIndex) throws ModelException {
        IPlayer p = GameModelFacade.instance().getLocalPlayer();

        if (!p.canAfford(offer) || !GameModelFacade.instance().localPlayerIsPlaying() || !offer.containsResources()) {
            throw new ModelException("Preconditions for action not met.");
        }

        try {
            String clientModel = m_theProxy.offerTrade(p.getIndex(), offer, recipientPlayerIndex);
            new ModelInitializer().initializeClientModel(clientModel, m_theProxy.getPlayerId());
        } catch (NetworkException e) {
            throw new ModelException(e);
        }
    }


    @Override
    public void maritimeTrade(int ratio, ResourceType giving, ResourceType getting) throws ModelException {
        assert ratio == 2 || ratio == 3 || ratio == 4;

        IPlayer p = GameModelFacade.instance().getLocalPlayer();

        if (!GameModelFacade.instance().localPlayerIsPlaying() || p.getResources().getCount(giving) < ratio) {
            throw new ModelException("Preconditions for action not met.");
        }

        try {
            String clientModel = m_theProxy.maritimeTrade(p.getIndex(), ratio, giving, getting);
            new ModelInitializer().initializeClientModel(clientModel, m_theProxy.getPlayerId());
        } catch (NetworkException e) {
            throw new ModelException(e);
        }
    }


    @Override
    public void discardCards(IResourceBank discardedCards) throws ModelException {
        IPlayer p = GameModelFacade.instance().getLocalPlayer();

        if (GameModelFacade.instance().getGame().getGameState() != GameState.Discarding
                || p.getResources().getCount() <= 7
                || !p.canAfford(discardedCards)) {
            throw new ModelException("Preconditions for action not met.");
        }

        try {
            String clientModel = m_theProxy.discardCards(p.getIndex(), discardedCards);
            new ModelInitializer().initializeClientModel(clientModel, m_theProxy.getPlayerId());
        } catch (NetworkException e) {
            throw new ModelException(e);
        }
    }


    @Override
    public int rollNumber() throws ModelException {
        // the range is 10 (2 - 12)
        final int ROLL_NUMBERS_RANGE = 10;
        final int MINIMUM_ROLL       = 2;
        final Random randomNumberGenerator = new Random();

        int rolledNumber = randomNumberGenerator.nextInt(ROLL_NUMBERS_RANGE) + MINIMUM_ROLL;
        assert rolledNumber >= 2 && rolledNumber <= 12;

        IPlayer p = GameModelFacade.instance().getLocalPlayer();
        if (GameModelFacade.instance().getGame().getGameState() != GameState.Rolling || !p.equals(GameModelFacade.instance().getGame().getCurrentPlayer())) {
            throw new ModelException("Preconditions for action not met.");
        }

        try {
            String clientModel = m_theProxy.rollNumber(p.getIndex(), rolledNumber);
            new ModelInitializer().initializeClientModel(clientModel, m_theProxy.getPlayerId());
        }
        catch (NetworkException e) {
            throw new ModelException(e);
        }

        return rolledNumber;
    }


    @Override
    public void finishTurn() throws ModelException {
        logger.entering("shared.model.ServerModelFacade", "finishTurn");

        IPlayer p = GameModelFacade.instance().getLocalPlayer();
        logger.fine("Player " + p.getName() + " ended turn.");

        if (!GameModelFacade.instance().localPlayerIsPlaying()
                && !GameModelFacade.instance().localPlayerIsPlacingInitialPieces()) {
            throw new ModelException("Preconditions for action not met.");
        }

        try {
            String clientModel = m_theProxy.finishTurn(p.getIndex());
            new ModelInitializer().initializeClientModel(clientModel, m_theProxy.getPlayerId());
        } catch (NetworkException e) {
            throw new ModelException(e);
        }

        logger.exiting("shared.model.ServerModelFacade", "finishTurn");
    }
}
