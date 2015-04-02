package shared.model;

import shared.locations.VertexLocation;


public class City extends Town {


    public City(IPlayer owner) {
        super(owner, null);
    }


    public City(IPlayer owner, VertexLocation location) {
        super(owner, location);
    }

    @Override
    public int getVictoryPoints() {
        return 2;
    }

    @Override
    public int getResourceCount() {
        return 2;
    }
}
