package shared.communication;

import shared.locations.VertexLocation;


public class BuildCityParams extends AbstractGameParams{
    public final int playerIndex;
    public final VertexLocation vertexLocation;

    public BuildCityParams(int playerIndex, VertexLocation vertexLocation) {
        this.playerIndex = playerIndex;
        this.vertexLocation = vertexLocation;
    }
}
