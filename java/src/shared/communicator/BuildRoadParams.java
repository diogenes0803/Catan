/**
 * 
 */
package shared.communicator;

import shared.locations.EdgeLocation;

/**
 * @author campbeln
 *
 */
public class BuildRoadParams {

	private String type;
	private int playerIndex;
	private JsonFriendlyEdgeLocation roadLocation;
	private boolean free;
	
	public BuildRoadParams(int playerIndex, EdgeLocation roadLocation, boolean free) {
		this.playerIndex = playerIndex;
		this.setRoadLocation(new JsonFriendlyEdgeLocation(roadLocation));
		this.free = free;
		setType("buildRoad");
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

	public JsonFriendlyEdgeLocation getRoadLocation() {
		return roadLocation;
	}

	public void setRoadLocation(JsonFriendlyEdgeLocation roadLocation) {
		this.roadLocation = roadLocation;
	}
	
}
