package server.commands;

import server.Server;
import server.model.ServerModel;
import shared.communicator.BuildSettlementParams;
import shared.communicator.RollNumberParams;

/**
 * 
 * @author oxbor
 *
 */
public class RollNumberCommand implements Command {

	RollNumberParams params;
	int gameId;
	
	public RollNumberCommand(RollNumberParams params, int gameId) {
		this.params = params;
		this.gameId = gameId;
	}

	@Override
	public void execute() {
		
		ServerModel game = Server.models.get(gameId);
		game.rollNumber(params);
		Server.models.put(gameId, game);

	}

}
