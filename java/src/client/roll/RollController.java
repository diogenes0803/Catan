package client.roll;

import java.util.Observable;
import java.util.Random;

import shared.models.Game;
import shared.models.TurnTracker;
import client.base.Controller;
import client.communication.ServerProxy;
import client.map.MapController;
import client.map.RollingState;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController {

    private IRollResultView resultView;

    /**
     * RollController constructor
     *
     * @param view       Roll view
     * @param resultView Roll result view
     */
    public RollController(IRollView view, IRollResultView resultView) {

        super(view);

        setResultView(resultView);
    }

    public IRollResultView getResultView() {
        return resultView;
    }

    public void setResultView(IRollResultView resultView) {
        this.resultView = resultView;
    }

    public IRollView getRollView() {
        return (IRollView) getView();
    }

    @Override
    public void rollDice() {
		Random rng = new Random();
		int dice1 = rng.nextInt(6) + 1;
		int dice2 = rng.nextInt(6) + 1;
		int number = dice1 + dice2;
		//int number = 7;
    	MapController.rollNumber(number);
    	
    	getResultView().setRollValue(number);
        getResultView().showModal();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Game) {
	        int currentTurn = TurnTracker.getInstance().getCurrentTurn();
	        int playerIndex = ServerProxy.getInstance().getlocalPlayer().getPlayerIndex();
	        
	        if (TurnTracker.getInstance().getStatus().equals("Rolling") && playerIndex == currentTurn) {
	        	getRollView().showModal();
	        }
        }
    }

}

