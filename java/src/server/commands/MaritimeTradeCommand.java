package server.commands;

import server.Server;
import server.model.ServerModel;
import shared.communicator.BuildSettlementParams;
import shared.communicator.MaritimeTradeParams;

/**
 * 
 * @author oxbor
 *
 */
public class MaritimeTradeCommand implements Command {

	MaritimeTradeParams params;
	int gameId;
	
	public MaritimeTradeCommand(MaritimeTradeParams params, int gameId) {
		this.params = params;
		this.gameId = gameId;
	}

	@Override
	public void execute() {
		
		ServerModel game = Server.models.get(gameId);
		game.maritimeTrade(params);
		Server.models.put(gameId, game);

	}

}
