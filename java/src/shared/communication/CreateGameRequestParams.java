package shared.communication;


public class CreateGameRequestParams extends AbstractJoinGameParams{
    public final boolean randomTiles;
    public final boolean randomNumbers;
    public final boolean randomPorts;
    public final String name;

    public CreateGameRequestParams(boolean randomTiles, boolean randomNumbers, boolean randomPorts, String name) {
        this.randomTiles = randomTiles;
        this.randomNumbers = randomNumbers;
        this.randomPorts = randomPorts;
        this.name = name;
    }
}
