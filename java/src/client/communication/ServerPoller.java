package client.communication;

/**
 * <p>Description: The Server Poller polls the server at regular intervals in order to update
 the current Model state. Is activated when client gui launches.</p>
 * @author Nate Campbell, dbilleter
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import shared.data.GameInfo;
import shared.models.CatanModel;
import shared.models.Game;

public class ServerPoller {

    private Timer gameListTimer;
    private Timer gameTimer;
    private static ServerPoller instance = new ServerPoller();

    public final int START_DELAY = 1 * 1000; //Waits 1 second to start running
    public final int CHECK_FREQUENCY = 1 * 1000; //check every 1 second

    public ServerPoller() {
    	gameListTimer = new Timer();
    	gameTimer = new Timer();


    }

    /**
     * @pre Nothing.
     * @post <p>Asks the server to update the current Model state.<br> The server checks to see if anything actually
     * needs to be updated.</p>
     */
    public void updateModel() {

        CatanModel model = ServerProxy.getInstance().getModel();
        if (model != null) {
            CatanModel.getInstance().getGameManager().setGame(model.getGameManager().getGame());
        }
    }

    /**
     * Stops poller.
     *
     * @pre to be called when client Jframe exits.
     */
    public void stopGameListTimer() {
    	gameListTimer.cancel();
    	gameListTimer.purge();
    }
    
    public void stopGameTimer() {
    	gameTimer.cancel();
    	gameTimer.purge();
    }

    public void startGameTimer() {
    	gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateModel();
            }
        }, START_DELAY, CHECK_FREQUENCY);

    }
    
    public void startGameListTimer() {
    	gameListTimer.schedule(new TimerTask() {
            @Override
            public void run() {
            	updateGameList();
            }
        }, START_DELAY, CHECK_FREQUENCY);
    }
    
    public void updateGameList() {
    	GameInfo[] games = ServerProxy.getInstance().listGames().getGames();
    	CatanModel.getInstance().getGameManager().setAvailableGames(games);
    }

    public static ServerPoller getInstance() {
        return instance;
    }

    public static void setInstance(ServerPoller instance) {
        ServerPoller.instance = instance;
    }

}//end class


