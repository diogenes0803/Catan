package shared.communication;

import shared.locations.EdgeLocation;


public class BuildRoadParams extends AbstractGameParams{
    public final int playerIndex;
    public final EdgeLocation roadLocation;
    public final boolean free;

    public BuildRoadParams(int playerIndex, EdgeLocation roadLocation, boolean free) {
        this.playerIndex = playerIndex;
        this.roadLocation = roadLocation;
        this.free = free;
    }
}
