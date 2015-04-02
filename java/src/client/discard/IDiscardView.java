package client.discard;

import client.base.*;
import shared.definitions.*;


public interface IDiscardView extends IOverlayView
{
	

	void setDiscardButtonEnabled(boolean enabled);
	

	void setResourceDiscardAmount(ResourceType resource, int amount);
	

	void setResourceMaxAmount(ResourceType resource, int maxAmount);
	

	void setResourceAmountChangeEnabled(ResourceType resource,
										boolean increase, boolean decrease);
	

	void setStateMessage(String message);
}

