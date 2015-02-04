/**
 * 
 */
package shared.communicator;

/**
 * @author campbeln
 *
 */
public class MonumentParams {

	private int playerIndex;
	
	public MonumentParams(int playerIndex) {
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
