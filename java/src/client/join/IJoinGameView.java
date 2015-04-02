package client.join;

import client.base.IOverlayView;
import client.data.GameInfo;
import client.data.PlayerInfo;


public interface IJoinGameView extends IOverlayView
{
	

	void setGames(GameInfo[] games, PlayerInfo localPlayer);
	
}

