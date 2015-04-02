package client.join;

import client.base.*;
import client.data.*;
import shared.definitions.*;


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

