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

	private int playerIndex;
	private EdgeLocation roadLocation;
	private boolean free;
	
	public BuildRoadParams(int playerIndex, EdgeLocation roadLocation, boolean free) {
		this.playerIndex = playerIndex;
		this.roadLocation = roadLocation;
		this.free = free;
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
	 * @return the roadLocation
	 */
	public EdgeLocation getRoadLocation() {
		return roadLocation;
	}

	/**
	 * @param roadLocation the roadLocation to set
	 */
	public void setRoadLocation(EdgeLocation roadLocation) {
		this.roadLocation = roadLocation;
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
	
}
