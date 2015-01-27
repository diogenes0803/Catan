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
	 */
	public void RefreshGameList(){
		return;
	}
	
	/**
	 * Create New Game and store in to game variable
	 * @pre User should not be in any game
	 * @post User should be in game and cannot create or join to the other game until left
	 * @param gameTitle
	 */
	public void CreateNewGame(String gameTitle){
		return;
	}
	
	/**
	 * Join selected game
	 * @pre User should not be in any game, Selected game must not be full or started
	 * @post User should be in game and cannot create or join to the other game until left
	 * @param gameId
	 */
	public void JoinGame(int gameId){
		return;
	}
	
	/**
	 * Leave currently joined game
	 * @pre User must be in game
	 * @post User must not be in game
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

	public List<GameInfo> getAvailableGames() {
		return availableGames;
	}

	public void setAvailableGames(List<GameInfo> availableGames) {
		this.availableGames = availableGames;
	}

	public void setJoinedGame(boolean isJoinedGame) {
		this.isJoinedGame = isJoinedGame;
	}
	
	
}
