package shared.models;

import java.util.List;

import shared.definitions.ResourceType;

public class Bank {
	
	private List<ResCard> resCards;
	private List<DevCard> devCards;
	
	/*
	 * 
	 */
	public void distributeCards(int diceSum){
		return;
	}
	
	/*
	 * 
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
