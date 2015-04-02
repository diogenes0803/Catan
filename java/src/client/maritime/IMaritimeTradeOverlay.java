package client.maritime;

import client.base.*;
import shared.definitions.*;


public interface IMaritimeTradeOverlay extends IOverlayView
{
	

	void reset();
	

	void hideGetOptions();
	

	void hideGiveOptions();
	

	void selectGetOption(ResourceType selectedResource, int amount);
	

	void selectGiveOption(ResourceType selectedResource, int amount);
	

	void setStateMessage(String message);
	

	void setTradeEnabled(boolean enable);
	

	void setCancelEnabled(boolean enabled);
	

	void showGetOptions(ResourceType[] enabledResources);
	

	void showGiveOptions(ResourceType[] enabledResources);
	
}

