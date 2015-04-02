package client.join;

import client.base.*;
import client.data.*;


public interface IPlayerWaitingView extends IOverlayView
{
	

	void setPlayers(PlayerInfo[] value);
	

	void setAIChoices(String[] value);
	

	String getSelectedAI();


    void refresh();
}

