package client.turntracker;

import client.base.Controller;
import client.communication.ServerProxy;
import client.map.MapController;
import shared.definitions.CatanColor;
import shared.models.CatanModel;
import shared.models.Game;
import shared.models.Player;
import shared.models.TurnTracker;

import java.util.Observable;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController {

    public TurnTrackerController(ITurnTrackerView view) {

        super(view);

        initFromModel();
    }

    @Override
    public ITurnTrackerView getView() {

        return (ITurnTrackerView) super.getView();
    }

    @Override
    public void endTurn() {
    	int playerIndex = ServerProxy.getInstance().getlocalPlayer().getPlayerIndex();
    	
    	MapController.finishTurn(playerIndex);
    }

    private void initFromModel() {
        //<temp>
        getView().setLocalPlayerColor(CatanColor.RED);
        //</temp>
        
        
    }

    @Override
    public void update(Observable o, Object arg) {
    	if(arg instanceof Game) {
	    	Game thisGame = CatanModel.getInstance().getGameManager().getGame();
	    	getView().setLocalPlayerColor(thisGame.getPlayers()[ServerProxy.getInstance().getlocalPlayer().getPlayerIndex()].getColor());
	    	
	    	Player[] players = CatanModel.getInstance().getGameManager().getGame().getPlayers();
	    	for (int i = 0; i < 4; i++) {
	    		String playerName = players[i].getName();
	    		CatanColor color = players[i].getColor();
	    		
	    		getView().initializePlayer(i, playerName, color);
	    		
	    		boolean highlight = false;
	    		if (i == thisGame.getTurnTracker().getCurrentTurn()) {
	    			highlight = true;
	    		}
	    		//What are these??? How do I populate them?
	    		boolean largestArmy = false;
	    		boolean longestRoad = false;
	    		
	    		getView().updatePlayer(i, players[i].getVictoryPoint(), highlight, largestArmy, longestRoad);
	    	}
	    	
	    	if(ServerProxy.getInstance().getlocalPlayer().getPlayerIndex() != thisGame.getTurnTracker().getCurrentTurn())
        	{
        		getView().updateGameState("Waiting for other Players", false);
        	}
        	else
        	{
        		getView().updateGameState("Finish Turn", true);
        	}		
	    }
    }
}

