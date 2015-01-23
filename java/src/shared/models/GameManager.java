package shared.models;

import java.util.List;

public class GameManager {

	private boolean isJoinedGame;
	private Game game;
	private List<GameInfo> availableGames;
	
	/**
	 * Update available Game List into availableGmaes
	 * @pre None
	 * @post Information of games that are waiting for players should be in availableGames List
	 * @param none
	 */
	public void RefreshGameList(){
		return;
	}
	
	/**
	 * Create New Game and store in to game variable
	 * @pre User should not be in any game
	 * @post User should be in game and cannot create or join to the other game until left
	 * @param String gameTitle
	 */
	public void CreateNewGame(String gameTitle){
		return;
	}
	
	/**
	 * Join selected game
	 * @pre User should not be in any game, Selected game must not be full or started
	 * @post User should be in game and cannot create or join to the other game until left
	 * @param int gameId
	 */
	public void JoinGame(int gameId){
		return;
	}
	
	/**
	 * Leave currently joined game
	 * @pre User must be in game
	 * @post User must not be in game
	 * @param none
	 * 
	 */
	public void LeaveGame(){
		return;
	}
	
	public boolean isJoinedGame() {
		return isJoinedGame;
	}

	public void setJoinedGmae(boolean isJoinedGmae) {
		this.isJoinedGame = isJoinedGmae;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	
}
