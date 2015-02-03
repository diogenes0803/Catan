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
	private int numberRolled;
	
	public RollNumberParams(int playerIndex, int numberRolled) {
		this.playerIndex = playerIndex;
		this.numberRolled = numberRolled;
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
	 * @return the numberRolled
	 */
	public int getNumberRolled() {
		return numberRolled;
	}

	/**
	 * @param numberRolled the numberRolled to set
	 */
	public void setNumberRolled(int numberRolled) {
		this.numberRolled = numberRolled;
	}
}
