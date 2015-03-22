package shared.communicator;

import shared.data.GameInfo;

/**
 * Dumb data holder
 *
 * @author Jonathan
 */
public class ListGamesResults extends ResponseBodyContainer {
    GameInfo[] games;

    public ListGamesResults(GameInfo[] games) {
        super("SUCCESS");
        this.games = games;
        setSuccess(true);
    }

    public ListGamesResults() {
        super("");
    }

    public GameInfo[] getGames() {
        return games;
    }

    public void setGames(GameInfo[] games) {
        this.games = games;
    }


}
