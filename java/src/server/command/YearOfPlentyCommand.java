package server.command;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.model.IGame;
import shared.model.IPlayer;


public class YearOfPlentyCommand extends AbstractCommand {

    private ResourceType local_firstType;
    private ResourceType local_secondType;

    public YearOfPlentyCommand(IGame game, IPlayer player, ResourceType firstType, ResourceType secondType) throws IllegalCommandException {
        super(game, player, "played a year of plenty card");

        if (!getGame().canPlayYearOfPlenty(getPlayer(), firstType, secondType)) {
            throw new IllegalCommandException("Player" + player.getName() + " attempted to play YearOfPlenty DevCard, requesting " + firstType.name() + " and "
                    + secondType.name() + " but that move is not allowed");
        }

        local_firstType = firstType;
        local_secondType = secondType;
    }


    public void performAction() {
        getPlayer().playDevCard(DevCardType.YEAR_OF_PLENTY);

        // Remove the resource cards from the game's resource bank
        getGame().getResourceBank().subtract(1, local_firstType);
        getGame().getResourceBank().subtract(1, local_secondType);

        // Add the resource cards to the player's hand
        getPlayer().addResources(1, local_firstType);
        getPlayer().addResources(1, local_secondType);

        assert (getGame().verifyResourceAmount());
    }
}
