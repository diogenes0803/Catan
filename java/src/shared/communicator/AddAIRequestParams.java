package shared.communicator;

/**
 * Class that represents the AddAIRequest params
 *
 * @author kentcoble
 */
public class AddAIRequestParams extends AbstractGameParams{
    public final String AIType;

    public AddAIRequestParams(String AIType) {
        this.AIType = AIType;
    }
}
