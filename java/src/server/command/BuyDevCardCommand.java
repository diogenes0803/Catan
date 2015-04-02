package server.command;

import shared.definitions.DevCardType;
import shared.model.IGame;
import shared.model.IPlayer;
import shared.model.Prices;


public class BuyDevCardCommand extends AbstractCommand {

    public BuyDevCardCommand(IGame game, IPlayer player) throws IllegalCommandException {
        super(game, player, "bought a development card");

        if (!getGame().canBuyDevCard(getPlayer())) {
            throw new IllegalCommandException("Player " + player.getName() + " tried to buy a DevCard illegally!");
        }
    }


    public void performAction() {
        // Randomly pick a dev card from the dev card hand
        DevCardType devCard = getGame().getDevCards().drawCard(getGame().getRandom());

        // Put it in the player's newDevCards hand unless it is a monument, in which case it goes straight to playable
        if (devCard == DevCardType.MONUMENT) {
            getPlayer().getPlayableDevCards().add(devCard);
        }
        else {
            getPlayer().getNewDevCards().add(devCard);
        }

        // Remove resources from the player
        getPlayer().removeResources(Prices.DEV_CARD);

        // Put removed resources in the Game ResourceBank
        getGame().getResourceBank().add(Prices.DEV_CARD);

        assert (getGame().verifyResourceAmount()) : "Incorrect resources!";
    }
}
