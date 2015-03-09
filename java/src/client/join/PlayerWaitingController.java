package client.join;

import java.util.List;
import java.util.Observable;

import shared.models.CatanModel;
import client.base.Controller;
import client.communication.ServerPoller;
import client.communication.ServerProxy;
import client.data.GameInfo;
import client.data.PlayerInfo;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {

	private int playersJoined = 0;
	
	public PlayerWaitingController(IPlayerWaitingView view) {

		super(view);
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
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
	}

	@Override
	public void addAI() {

		ServerProxy.getInstance().AddAI(getView().getSelectedAI());
		
	}
	
	private void refreshPlayers() {
		int joinedGameId = ServerProxy.getInstance().getCurrentGameId();
		List<PlayerInfo> playerInfoList = CatanModel.getInstance().getGameManager().findGameInfoById(joinedGameId).getPlayers();
		PlayerInfo[] playerInfoArray = new PlayerInfo[playerInfoList.size()];
		for(int i = 0; i < playerInfoList.size(); i++) {
			playerInfoArray[i] = playerInfoList.get(i);
		}
		
		getView().setPlayers(playerInfoArray);
	}

	@Override
	public void update(Observable o, Object arg) {
		int currentGameId = ServerProxy.getInstance().getCurrentGameId();
		if(arg instanceof GameInfo[] && currentGameId != -1) {
			GameInfo currentGame = CatanModel.getInstance().getGameManager().findGameInfoById(currentGameId);
			if(playersJoined < currentGame.getPlayers().size()) {
				playersJoined = currentGame.getPlayers().size();
				refreshPlayers();
				getView().closeModal();
				getView().showModal();
				if(playersJoined >= 4) {
					int playerId = ServerProxy.getInstance().getlocalPlayer().getId();
					GameInfo thisGame = CatanModel.getInstance().getGameManager().findGameInfoById(ServerProxy.getInstance().getCurrentGameId());
					ServerProxy.getInstance().getlocalPlayer().setPlayerIndex(thisGame.findPlayerIndexById(playerId));
					ServerPoller.getInstance().stopGameListTimer();
					ServerPoller.getInstance().startGameTimer();
					getView().closeModal();
				}
			}
		}
		
	}

}

