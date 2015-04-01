package server.commands;

import server.Server;
import server.model.ServerModel;
import shared.communicator.AcceptTradeParams;

/**
 * 
 * @author oxbor
 *
 */
public class AcceptTradeCommand implements Command {

	AcceptTradeParams params;
	int gameId;
	
	public AcceptTradeCommand(AcceptTradeParams params, int gameId) {
		this.params = params;
		this.gameId = gameId;
	}

	@Override
	public void execute() {
		
		ServerModel game = Server.models.get(gameId);
		game.acceptTrade(params);
		Server.models.put(gameId, game);

	}

}
