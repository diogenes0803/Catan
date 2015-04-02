package server.command;

import shared.model.IGame;
import shared.model.IPlayer;


public class FinishTurnCommand extends AbstractCommand {

    public FinishTurnCommand(IGame game, IPlayer player) throws IllegalCommandException {
        super(game, player, "ended their turn");

        if (!game.isPlaying(player) && !game.isPlacingInitialPieces(player)) {
            throw new IllegalCommandException("Player tried to end their turn illegally.");
        }
    }


    public void performAction() {
        assert getGame().isPlaying(getPlayer()) || getGame().isPlacingInitialPieces(getPlayer());
        // call finish turn on the game model
        getGame().finishTurn();
    }
}
