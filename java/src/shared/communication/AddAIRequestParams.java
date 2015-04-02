package shared.communication;


public class AddAIRequestParams extends AbstractGameParams{
    public final String AIType;

    public AddAIRequestParams(String AIType) {
        this.AIType = AIType;
    }
}
