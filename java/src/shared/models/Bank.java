package shared.models;

import java.util.List;
import shared.definitions.ResourceType;

/**
 * @author HojuneYoo
 * Bank holds all resource cards and development cards that are not belongs to any players
 * When user wants to build something then they have to pay their resources or development cards to the bank
 * Bank also can be used when user wants to trade resources
 */

public class Bank {
	
	private List<ResCard> resCards;
	private List<DevCard> devCards;

	/**
	 * Distribute Resource Cards to users who are qualified 
	 * @pre Dices needs to be thrown on that turn before bank can distribute a card
	 * @post Remove resource cards that has been distributed
	 * @param diceSum
	 */
	public void distributeCards(int diceSum){
		return;
	}
	
	/**
	 * Trade resource cards with a user. User must pay 4 of same resources to trade 1 of anything that user wants.
	 * @pre User must have at least 4 resources of same kind
	 * @post User pay 4 resource cards of same kind and take 1 of any resource card from bank
	 * @param playerId
	 * @param userPay
	 * @param userWants
	 */
	public void trade(int playerId, ResourceType userPay, ResourceType userWants){
		return;
	}

	public List<ResCard> getResCards() {
		return resCards;
	}

	public void setResCards(List<ResCard> resCards) {
		this.resCards = resCards;
	}

	public List<DevCard> getDevCards() {
		return devCards;
	}

	public void setDevCards(List<DevCard> devCards) {
		this.devCards = devCards;
	}
	
	

	
	
}
