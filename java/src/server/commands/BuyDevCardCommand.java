package server.commands;

import server.Server;
import server.model.ServerModel;
import shared.communicator.BuildSettlementParams;
import shared.communicator.BuyDevCardParams;

/**
 * 
 * @author oxbor
 *
 */
public class BuyDevCardCommand implements Command {

	BuyDevCardParams params;
	int gameId;
	
	public BuyDevCardCommand(BuyDevCardParams params, int gameId) {
		this.params = params;
		this.gameId = gameId;
	}

	@Override
	public void execute() {
		
		ServerModel game = Server.models.get(gameId);
		game.buyDevCard(params);
		Server.models.put(gameId, game);

	}

}
