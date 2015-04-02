package client.domestic;

import client.base.*;
import shared.definitions.*;


public interface IDomesticTradeController extends IController
{
	

	void startTrade();
	

	void decreaseResourceAmount(ResourceType resource);
	

	void increaseResourceAmount(ResourceType resource);
	

	void sendTradeOffer();
	

	void setPlayerToTradeWith(int playerIndex);
	

	void setResourceToReceive(ResourceType resource);
	

	void setResourceToSend(ResourceType resource);
	

	void unsetResource(ResourceType resource);
	

	void cancelTrade();
	

	void acceptTrade(boolean willAccept);
}

