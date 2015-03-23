/**
 * 
 */
package server.facades;

import server.Server;
import server.model.ServerModel;
import shared.communicator.CreateGameParams;
import shared.communicator.CreateGameResults;
import shared.communicator.JoinGameParams;
import shared.communicator.JoinGameResults;
import shared.communicator.ListGamesResults;
import shared.communicator.LoadGameParams;
import shared.communicator.LoadGameResults;
import shared.communicator.SaveGameParams;
import shared.communicator.SaveGameResults;
import shared.data.GameInfo;
import shared.data.PlayerInfo;

/**
 * @author campbeln
 *
 */
public class GamesFacade implements Facade {

	public GamesFacade(){}
	
	public ListGamesResults list() {
		int gamesSize = Server.models.size();
		GameInfo[] games = new GameInfo[1];
		//GameInfo[] games = new GameInfo[gameSize];
		GameInfo testGame = new GameInfo();
		testGame.setTitle("test");
		testGame.setId(0);
		PlayerInfo testPlayer = new PlayerInfo();
		testPlayer.setColor("orange");
		testPlayer.setId(0);
		testPlayer.setName("Jack");
		testPlayer.setPlayerIndex(0);
		testGame.addPlayer(new PlayerInfo());
		games[0] = testGame;
		if(Server.models.size() > 0) {
			ServerModel[] models = (ServerModel[])Server.models.values().toArray();
			for(int i = 0; i < models.length; i++) {
				games[i] = models[i].toGameInfo();
			}
		}
		ListGamesResults results = new ListGamesResults();
		results.setGames(games);
		results.setSuccess(true);
		return results;
		
	}
	
	/**
	 * Takes Create Game Params and create a new game and save it in the server
	 * @param thisParams
	 * @return CreateGameResults
	 */
	public CreateGameResults create(CreateGameParams thisParams) {
		CreateGameResults thisResult = new CreateGameResults();
		return thisResult;
	}
	
	/**
	 * Takes JoinGameParams and put joined user information in to the game
	 * @param thisParams
	 * @return JoinGameResults
	 */
	public JoinGameResults join(JoinGameParams thisParams) {
		JoinGameResults thisResult = new JoinGameResults();
		return thisResult;
	}
	
	/**
	 * Takes SaveGameParams and save current game model
	 * @param thisParams
	 * @return SaveGameResults
	 */
	public SaveGameResults save(SaveGameParams thisParams) {
		SaveGameResults thisResult = new SaveGameResults();
		return thisResult;
	}
	
	/**
	 * Takes LoadGameResults and load saved game model
	 * @param thisParams
	 * @return LoadGameResults
	 */
	public LoadGameResults load(LoadGameParams thisParams) {
		LoadGameResults thisResult = new LoadGameResults();
		return thisResult;
	}
}
