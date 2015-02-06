package shared.communicator;

/**
 * Description: Holds the info necessary to login a user
 * @author oxbor
 *
 */
public class UserLoginParams {
	
	private UserName userName;
	private String password;
	
	UserLoginParams(UserName userName, String password)
	{
		this.userName = userName;
		this.password = password;
	}

	public UserName getUserName() {
		return userName;
	}

	public void setUserName(UserName userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
