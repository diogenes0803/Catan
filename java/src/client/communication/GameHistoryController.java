package client.communication;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import shared.definitions.CatanColor;
import shared.models.CatanModel;
import shared.models.Game;
import shared.models.MessageLine;
import client.base.Controller;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController {

	public GameHistoryController(IGameHistoryView view) {
		
		super(view);
		
	}
	
	@Override
	public IGameHistoryView getView() {
		
		return (IGameHistoryView)super.getView();
	}

	@Override
	public void update(Observable o, Object arg) {
		Game currentGame = CatanModel.getInstance().getGameManager().getGame();
		Game updatedGame = (Game)arg;
		if(updatedGame.getLogs().size() - currentGame.getLogs().size() != 0) {
			updateFromModel(updatedGame);
		}
	}
	
	private void updateFromModel(Game game) {
		List<LogEntry> entries = new ArrayList<LogEntry>();
		List<MessageLine> logs = game.getLogs();
		for(MessageLine log : logs) {
			CatanColor playerColor = game.getPlayerColorByPlayerName(log.getSource());
			entries.add(new LogEntry(playerColor, log.getMessage()));
		}
		
		getView().setEntries(entries);
	}
}

