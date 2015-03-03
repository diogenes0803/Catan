/**
 *
 */
package shared.communicator;

import shared.models.ResourceList;

/**
 * @author campbeln
 */
public class DiscardCardsParams {

    private int playerIndex;
    private ResourceList discardedCards;
    private String type;

    public DiscardCardsParams(int playerIndex, ResourceList discardedCards) {
        this.playerIndex = playerIndex;
        this.discardedCards = discardedCards;
        setType("discardCards");
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
     * @return the discardedCards
     */
    public ResourceList getDiscardedCards() {
        return discardedCards;
    }

    /**
     * @param discardedCards the discardedCards to set
     */
    public void setDiscardedCards(ResourceList discardedCards) {
        this.discardedCards = discardedCards;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
