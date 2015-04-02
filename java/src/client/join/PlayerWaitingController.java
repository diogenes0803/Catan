package client.join;

import client.base.Controller;
import client.data.PlayerInfo;
import client.network.GameAdministrator;
import client.network.NetworkException;
import client.poller.ServerPoller;
import shared.model.GameModelFacade;
import shared.model.IPlayer;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;



public class PlayerWaitingController extends Controller implements IPlayerWaitingController, Observer {
    private final static Logger logger = Logger.getLogger("catan");

    PlayerInfo[] m_previousPlayers;

	public PlayerWaitingController(IPlayerWaitingView view) {

		super(view);

        m_previousPlayers = null;

        GameModelFacade.instance().getGame().addObserver(this);
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
        try {
            getView().setAIChoices(GameAdministrator.getInstance().listAI());
        } catch (NetworkException e) {
            logger.log(Level.WARNING, "When attempting to list AI's, this error was thrown: " + e.getMessage(), e);
        }

        if (!GameModelFacade.instance().getGame().gameHasStarted()) {
            getView().showModal();
        }
	}

	@Override
	public void addAI() {
        try {
            GameAdministrator.getInstance().addAI(getView().getSelectedAI());
            ServerPoller.getInstance().updateGame();
        } catch (NetworkException ex) {
            logger.log(Level.WARNING, "When adding an AI, this error was thrown: " + ex.getMessage(), ex);
        }
	}

    @Override
    public void update(Observable o, Object arg) {
        initFromModel();
    }

    private void initFromModel() {
        if (GameModelFacade.instance().getGame().gameHasStarted()) {
            if (getView().isModalShowing()) {
                getView().closeThisModal();
                m_previousPlayers = null;
            }
        }
        else {
            List<IPlayer> playerList = GameModelFacade.instance().getGame().getPlayers();
            PlayerInfo[] players = PlayerInfo.fromPlayers(playerList);
            if (m_previousPlayers == null || !Arrays.equals(m_previousPlayers, players)) {
                getView().setPlayers(players);
                m_previousPlayers = players;
            }
            getView().refresh();
        }
    }
}

