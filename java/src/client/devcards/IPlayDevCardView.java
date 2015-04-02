package client.devcards;

import client.base.*;
import shared.definitions.*;


public interface IPlayDevCardView extends IOverlayView
{
	

	void reset();
	

	void setCardEnabled(DevCardType cardType, boolean enabled);
	

	void setCardAmount(DevCardType cardType, int amount);
}

