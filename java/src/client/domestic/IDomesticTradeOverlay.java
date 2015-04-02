package client.domestic;

import client.base.IOverlayView;
import client.data.PlayerInfo;
import shared.definitions.ResourceType;


public interface IDomesticTradeOverlay extends IOverlayView
{
	

	void reset();
	

	void setPlayers(PlayerInfo[] value);
	

	void setPlayerSelectionEnabled(boolean enable);
	

	void setResourceAmount(ResourceType resource, int amount);
	

	void setResourceAmountChangeEnabled(ResourceType resource,
										   boolean canIncrease,
										   boolean canDecrease);
	

	void setResourceSelectionEnabled(boolean enable);
	

	void setStateMessage(String message);
	

	void setTradeEnabled(boolean enable);
	

	void setCancelEnabled(boolean enabled);
	
}

