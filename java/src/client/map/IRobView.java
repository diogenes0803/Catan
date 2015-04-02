package client.map;

import client.base.IOverlayView;
import client.data.RobPlayerInfo;


public interface IRobView extends IOverlayView
{
	
	void setPlayers(RobPlayerInfo[] candidateVictims);
}

