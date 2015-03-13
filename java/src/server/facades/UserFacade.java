/**
 * 
 */
package server.facades;

import shared.communicator.RegisterUserParams;
import shared.communicator.RegisterUserResults;
import shared.communicator.UserLoginParams;
import shared.communicator.UserLoginResults;

/**
 * @author campbeln
 *
 */
public class UserFacade implements Facade {
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	 public UserLoginResults userLogin(UserLoginParams params) {
		 UserLoginResults result = new UserLoginResults();
		 return result;
	 }
	 
	 /**
	  * 
	  * @param params
	  * @return
	  */
	 public RegisterUserResults registerUser(RegisterUserParams params) {
	        RegisterUserResults result = new RegisterUserResults();
	        return result;
	 }

}
