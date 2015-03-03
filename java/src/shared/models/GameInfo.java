package shared.models;

/**
 * @author HojuneYoo Model that contains information of game This will be used when user select a joinable game
 */
public class GameInfo {

    private String gameTitle;
    private int gameId;
    private int numPlayer;


    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getNumPlayer() {
        return numPlayer;
    }

    public void setNumPlayer(int numPlayer) {
        this.numPlayer = numPlayer;
    }

}
