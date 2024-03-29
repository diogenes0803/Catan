/**
 * 
 */
package shared.communicator;

/**
 * @author campbeln
 *
 */
public class FinishTurnParams {

	private String type;
	private int playerIndex;
	
	public FinishTurnParams (int playerIndex) {
		this.playerIndex = playerIndex;
		setType("finishTurn");
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
