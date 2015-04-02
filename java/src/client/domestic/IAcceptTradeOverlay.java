package client.domestic;

import client.base.*;
import shared.definitions.*;


public interface IAcceptTradeOverlay extends IOverlayView
{
	

	void addGetResource(ResourceType resource, int amount);
	

	void addGiveResource(ResourceType resource, int amount);
	

	void setAcceptEnabled(boolean enable);
	

	void setPlayerName(String name);
	

	void reset();
}

