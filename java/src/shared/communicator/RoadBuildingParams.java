/**
 * 
 */
package shared.communicator;

import shared.locations.EdgeLocation;

/**
 * @author campbeln
 *
 */
public class RoadBuildingParams {

	private int playerIndex;
	private EdgeLocation spot1;
	private EdgeLocation spot2;
	
	public RoadBuildingParams(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) {
		this.playerIndex = playerIndex;
		this.spot1 = spot1;
		this.spot2 = spot2;
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
	 * @return the spot1
	 */
	public EdgeLocation getSpot1() {
		return spot1;
	}

	/**
	 * @param spot1 the spot1 to set
	 */
	public void setSpot1(EdgeLocation spot1) {
		this.spot1 = spot1;
	}

	/**
	 * @return the spot2
	 */
	public EdgeLocation getSpot2() {
		return spot2;
	}

	/**
	 * @param spot2 the spot2 to set
	 */
	public void setSpot2(EdgeLocation spot2) {
		this.spot2 = spot2;
	}
	
}
