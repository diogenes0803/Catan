/**
 *
 */
package shared.communicator;

/**
 * @author campbeln
 */
public class MonumentParams {

    private int playerIndex;
    private String type;

    public MonumentParams(int playerIndex) {
        this.playerIndex = playerIndex;
        setType("Monument");
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
