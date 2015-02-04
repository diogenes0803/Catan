/**
 * 
 */
package shared.communicator;

/**
 * @author campbeln
 *
 */
public class FinishTurnParams {

	private int playerIndex;
	
	public FinishTurnParams (int playerIndex) {
		this.playerIndex = playerIndex;
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
}
