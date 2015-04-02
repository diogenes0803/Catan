package client.join;

import client.base.Controller;
import client.base.IAction;
import client.data.GameInfo;
import client.data.PlayerInfo;
import client.misc.IMessageView;
import client.network.GameAdministrator;
import client.network.IGameAdministrator;
import client.network.NetworkException;
import shared.definitions.CatanColor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;



public class JoinGameController extends Controller implements IJoinGameController {
    private final static Logger logger = Logger.getLogger("catan");

	private INewGameView newGameView;
	private ISelectColorView selectColorView;
	private IMessageView messageView;
	private IAction joinAction;
    private IGameAdministrator m_admin;
    private GameInfo m_joinGame;

    private static final int c_millisecondsPerSecond = 1000;
    private static final int c_defaultPollingSeconds = 3;
    private Timer m_timer;
    private GameInfo[] prevGames = null;
    private List<PlayerInfo> prevPlayers = null;
	

	public JoinGameController(IJoinGameView view, INewGameView newGameView, 
								ISelectColorView selectColorView, IMessageView messageView) {

		super(view);

		setNewGameView(newGameView);
		setSelectColorView(selectColorView);
		setMessageView(messageView);

        m_admin = GameAdministrator.getInstance();
        m_timer = new Timer(c_defaultPollingSeconds * c_millisecondsPerSecond, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getGames();

                if (prevGames != null && m_joinGame != null && getSelectColorView().isModalShowing()) {
                    for (int i = 0; i < prevGames.length; i++) {
                        if (prevGames[i].getId() == m_joinGame.getId()) {
                            if (!prevGames[i].equals(m_joinGame)) {
                                m_joinGame = prevGames[i];
//                            getSelectColorView().closeThisModal();
                                getColors();
//                            getSelectColorView().showModal();
                                getSelectColorView().refresh();
                            }
                            break;
                        }
                    }
                }
            }
        });
	}
	
	public IJoinGameView getJoinGameView() {
		
		return (IJoinGameView)super.getView();
	}
	

	public IAction getJoinAction() {
		
		return joinAction;
	}


	public void setJoinAction(IAction value) {	
		
		joinAction = value;
	}
	
	public INewGameView getNewGameView() {
		
		return newGameView;
	}

	public void setNewGameView(INewGameView newGameView) {
		
		this.newGameView = newGameView;
	}
	
	public ISelectColorView getSelectColorView() {
		
		return selectColorView;
	}
	public void setSelectColorView(ISelectColorView selectColorView) {
		
		this.selectColorView = selectColorView;
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	public void setMessageView(IMessageView messageView) {
		
		this.messageView = messageView;
	}

	@Override
	public void start() {
        m_timer.start();

        getGames();

		getJoinGameView().showModal();
	}

	@Override
	public void startCreateNewGame() {
        getNewGameView().setUseRandomPorts(false);
        getNewGameView().setRandomlyPlaceHexes(false);
        getNewGameView().setRandomlyPlaceNumbers(false);
        getNewGameView().setTitle("");
		getNewGameView().showModal();
	}

	@Override
	public void cancelCreateNewGame() {
        getGames();
		
		getNewGameView().closeTopModal();
	}

	@Override
	public void createNewGame() {
        if (!getNewGameView().getTitle().equals("")) {
            try {
                GameInfo newGame = m_admin.createGame(getNewGameView().getRandomlyPlaceHexes(), getNewGameView().getRandomlyPlaceNumbers(), getNewGameView().getUseRandomPorts(), getNewGameView().getTitle());
                getNewGameView().closeTopModal();
                getGames();
                m_admin.joinGame(newGame.getId(), CatanColor.RED);
                getGames();
            } catch (NetworkException e) {
                logger.log(Level.WARNING, "Create game failed. - Network Exception", e);
                getMessageView().showModal();
                getMessageView().setTitle("Error!");
                getMessageView().setMessage("Create game failed.");
            } catch (IOException e) {
                logger.log(Level.WARNING, "Create game failed. - I/O Exception", e);
                getMessageView().showModal();
                getMessageView().setTitle("Error!");
                getMessageView().setMessage("Create game failed.");
            }
        } else {
            getMessageView().showModal();
            getMessageView().setTitle("Warning!");
            getMessageView().setMessage("The game title is empty.");
        }
	}

	@Override
	public void startJoinGame(GameInfo game) {
        m_joinGame = game;
        prevPlayers = null;

        getColors();
		getSelectColorView().showModal();
	}

	@Override
	public void cancelJoinGame() {

		getJoinGameView().closeTopModal();
	}

	@Override
	public void joinGame(CatanColor color) {
        if (color != null) {
            boolean success = false;
            try {
                success = m_admin.joinGame(m_joinGame.getId(), color);
            } catch (NetworkException e) {
                logger.log(Level.WARNING, "Join game failed. - Network Exception", e);
            }
            // If join succeeded
            if (success) {
                getSelectColorView().closeThisModal();
                getJoinGameView().closeThisModal();
                m_timer.stop();
                joinAction.execute();
            } else {
                getMessageView().showModal();
                getMessageView().setTitle("Error!");
                getMessageView().setMessage("Join game failed.");
            }
        } else {
            getMessageView().showModal();
            getMessageView().setTitle("Warning!");
            getMessageView().setMessage("Please choose a color.");
        }
	}

    @Override
    public void update(Observable o, Object arg) {

    }

    private void getGames() {
        try {
            List<GameInfo> gameList = m_admin.listGames();
            GameInfo[] games = new GameInfo[gameList.size()];
            for (int i = 0; i < games.length; i++) {
                GameInfo tempGame = new GameInfo();
                tempGame.setId(gameList.get(i).getId());
                tempGame.setTitle(gameList.get(i).getTitle());
                List<PlayerInfo> players = gameList.get(i).getPlayers();
                for (int j = 0; j < players.size(); j++) {
                    if (players.get(j).getId() != -1) {
                        tempGame.addPlayer(new PlayerInfo(players.get(j).getId(), players.get(j).getPlayerIndex(), players.get(j).getName(), players.get(j).getColor()));
                    }
                }
                games[i] = tempGame;
            }

            PlayerInfo player = new PlayerInfo(m_admin.getLocalPlayerId(), -1, m_admin.getLocalPlayerName(), null);

            if (!Arrays.equals(games, prevGames)) {
                getJoinGameView().setGames(games, player);
                prevGames = games;
            }

        } catch (NetworkException e) {
            logger.log(Level.WARNING, "Update failed. - Network Exception", e);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Update failed. - I/O Exception", e);
        }
    }

    private void getColors() {
        for (CatanColor color : CatanColor.values()) {
            getSelectColorView().setColorEnabled(color, true);
        }

        for (PlayerInfo player : m_joinGame.getPlayers()) {
            if (player.getId() != m_admin.getLocalPlayerId()) {
                getSelectColorView().setColorEnabled(player.getColor(), false);
            }
        }
    }
}

