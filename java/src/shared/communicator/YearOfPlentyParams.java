/**
 * 
 */
package shared.communicator;

import shared.definitions.ResourceType;

/**
 * @author campbeln
 *
 */
public class YearOfPlentyParams {

	private int playerIndex;
	private ResourceType resource1;
	private ResourceType resource2;
	private String type;
	
	public YearOfPlentyParams(int playerIndex, ResourceType resource1, ResourceType resource2) {
		this.playerIndex = playerIndex;
		this.resource1 = resource1;
		this.resource2 = resource2;
		setType("Year_of_Plenty");
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
	 * @return the resource1
	 */
	public ResourceType getResource1() {
		return resource1;
	}

	/**
	 * @param resource1 the resource1 to set
	 */
	public void setResource1(ResourceType resource1) {
		this.resource1 = resource1;
	}

	/**
	 * @return the resource2
	 */
	public ResourceType getResource2() {
		return resource2;
	}

	/**
	 * @param resource2 the resource2 to set
	 */
	public void setResource2(ResourceType resource2) {
		this.resource2 = resource2;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
