package shared.communication;

import shared.definitions.ResourceType;


public class MonopolyParams extends AbstractGameParams{
    public final ResourceType resource;
    public final int playerIndex;

    public MonopolyParams(ResourceType resource, int playerIndex) {
        this.resource = resource;
        this.playerIndex = playerIndex;
    }
}
