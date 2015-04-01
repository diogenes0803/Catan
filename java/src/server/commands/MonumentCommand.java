package server.commands;

import server.Server;
import server.model.ServerModel;
import shared.communicator.BuildSettlementParams;
import shared.communicator.MonumentParams;

/**
 * 
 * @author oxbor
 *
 */
public class MonumentCommand implements Command {

	MonumentParams params;
	int gameId;
	
	public MonumentCommand(MonumentParams params, int gameId) {
		this.params = params;
		this.gameId = gameId;
	}

	@Override
	public void execute() {
		
		ServerModel game = Server.models.get(gameId);
		game.monument(params);
		Server.models.put(gameId, game);


	}

}
