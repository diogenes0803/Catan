package server.commands;

import server.Server;
import server.model.ServerModel;
import shared.communicator.BuildSettlementParams;
import shared.communicator.MonopolyParams;

/**
 * 
 * @author oxbor
 *
 */
public class MonopolyCommand implements Command {

	MonopolyParams params;
	int gameId;
	
	public MonopolyCommand(MonopolyParams params, int gameId) {
		this.params = params;
		this.gameId = gameId;
	}

	@Override
	public void execute() {
		
		ServerModel game = Server.models.get(gameId);
		game.monopoly(params);
		Server.models.put(gameId, game);


	}

}
