/**
 * 
 */
package shared.communicator;

/**
 * @author campbeln
 *
 */
public class RollNumberParams {
	
	private int playerIndex;
	private int number;
	private String type;
	
	public RollNumberParams(int playerIndex, int number) {
		this.playerIndex = playerIndex;
		this.number = number;
		setType("rollNumber");
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
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
