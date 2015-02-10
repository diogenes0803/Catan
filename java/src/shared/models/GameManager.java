package shared.models;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

	private boolean isJoinedGame;
	private Game game;
	private List<GameInfo> availableGames;
	
	public GameManager(){
	    isJoinedGame = false;
	    game = null;
	    availableGames = new ArrayList<GameInfo>();
	}
	
	
	public boolean isJoinedGame() {
		return isJoinedGame;
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
