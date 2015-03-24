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
import shared.definitions.CatanColor;
import shared.models.Player;

/**
 * @author campbeln
 *
 */
public class GamesFacade implements Facade {

	public GamesFacade(){}
	
	public ListGamesResults list() {
		int gamesSize = Server.models.size();
		GameInfo[] games = new GameInfo[gamesSize];
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
		ServerModel game = new ServerModel();
		game.setGameTitle(thisParams.getName());
		game.setGameId(Server.models.size());
		Server.models.put(game.getGameId(), game);
		thisResult.setId(game.getGameId());
		thisResult.setTitle(game.getGameTitle());
		return thisResult;
	}
	
	/**
	 * Takes JoinGameParams and put joined user information in to the game
	 * @param thisParams
	 * @return JoinGameResults
	 */
	public JoinGameResults join(JoinGameParams thisParams) {
		JoinGameResults thisResult = new JoinGameResults();
		Player player = new Player();
		player.setColor(CatanColor.getCatanColor(thisParams.getColor()));
		thisResult.setSuccess(Server.models.get(thisParams.getId()).addPlayer(player));
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
