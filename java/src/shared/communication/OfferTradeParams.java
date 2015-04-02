package shared.communication;

import shared.model.ResourceBank;


public class OfferTradeParams extends AbstractGameParams{
    public final int playerIndex;
    public final ResourceBank offer;
    public final int receiver;

    public OfferTradeParams(int playerIndex, ResourceBank offer, int receiver) {
        this.playerIndex = playerIndex;
        this.offer = offer;
        this.receiver = receiver;
    }
}
