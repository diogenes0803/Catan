package client.join;

import java.util.Observable;

import shared.models.CatanModel;
import shared.models.Player;
import client.base.Controller;
import client.communication.ServerPoller;
import client.communication.ServerProxy;
import client.data.PlayerInfo;


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
			
			/*
			String[] values = new String[ServerProxy.getInstance().listAI().getaITypes().size()];
			int i = 0;
			for (String value : ServerProxy.getInstance().listAI().getaITypes())
			{
				values[i] = value;
				i++;
			}
			*/
			//Hack because I know that this is the only working value currently
			String[] values = new String[1];
			values[0] = "LARGEST_ARMY";
			
			getView().setAIChoices(values);
			
			PlayerInfo[] playerInfo = new PlayerInfo[playersJoined];
			Player[] players = CatanModel.getInstance().getGameManager().getGame().getPlayers();
			
			for (int j = 0; j < playersJoined; j++)
			{
				PlayerInfo info = new PlayerInfo();
				info.setColor(players[j].getColor());
				info.setId(players[j].getPlayerId());
				info.setName(players[j].getName());
				info.setPlayerIndex(j);
				playerInfo[j] = info;
			}
			
			getView().setPlayers(playerInfo);
		}
		else {
			//was poller
			CatanModel.getInstance().getGameManager().changed();
		}
		ServerPoller.getInstance().startTimer();
	}

	@Override
	public void addAI() {

		ServerProxy.getInstance().AddAI(getView().getSelectedAI());

		playersJoined = CatanModel.getInstance().getGameManager().getGame().getNumberOfPlayers();
		if (playersJoined == 4) {
			getView().closeModal();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("running");
		start();
		
	}

}

