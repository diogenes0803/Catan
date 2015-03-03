package shared.communicator;

public class SendChatParams {
    private String type;
    private int playerIndex;
    private String content;


    public SendChatParams(String content, int playerIndex) {
        this.playerIndex = playerIndex;
        this.content = content;
        setType("sendChat");
    }

    /**
     * @return the playerIndex
     */
    public int getPlayerIndex() {
        return playerIndex;
    }

    /**
     * @param playerIndex the playerIndex to set
     */
    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
