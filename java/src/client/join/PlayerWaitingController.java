package client.join;

import java.util.Observable;

import shared.models.CatanModel;
import client.base.Controller;
import client.communication.ServerPoller;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {

	private int playersJoined;
	
	public PlayerWaitingController(IPlayerWaitingView view) {

		super(view);
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {

		playersJoined = CatanModel.getInstance().getGameManager().getGame().getNumberOfPlayers();
		if (playersJoined < 4) {
			getView().showModal();
		}
		else {
			ServerPoller.getInstance().startTimer();
			CatanModel.getInstance().getGameManager().changed();
		}
	}

	@Override
	public void addAI() {

		// TEMPORARY
		getView().closeModal();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

