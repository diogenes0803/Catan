package shared.communication;


public class SetLogLevelRequestParams extends AbstractGameParams{
    public final String logLevel;

    public SetLogLevelRequestParams(String logLevel) {
        this.logLevel = logLevel;
    }
}
