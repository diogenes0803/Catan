package shared.communicator;

import shared.data.GameInfo;

/**
 * Dumb data holder
 *
 * @author Jonathan
 */
public class ListGamesResults extends ResponseBodyContainer {
    GameInfo[] Game;

    public ListGamesResults(GameInfo[] games) {
        super("SUCCESS");
        this.Game = games;
        setSuccess(true);
    }

    public ListGamesResults() {
        super("");
    }

    public GameInfo[] getGames() {
        return Game;
    }

    public void setGames(GameInfo[] games) {
        this.Game = games;
    }


}
