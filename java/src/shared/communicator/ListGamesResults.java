package shared.communicator;

import java.util.List;

import shared.models.Game;

/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class ListGamesResults {
	List<Game> games;
	boolean success;
	
	ListGamesResults(List<Game> games)
	{
		this.games = games;
	}
	
	public ListGamesResults() {
		// TODO Auto-generated constructor stub
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	
}
