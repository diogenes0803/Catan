package client.points;

import client.base.IOverlayView;


public interface IGameFinishedView extends IOverlayView
{
	

	void setWinner(String name, boolean isLocalPlayer);
	
}

