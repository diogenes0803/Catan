package shared.communicator;

/**
 * Abstract class for parameter objects used when playing a game
 * These adds a userId and a gameId, which are found in the client's cookies.
 *
 * @author kentcoble
 */
public abstract class AbstractGameParams implements IGameParams {
    private int userId;
    private int gameId;

    /**
     * Change the user id
     * @param userId the new user id
     */
    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Change the game id
     * @param gameId the new game id
     */
    @Override
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * Get the user ID of the user who made the request
     *
     * @return the user ID
     */
    @Override
    public int getUserId() {
        return userId;
    }

    /**
     * Get the game ID of the game this request is for
     *
     * @return the game ID
     */
    @Override
    public int getGameId() {
        return gameId;
    }
}
