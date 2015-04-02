package client.discard;

import client.base.IController;
import shared.definitions.ResourceType;


public interface IDiscardController extends IController
{

	void increaseAmount(ResourceType resource);
	

	void decreaseAmount(ResourceType resource);
	

	void discard();
	
}

