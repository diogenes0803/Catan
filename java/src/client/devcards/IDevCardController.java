package client.devcards;

import client.base.IController;
import shared.definitions.ResourceType;


public interface IDevCardController extends IController
{
	

	void startBuyCard();
	

	void cancelBuyCard();
	

	void buyCard();
	

	void startPlayCard();
	

	void cancelPlayCard();
	

	void playMonopolyCard(ResourceType resource);
	

	void playMonumentCard();
	

	void playRoadBuildCard();
	

	void playSoldierCard();
	

	void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2);
}

