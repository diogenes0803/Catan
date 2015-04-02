package server.command;

import shared.definitions.DevCardType;
import shared.model.IGame;
import shared.model.IPlayer;

import java.util.logging.Logger;


public class MonumentCommand extends AbstractCommand {

    private final static Logger logger = Logger.getLogger("catan");

    public MonumentCommand(IGame game, IPlayer player) throws IllegalCommandException {

        super(game, player, "played a monument card");

        if (!getGame().canPlayMonument(getPlayer())) {
            throw new IllegalCommandException("Player " + player.getName() + " attempted to play monument card, but cannot do that");
        }
    }


    public void performAction() {
        // Remove dev card from player's hand
        getPlayer().playDevCard(DevCardType.MONUMENT);

        // Increment player's Monuments
        getPlayer().incrementMonuments();

        // playing a monument DOES NOT count as playing a dev card

        getGame().calculateVictoryPoints();
    }
}
