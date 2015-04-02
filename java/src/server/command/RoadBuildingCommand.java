package server.command;

import shared.definitions.DevCardType;
import shared.locations.EdgeLocation;
import shared.model.IGame;
import shared.model.IPlayer;


public class RoadBuildingCommand extends AbstractCommand {

    private EdgeLocation local_location1;
    private EdgeLocation local_location2;

    public RoadBuildingCommand(IGame game, IPlayer player, EdgeLocation edge1, EdgeLocation edge2) throws IllegalCommandException {
        super(game, player, "played a road building card");

        if (!getGame().canPlayRoadBuilding(getPlayer(), edge1, edge2)) {
            throw new IllegalCommandException("Player " + player.getName() + " cannot build roads at " + edge1 + " and " + edge2);
        }

        local_location1 = edge1;
        local_location2 = edge2;
    }


    public void performAction() {
        getPlayer().playDevCard(DevCardType.ROAD_BUILD);

        getGame().getMap().placeRoad(getPlayer().buildRoad(true), local_location1);
        getGame().getMap().placeRoad(getPlayer().buildRoad(true), local_location2);

        getGame().calculateVictoryPoints();
    }
}
