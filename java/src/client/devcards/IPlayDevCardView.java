package client.devcards;

import client.base.IOverlayView;
import shared.definitions.DevCardType;


public interface IPlayDevCardView extends IOverlayView
{
	

	void reset();
	

	void setCardEnabled(DevCardType cardType, boolean enabled);
	

	void setCardAmount(DevCardType cardType, int amount);
}

