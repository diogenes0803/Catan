package shared.communication;


public class AcceptTradeParams extends AbstractGameParams{
    public final int playerIndex;
    public final boolean willAccept;

    public AcceptTradeParams(int playerIndex, boolean willAccept) {
        this.playerIndex = playerIndex;
        this.willAccept = willAccept;
    }
}
