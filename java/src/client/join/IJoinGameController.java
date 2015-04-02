package client.join;

import client.base.IController;
import client.data.GameInfo;
import shared.definitions.CatanColor;


public interface IJoinGameController extends IController
{
	

	void start();
	

	void startCreateNewGame();
	

	void cancelCreateNewGame();
	

	void createNewGame();
	

	void startJoinGame(GameInfo game);
	

	void cancelJoinGame();
	

	void joinGame(CatanColor color);
	
}

