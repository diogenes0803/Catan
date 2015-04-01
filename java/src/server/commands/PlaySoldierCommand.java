package server.commands;

import server.Server;
import server.model.ServerModel;
import shared.communicator.BuildSettlementParams;
import shared.communicator.PlaySoldierParams;

/**
 * 
 * @author oxbor
 *
 */
public class PlaySoldierCommand implements Command {

	PlaySoldierParams params;
	int gameId;
	
	public PlaySoldierCommand(PlaySoldierParams params, int gameId) {
		this.params = params;
		this.gameId = gameId;
	}

	@Override
	public void execute() {
		
		ServerModel game = Server.models.get(gameId);
		game.soldier(params);
		Server.models.put(gameId, game);

	}

}
