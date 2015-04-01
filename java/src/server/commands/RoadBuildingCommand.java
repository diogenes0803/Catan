package server.commands;

import server.Server;
import server.model.ServerModel;
import shared.communicator.BuildSettlementParams;
import shared.communicator.RoadBuildingParams;

/**
 * 
 * @author oxbor
 *
 */
public class RoadBuildingCommand implements Command {

	RoadBuildingParams params;
	int gameId;
	
	public RoadBuildingCommand(RoadBuildingParams params, int gameId) {
		this.params = params;
		this.gameId = gameId;
	}

	@Override
	public void execute() {
		
		ServerModel game = Server.models.get(gameId);
		game.roadBuilding(params);
		Server.models.put(gameId, game);

	}

}
