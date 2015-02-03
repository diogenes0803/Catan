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
}
