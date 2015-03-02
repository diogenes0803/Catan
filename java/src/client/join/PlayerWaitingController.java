package client.join;

import java.util.Observable;

import shared.models.CatanModel;
import shared.models.Game;
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

		playersJoined = CatanModel.getInstance().getGameManager().getGame().getPlayers().length;
		if (playersJoined < 4) {
			getView().showModal();
		}
		else {
			ServerPoller.getInstance().startTimer();
			//Game game = CatanModel.getInstance().getGameManager().getGame();
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

