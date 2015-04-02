package server.command;

import shared.definitions.CatanColor;
import shared.model.IGame;
import shared.model.IUser;
import shared.model.Player;


public class JoinGameCommand extends AbstractCommand {
    IUser user;
    CatanColor color;

    public JoinGameCommand(IGame game, IUser user, CatanColor color) throws IllegalCommandException {
        super(game, Player.NULL_PLAYER);

        if (!game.canJoinGame(user, color)) {
            throw new IllegalCommandException(user.getUsername() + " cannot join game " + getGame().getID() + ".");
        }

        this.user = user;
        this.color = color;
    }


    public void performAction() {
        assert getGame().canJoinGame(user, color);
        getGame().joinGame(user, color);
    }
}
