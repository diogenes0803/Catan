package server.command;

import shared.model.IGame;
import shared.model.IPlayer;


public class SendChatCommand extends AbstractCommand {

    private String local_message;

    public SendChatCommand(IGame game, IPlayer player, String message) throws IllegalCommandException {
        super(game, player);

        local_message = message;
    }


    public void performAction() {
        getGame().getChatHistory().addMessage(getPlayer(), local_message);
    }
}
