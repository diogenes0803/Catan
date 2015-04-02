package shared.communication;

import shared.model.ResourceBank;


public class DiscardCardParams extends AbstractGameParams{
    public final int playerIndex;
    public final ResourceBank discardedCards;

    public DiscardCardParams(int playerIndex, ResourceBank discardedCards) {
        this.playerIndex = playerIndex;
        this.discardedCards = discardedCards;
    }
}
