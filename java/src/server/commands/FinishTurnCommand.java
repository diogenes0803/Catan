package server.commands;

import server.Server;
import server.model.ServerModel;
import shared.communicator.BuildSettlementParams;
import shared.communicator.FinishTurnParams;

/**
 * 
 * @author oxbor
 *
 */
public class FinishTurnCommand implements Command {

	FinishTurnParams params;
	int gameId;
	
	public FinishTurnCommand(FinishTurnParams params, int gameId) {
		this.params = params;
		this.gameId = gameId;
	}

	@Override
	public void execute() {
		
		ServerModel game = Server.models.get(gameId);
		game.finishTurn(params);
		Server.models.put(gameId, game);

	}

}
