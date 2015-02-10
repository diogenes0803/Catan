/**
 * 
 */
package shared.communicator;

import shared.locations.VertexLocation;

/**
 * @author campbeln
 *
 */
public class BuildSettlementParams {

	private int playerIndex;
	private VertexLocation vertexLocation;
	private boolean free;
	private String type;
	
	public BuildSettlementParams(int playerIndex, VertexLocation vertexLocation, boolean free) {
		this.playerIndex = playerIndex;
		this.vertexLocation = vertexLocation;
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
	 * @return the vertexLocation
	 */
	public VertexLocation getVertexLocation() {
		return vertexLocation;
	}

	/**
	 * @param vertexLocation the vertexLocation to set
	 */
	public void setVertexLocation(VertexLocation vertexLocation) {
		this.vertexLocation = vertexLocation;
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
	
}
