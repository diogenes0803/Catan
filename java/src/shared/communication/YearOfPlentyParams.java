package shared.communication;

import shared.definitions.ResourceType;


public class YearOfPlentyParams extends AbstractGameParams{
    public final int playerIndex;
    public final ResourceType resource1;
    public final ResourceType resource2;

    public YearOfPlentyParams(int playerIndex, ResourceType resource1, ResourceType resource2) {
        this.playerIndex = playerIndex;
        this.resource1 = resource1;
        this.resource2 = resource2;
    }
}
