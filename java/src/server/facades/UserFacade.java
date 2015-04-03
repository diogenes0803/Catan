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
	
	private int userID = 7;
	 /**
	 * @param params
	 * @return
	 */
	public UserLoginResults userLogin(UserLoginParams params) {
		UserLoginResults result = new UserLoginResults();
		//Sees if the user exists, if not returns null
		if ( Server.users.get(params.getusername()) != null)
		{
			 if (Server.users.get(params.getusername()).getPassword().equals(params.getPassword()))
			 {
				 result.setName(params.getusername());
				 result.setPassword(params.getPassword());
				 result.setPlayerId(Server.users.get(params.getusername()).getPlayerID());
				 result.setSuccess(true);
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
	 
	 private int addUserID() {
		int currentID = userID;
		userID++;
		return currentID;
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
				User user = new User(params.getusername(), params.getPassword(), addUserID());
				Server.users.put(params.getusername(), user);
				result.setName(params.getusername());
				result.setPassword(params.getPassword());
				result.setPlayerId(user.getPlayerID());
			}
			else
			{
				result = null;
			}
					 
	        
	        return result;
	 }
	
	

}