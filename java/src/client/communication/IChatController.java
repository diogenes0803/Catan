package client.communication;

import client.base.IController;


public interface IChatController extends IController
{
	

	void sendMessage(String message);
}

