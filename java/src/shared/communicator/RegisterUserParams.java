package shared.communicator;

/**
 * Dumb data holder
 *
 * @author Jonathan
 */
public class RegisterUserParams {
    private String username;
    private String password;

    public RegisterUserParams(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
