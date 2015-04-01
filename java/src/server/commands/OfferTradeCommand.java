package server.commands;

import server.Server;
import server.model.ServerModel;
import shared.communicator.BuildSettlementParams;
import shared.communicator.OfferTradeParams;

/**
 * 
 * @author oxbor
 *
 */
public class OfferTradeCommand implements Command {

	OfferTradeParams params;
	int gameId;
	
	public OfferTradeCommand(OfferTradeParams params, int gameId) {
		this.params = params;
		this.gameId = gameId;
	}

	@Override
	public void execute() {
		
		ServerModel game = Server.models.get(gameId);
		game.offerTrade(params);
		Server.models.put(gameId, game);

	}

}
