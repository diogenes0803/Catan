package shared.communication;


public class SendChatParams extends AbstractGameParams{
    public final int playerIndex;
    public final String content;

    public SendChatParams(int playerIndex, String content) {
        this.playerIndex = playerIndex;
        this.content = content;
    }
}
