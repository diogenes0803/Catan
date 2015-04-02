package shared.communication;


public abstract class AbstractJoinGameParams implements IJoinGameParams {
    private int userId;


    @Override
    public int getUserId() {
        return userId;
    }


    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
