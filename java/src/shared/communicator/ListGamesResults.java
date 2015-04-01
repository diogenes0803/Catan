package shared.communicator;

import com.google.gson.annotations.Expose;

import shared.data.GameInfo;

/**
 * Dumb data holder
 *
 * @author Jonathan
 */
public class ListGamesResults extends ResponseBodyContainer {
    @Expose GameInfo[] Game;

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
