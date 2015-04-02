package shared.communication;


public class LoadGameRequestParams extends AbstractJoinGameParams{
    public final String name;

    public LoadGameRequestParams(String name) {
        this.name = name;
    }
}
