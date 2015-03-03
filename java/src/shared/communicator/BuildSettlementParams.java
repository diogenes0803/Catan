/**
 *
 */
package shared.communicator;

import shared.locations.VertexLocation;

/**
 * @author campbeln
 */
public class BuildSettlementParams {

    private String type;
    private int playerIndex;
    private JsonFriendlyVertexLocation vertexLocation;
    private boolean free;

    public BuildSettlementParams(int playerIndex, VertexLocation vertexLocation, boolean free) {
        this.playerIndex = playerIndex;
        this.setVertexLocation(new JsonFriendlyVertexLocation(vertexLocation));
        this.free = free;
        setType("buildSettlement");
    }

    /**
     * @return the playerIndex
     */
    public int getPlayerIndex() {
        return playerIndex;
    }

    /**
     * @param playerIndex the playerIndex to set
     */
    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    /**
     * @return the free
     */
    public boolean isFree() {
        return free;
    }

    /**
     * @param free the free to set
     */
    public void setFree(boolean free) {
        this.free = free;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JsonFriendlyVertexLocation getVertexLocation() {
        return vertexLocation;
    }

    public void setVertexLocation(JsonFriendlyVertexLocation vertexLocation) {
        this.vertexLocation = vertexLocation;
    }

}
