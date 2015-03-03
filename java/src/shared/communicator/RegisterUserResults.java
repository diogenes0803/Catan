package shared.communicator;

/**
 * Dumb data holder
 *
 * @author Jonathan, dbilleter
 */
public class RegisterUserResults extends UserLoginResults {


    public RegisterUserResults(String response_body) {
        super(response_body);
    }

    public RegisterUserResults() {
        super("");
    }


}
