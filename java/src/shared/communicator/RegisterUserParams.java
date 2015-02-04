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
}
