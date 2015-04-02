package client.domestic;

import client.base.IOverlayView;
import shared.definitions.ResourceType;


public interface IAcceptTradeOverlay extends IOverlayView
{
	

	void addGetResource(ResourceType resource, int amount);
	

	void addGiveResource(ResourceType resource, int amount);
	

	void setAcceptEnabled(boolean enable);
	

	void setPlayerName(String name);
	

	void reset();
}

