package client.resources;

import client.base.*;


public interface IResourceBarView extends IView
{
	

	void setElementEnabled(ResourceBarElement element, boolean enabled);
	

	void setElementAmount(ResourceBarElement element, int amount);
}

