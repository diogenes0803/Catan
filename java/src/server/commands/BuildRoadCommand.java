package server.commands;

import server.Server;
import server.model.ServerModel;
import shared.communicator.BuildRoadParams;

/**
 * 
 * @author oxbor
 *
 */
public class BuildRoadCommand implements Command {

	BuildRoadParams params;
	int gameId;
	
	public BuildRoadCommand(BuildRoadParams params, int gameId) {
		this.params = params;
		this.gameId = gameId;
	}

	@Override
	public void execute() {
		
		ServerModel game = Server.models.get(gameId);
		game.buildRoad(params);
		Server.models.put(gameId, game);
	}

}
