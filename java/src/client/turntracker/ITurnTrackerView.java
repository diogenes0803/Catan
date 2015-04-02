package client.turntracker;

import client.base.*;
import shared.definitions.*;


public interface ITurnTrackerView extends IView
{
	

	void setLocalPlayerColor(CatanColor value);
	

	void initializePlayer(int playerIndex, String playerName,
						  CatanColor playerColor);
	

	void updatePlayer(int playerIndex, int points, boolean highlight,
					  boolean largestArmy, boolean longestRoad, CatanColor playerColor);
	

	void updateGameState(String stateMessage, boolean enable);
}

