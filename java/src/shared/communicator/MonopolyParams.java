/**
 * 
 */
package shared.communicator;

/**
 * @author campbeln
 *
 */
public class MonopolyParams {

	private int playerIndex;
	private String resource;
	private String type;
	
	public MonopolyParams(int playerIndex, String resource) {
		this.playerIndex = playerIndex;
		this.resource = resource;
		setType("Monopoly");
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
	 * @return the resource
	 */
	public String getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
