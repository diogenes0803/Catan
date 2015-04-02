package server.command;

import shared.locations.EdgeLocation;
import shared.model.IGame;
import shared.model.IPlayer;
import shared.model.Prices;


public class BuildRoadCommand extends AbstractCommand {
    private EdgeLocation local_location;
    private boolean local_free;

    public BuildRoadCommand(IGame game, IPlayer player, EdgeLocation location, boolean free) throws IllegalCommandException {
        super(game, player, "built a road");

        assert location != null;

        if (!getGame().canPlaceRoad(player, location)) {
            throw new IllegalCommandException(player.getName() + " cannot build a road at " + location);
        }
        else if (free && !getGame().isFreeRound()) {
            throw new IllegalCommandException(player.getName() + " tried build a road without paying for it. (Cheater!)");
        }
        else if (!free && !getPlayer().canBuyRoad()) {
            throw new IllegalCommandException(player.getName() + " can't buy a road.");
        }

        local_location = location;
        local_free = free;
    }


    public void performAction() {
        getGame().getMap().placeRoad(getPlayer().buildRoad(local_free), local_location);

        if (!local_free) getGame().getResourceBank().add(Prices.ROAD); // player gave resources to bank

        getGame().calculateVictoryPoints();

        assert (getGame().verifyResourceAmount());
    }
}
