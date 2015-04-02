package server.command;

import shared.locations.VertexLocation;
import shared.model.*;


public class BuildSettlementCommand extends AbstractCommand {
    private VertexLocation local_location;
    private boolean local_free;

    public BuildSettlementCommand(IGame game, IPlayer player, VertexLocation location, boolean free) throws IllegalCommandException {
        super(game, player, "built a settlement");

        if (!getGame().canPlaceSettlement(player, location)) {
            throw new IllegalCommandException(player.getName() + " cannot build a settlement at " + location);
        }
        else if (free && !getGame().isFreeRound()) {
            throw new IllegalCommandException(player.getName() + " tried build a settlement without paying for it! (Cheater!)");
        }
        else if (!free && !getPlayer().canBuySettlement()) {
            throw new IllegalCommandException(player.getName() + " can't buy a settlement.");
        }

        local_location = location;
        local_free = free;
    }


    protected void performAction() {
        Settlement settlement = getPlayer().buildSettlement(local_free);
        getGame().getMap().placeSettlement(settlement, local_location);

        if (!local_free) getGame().getResourceBank().add(Prices.SETTLEMENT); // player gave resources to bank

        getGame().calculateVictoryPoints();

        if (getGame().getGameState() == GameState.SecondRound) {
            assert local_free : "SecondRound, so settlement should be free";
            getGame().getMap().distributeInitialResources(settlement, getGame().getResourceBank());
        }

        assert (getGame().verifyResourceAmount());
    }
}
