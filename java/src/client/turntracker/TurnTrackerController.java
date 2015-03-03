package client.turntracker;

import client.base.Controller;
import client.communication.ServerProxy;
import shared.definitions.CatanColor;
import shared.models.Game;
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

    }

    private void initFromModel() {
        //<temp>
        getView().setLocalPlayerColor(CatanColor.RED);
        //</temp>
    }

    @Override
    public void update(Observable o, Object arg) {
    	Game thisGame = (Game)arg;
    	getView().setLocalPlayerColor(thisGame.getPlayers()[ServerProxy.getInstance().getlocalPlayer().getPlayerIndex()].getColor());
    	
    	boolean largestArmy = false;
    	boolean longestRoad = false;
    	
    	//Need to know when the TurnTacker Initialize
    	if(thisGame.getTurnTracker()!=null)
    	{
    		
    		getView().initializePlayer(thisGame.getTurnTracker().getCurrentTurn(), thisGame.getPlayers()[thisGame.getTurnTracker().getCurrentTurn()].getName(),
    				thisGame.getPlayers()[thisGame.getTurnTracker().getCurrentTurn()].getColor());
    		
    		
    		
    		
    		
        	if(thisGame.getTurnTracker().getCurrentTurn()==thisGame.getTurnTracker().getLargestArmy())
        	{
        		largestArmy = true;
        	}
        	
        	if(thisGame.getTurnTracker().getCurrentTurn()==thisGame.getTurnTracker().getLongestRoad())
        	{
        		longestRoad = true;
        	}
        	System.out.println("AAAAAAAAAAAAAAAAAaa" + thisGame.getTurnTracker().getCurrentTurn());
        	getView().updatePlayer(
        			thisGame.getTurnTracker().getCurrentTurn(), 
        			thisGame.getPlayers()[thisGame.getTurnTracker().getCurrentTurn()].getVictoryPoint(), 
        			true, 
        			largestArmy,
        			longestRoad);
        
        	
        	
        /**
       	  * I can only find two messages
       	  * "Waiting for others player"
       	  * "Finish Turn"
       	  * Finish Turn
       	  * getView().updateGameState("Game State Button", true);
       	  * 
       	  **/
        	
        	if(ServerProxy.getInstance().getlocalPlayer().getPlayerIndex()!=thisGame.getTurnTracker().getCurrentTurn())
        		
        	{
        		getView().updateGameState("Waiting for others players", true);
        	}
        	else
        	{
        		getView().updateGameState("End the turn", true);
        	}
    	}
    	
    	 
    			
    }

}

