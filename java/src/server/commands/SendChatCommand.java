package server.commands;

import server.Server;
import server.model.ServerModel;
import shared.communicator.BuildSettlementParams;
import shared.communicator.SendChatParams;

/**
 * 
 * @author oxbor
 *
 */
public class SendChatCommand implements Command {

	SendChatParams params;
	int gameId;
	
	public SendChatCommand(SendChatParams params, int gameId) {
		this.params = params;
		this.gameId = gameId;
	}

	@Override
	public void execute() {
		
		ServerModel game = Server.models.get(gameId);
		game.sendChat(params);
		Server.models.put(gameId, game);

	}

}
