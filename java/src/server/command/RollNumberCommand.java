package server.command;

import shared.model.GameState;
import shared.model.IGame;
import shared.model.IPlayer;


public class RollNumberCommand extends AbstractCommand {
    private int local_number;

    public RollNumberCommand(IGame game, IPlayer player, int number) throws IllegalCommandException{
        super(game, player, "rolled a " + number);
        local_number = number;

        if(!getGame().isPlayersTurn(player) || getGame().getGameState() != GameState.Rolling) {
            throw new IllegalCommandException("Player " + getPlayer().getName() + " cannot roll right now.");
        }
        else if (number < 2 || number > 12) {
            throw new IllegalCommandException("Illegal roll: " + number);
        }
    }


    public void performAction() {
        getGame().rollNumber(local_number);

        assert getGame().verifyResourceAmount();
    }
}
