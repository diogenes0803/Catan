package server.commands;

import server.Server;
import server.model.ServerModel;
import shared.communicator.BuildCityParams;
import shared.communicator.BuildSettlementParams;

/**
 * 
 * @author oxbor
 *
 */
public class BuildCityCommand implements Command {

	BuildCityParams params;
	int gameId;
	
	public BuildCityCommand(BuildCityParams params, int gameId) {
		this.params = params;
		this.gameId = gameId;
	}

	@Override
	public void execute() {
		
		ServerModel game = Server.models.get(gameId);
		game.buildCity(params);
		Server.models.put(gameId, game);
	}

}
