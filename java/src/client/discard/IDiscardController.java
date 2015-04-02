package client.discard;

import client.base.*;
import shared.definitions.*;


public interface IDiscardController extends IController
{

	void increaseAmount(ResourceType resource);
	

	void decreaseAmount(ResourceType resource);
	

	void discard();
	
}

