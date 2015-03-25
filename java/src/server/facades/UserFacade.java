/**
 * 
 */
package server.facades;

import server.Server;
import server.data.User;
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
	 * @param params
	 * @return
	 */
	public UserLoginResults userLogin(UserLoginParams params) {
		UserLoginResults result = new UserLoginResults();
		
		User user = new User("Sam", "sam", 0);
		
		Server.users.put("Sam", user);
		//Sees if the user exists, if not returns null
		if ( Server.users.get(params.getusername()) != null)
		{
			 if (Server.users.get(params.getusername()).getPassword().equals(params.getPassword()))
			 {
				 //The user should be able to log in now
			 }
			 else
			 {
				 return null;
			 }
		}
		else
		{
			result = null;
		}
				 
		return result;
	 }
	 
	 /**
	 * @param params
	 * @return
	 */
	public RegisterUserResults registerUser(RegisterUserParams params) {
	        RegisterUserResults result = new RegisterUserResults();
	        
	      //Sees if the user exists, if not returns null
			if ( Server.users.get(params.getusername()) == null)
			{
				//How to set userID correctly?
				User user = new User(params.getusername(), params.getPassword(), 0);
				Server.users.put(params.getusername(), user);
			}
			else
			{
				result = null;
			}
					 
	        
	        return result;
	 }

}