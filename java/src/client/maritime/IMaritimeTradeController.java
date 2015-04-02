package client.maritime;

import client.base.*;
import shared.definitions.*;


public interface IMaritimeTradeController extends IController
{
	

	void startTrade();
	

	void makeTrade();
	

	void cancelTrade();
	

	void setGetResource(ResourceType resource);
	

	void setGiveResource(ResourceType resource);
	

	void unsetGetValue();
	

	void unsetGiveValue();
	
}

