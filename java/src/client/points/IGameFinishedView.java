package client.points;

import client.base.*;


public interface IGameFinishedView extends IOverlayView
{
	

	void setWinner(String name, boolean isLocalPlayer);
	
}

