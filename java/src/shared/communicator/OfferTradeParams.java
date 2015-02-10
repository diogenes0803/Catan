/**
 * 
 */
package shared.communicator;

import shared.models.ResourceList;

/**
 * @author campbeln
 *
 */
public class OfferTradeParams {

	private int playerIndex;
	private ResourceList offer;
	private int receiver;
	private String type;
	
	public OfferTradeParams(int playerIndex, ResourceList offer, int receiver) {
		this.playerIndex = playerIndex;
		this.offer = offer;
		this.receiver = receiver;
		setType("offerTrade");
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
	 * @return the offer
	 */
	public ResourceList getOffer() {
		return offer;
	}

	/**
	 * @param offer the offer to set
	 */
	public void setOffer(ResourceList offer) {
		this.offer = offer;
	}

	/**
	 * @return the receiver
	 */
	public int getReceiver() {
		return receiver;
	}

	/**
	 * @param receiver the receiver to set
	 */
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
