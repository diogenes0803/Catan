package shared.models;

import java.util.List;

/**
 * Model for on going game
 * @author HojuneYoo
 *
 */

public class Game {
	
	private int gameId;
	private String gameTitle;
	private List<Player> players;
	private boolean isStarted;
	private Map map;
	private Bank bank;
	
	/**
	 * Start a game
	 * @pre Game must have 4 players and game must not been started yet.
	 * @post Game must have started
	 */
	public void startGame(){
		return;
	}
	
	/**
	 * Allow user to leave a game
	 * @pre User must be in a game
	 * @post Game has to be stopped and user must be out of game
	 */
	public void leaveGame(){
		return;
	}
	
	/**
	 * Save current game
	 * @pre User must be in a game
	 * @post Game has to be saved in server
	 */
	public void SaveGame(){
		
	}
	
	/**
	 * Load previous game
	 * @pre Server must have information about saved game, All users who played before should be joined
	 * @post Game must be loaded and started
	 */
	public void LoadGame(){
		
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getGameTitle() {
		return gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	

}
