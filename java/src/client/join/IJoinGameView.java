package client.join;

import client.base.*;
import client.data.*;


public interface IJoinGameView extends IOverlayView
{
	

	void setGames(GameInfo[] games, PlayerInfo localPlayer);
	
}

