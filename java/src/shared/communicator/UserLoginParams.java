package shared.communicator;

/**
 * Description: Holds the info necessary to login a user
 *
 * @author oxbor
 */
public class UserLoginParams {

    private String username;
    private String password;

    public UserLoginParams(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserLoginParams() {
		// TODO Auto-generated constructor stub
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
