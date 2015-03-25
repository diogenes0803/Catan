/**
 * 
 */
package server.facades;

import server.Server;
import shared.communicator.RegisterUserParams;
import shared.communicator.RegisterUserResults;
import shared.communicator.UserLoginParams;
import shared.communicator.UserLoginResults;

/**
 * @author campbeln
 *
 */
public class UserFacade implements Facade {
	
	
	private Server server;
	 /**
	 * @param params
	 * @return
	 */
	public UserLoginResults userLogin(UserLoginParams params) {
		 UserLoginResults result = new UserLoginResults();
		 
		 
		 
		 result.setName("Sam");
		 result.setPassword("Sam");
		 result.setPlayerId(0);
		 
		 return result;
	 }
	 
	 /**
	 * @param params
	 * @return
	 */
	public RegisterUserResults registerUser(RegisterUserParams params) {
	        RegisterUserResults result = new RegisterUserResults();
	        return result;
	 }

	@Override
	public void setServer(Server server) {
		
		this.server = server;
	}

}
