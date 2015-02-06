package shared.communicator;
/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class RegisterUserParams {
	private UserName userName;
	private String password;

	RegisterUserParams(UserName userName, String password)
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
