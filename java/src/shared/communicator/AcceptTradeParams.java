/**
 * 
 */
package shared.communicator;

/**
 * @author campbeln
 *
 */
public class AcceptTradeParams {

	private int playerIndex;
	private boolean accepted;
	
	public AcceptTradeParams(int playerIndex, boolean accepted) {
		this.playerIndex = playerIndex;
		this.accepted = accepted;
	}

	/**
	 * @return the playerIndex of the player accepting the trade
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
	 * @return the boolean
	 */
	public boolean isAccepted() {
		return accepted;
	}

	/**
	 * @param accepted the boolean to set
	 */
	public void setWillAccept(boolean accepted) {
		this.accepted = accepted;
	}
	
}
