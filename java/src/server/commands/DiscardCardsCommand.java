package server.commands;

import server.Server;
import server.model.ServerModel;
import shared.communicator.BuildSettlementParams;
import shared.communicator.DiscardCardsParams;

/**
 * 
 * @author oxbor
 *
 */
public class DiscardCardsCommand implements Command {

	DiscardCardsParams params;
	int gameId;
	
	public DiscardCardsCommand(DiscardCardsParams params, int gameId) {
		this.params = params;
		this.gameId = gameId;
	}

	@Override
	public void execute() {
		
		ServerModel game = Server.models.get(gameId);
		game.discardCards(params);
		Server.models.put(gameId, game);

	}

}
