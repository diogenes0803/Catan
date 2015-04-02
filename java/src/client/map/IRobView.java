package client.map;

import client.base.*;
import client.data.*;


public interface IRobView extends IOverlayView
{
	
	void setPlayers(RobPlayerInfo[] candidateVictims);
}

