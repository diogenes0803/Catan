package shared.communication;


public class CredentialsParams extends AbstractJoinGameParams{
    public final String username;
    public final String password;

    public CredentialsParams(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
