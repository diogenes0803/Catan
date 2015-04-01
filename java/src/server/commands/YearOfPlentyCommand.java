package server.commands;

import server.Server;
import server.model.ServerModel;
import shared.communicator.BuildSettlementParams;
import shared.communicator.YearOfPlentyParams;

/**
 * 
 * @author oxbor
 *
 */
public class YearOfPlentyCommand implements Command {

	YearOfPlentyParams params;
	int gameId;
	
	public YearOfPlentyCommand(YearOfPlentyParams params, int gameId) {
		this.params = params;
		this.gameId = gameId;
	}

	@Override
	public void execute() {
		
		ServerModel game = Server.models.get(gameId);
		game.yearOfPlenty(params);
		Server.models.put(gameId, game);

	}

}
