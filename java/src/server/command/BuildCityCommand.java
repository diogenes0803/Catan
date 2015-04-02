package server.command;

import shared.locations.VertexLocation;
import shared.model.IGame;
import shared.model.IPlayer;
import shared.model.ITown;
import shared.model.Prices;


public class BuildCityCommand extends AbstractCommand {
    private VertexLocation local_location;

    public BuildCityCommand(IGame game, IPlayer player, VertexLocation location) throws IllegalCommandException {
        super(game, player, "built a city");

        if (!getGame().canBuildCity(player, location)) {
            throw new IllegalCommandException("Player " + player.getName() + " cannot build a city at " + location);
        }

        local_location = location;
    }


    public void performAction() {
        ITown replacing = getGame().getMap().getTownAt(local_location);
        getGame().getMap().placeCity(getPlayer().buildCity(replacing), local_location);
        getGame().getResourceBank().add(Prices.CITY); // the player gave these resources to the bank

        getGame().calculateVictoryPoints();

        assert (getGame().verifyResourceAmount());
    }
}
