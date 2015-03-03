/**
 *
 */
package shared.communicator;

/**
 * @author campbeln
 */
public class BuyDevCardParams {

    private int playerIndex;
    private String type;

    public BuyDevCardParams(int playerIndex) {
        this.playerIndex = playerIndex;
        setType("buyDevCard");
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
