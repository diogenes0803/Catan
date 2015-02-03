/**
 * 
 */
package shared.communicator;

/**
 * @author campbeln
 *
 */
public class BuyDevCardParams {

	private int playerIndex;
	
	public BuyDevCardParams(int playerIndex) {
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
