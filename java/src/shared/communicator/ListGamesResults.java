package shared.communicator;

import java.util.ArrayList;
import java.util.List;

import shared.models.Game;

/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class ListGamesResults extends ResponseBodyContainer{
	List<Game> games;
	
	public ListGamesResults(List<Game> games)
	{
	    super("SUCCESS");
		this.games = games;
        setSuccess(true);
	}
	
	public ListGamesResults() {
	    super("");
		games = new ArrayList<Game>();
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}



	
}
