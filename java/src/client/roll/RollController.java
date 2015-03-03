package client.roll;

import client.base.Controller;
import client.communication.ServerProxy;
import client.map.MapController;
import client.map.RollingState;

import java.util.Observable;
import java.util.Random;

import shared.models.TurnTracker;


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
    	MapController.rollNumber(number);
    	
    	getResultView().setRollValue(number);
        getResultView().showModal();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (MapController.state == null) {
			System.out.println("yes");
		}
        System.out.println("here");
        
        int currentTurn = TurnTracker.getInstance().getCurrentTurn();
        int playerIndex = ServerProxy.getInstance().getlocalPlayer().getPlayerIndex();
        System.out.println(currentTurn);
        System.out.println(playerIndex);
        
        if (MapController.state == RollingState.singleton  && playerIndex == currentTurn) {
        	System.out.println("HERE");
        	getRollView().showModal();
        }
    }

}

