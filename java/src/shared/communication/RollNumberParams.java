package shared.communication;


public class RollNumberParams extends AbstractGameParams{
    public final int playerIndex;
    public final int number;

    public RollNumberParams(int playerIndex, int number) {
        this.playerIndex = playerIndex;
        this.number = number;
    }
}
