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
	private boolean willAccept;
	private String type;
	
	public AcceptTradeParams(int playerIndex, boolean willAccept) {
		this.playerIndex = playerIndex;
		this.willAccept = willAccept;
		setType("acceptTrade");
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
	public boolean willAccept() {
		return willAccept;
	}

	/**
	 * @param willAccept the boolean to set
	 */
	public void setWillAccept(boolean willAccept) {
		this.willAccept = willAccept;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
