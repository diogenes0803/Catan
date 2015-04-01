package server.commands;

import server.Server;
import server.model.ServerModel;
import shared.communicator.BuildSettlementParams;
import shared.communicator.RobPlayerParams;

/**
 * 
 * @author oxbor
 *
 */
public class RobPlayerCommand implements Command {

	RobPlayerParams params;
	int gameId;
	
	public RobPlayerCommand(RobPlayerParams params, int gameId) {
		this.params = params;
		this.gameId = gameId;
	}

	@Override
	public void execute() {
		
		ServerModel game = Server.models.get(gameId);
		game.robPlayer(params);
		Server.models.put(gameId, game);

	}

}
