package shared.communication;


public abstract class AbstractGameParams implements IGameParams {
    private int userId;
    private int gameId;


    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Override
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }


    @Override
    public int getUserId() {
        return userId;
    }


    @Override
    public int getGameId() {
        return gameId;
    }
}
