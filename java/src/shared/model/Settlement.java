package shared.model;

import shared.locations.VertexLocation;


public class Settlement extends Town {


    public Settlement(IPlayer owner) {
        super(owner, null);
    }


    public Settlement(IPlayer owner, VertexLocation location) {
        super(owner, location);
    }

    @Override
    public int getVictoryPoints() {
        return 1;
    }

    @Override
    public int getResourceCount() {
        return 1;
    }

}
