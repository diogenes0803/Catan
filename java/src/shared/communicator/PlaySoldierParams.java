/**
 * 
 */
package shared.communicator;

import shared.locations.HexLocation;

/**
 * @author campbeln
 *
 */
public class PlaySoldierParams {

	private int playerIndex;
	private int victimIndex;
	private HexLocation location;
	private String type;
	
	public PlaySoldierParams(int playerIndex, int victimIndex, HexLocation location) {
		this.playerIndex = playerIndex;
		this.victimIndex = victimIndex;
		this.location = location;
		setType("Soldier");
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
	 * @return the victimIndex
	 */
	public int getVictimIndex() {
		return victimIndex;
	}

	/**
	 * @param victimIndex the victimIndex to set
	 */
	public void setVictimIndex(int victimIndex) {
		this.victimIndex = victimIndex;
	}

	/**
	 * @return the location
	 */
	public HexLocation getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(HexLocation location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
