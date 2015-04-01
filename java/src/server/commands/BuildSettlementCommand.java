package server.commands;

import server.Server;
import server.model.ServerModel;
import shared.communicator.BuildSettlementParams;

/**
 * 
 * @author oxbor
 *
 */
public class BuildSettlementCommand implements Command {

	BuildSettlementParams params;
	int gameId;
	
	public BuildSettlementCommand(BuildSettlementParams params, int gameId) {
		this.params = params;
		this.gameId = gameId;
	}
	@Override
	public void execute() {
		
		ServerModel game = Server.models.get(gameId);
		game.buildSettlement(params);
		Server.models.put(gameId, game);

	}

}
