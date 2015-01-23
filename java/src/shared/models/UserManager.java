package shared.models;

/**
 * UserManager Model contains information about the user that is logged in and has functionality to register new user and login, out
 * @author HojuneYoo
 *
 */

public class UserManager {
	
	private boolean isLoggedIn;
	private User loggedInUser;
	
	/**
	 * Register a New User to the server
	 * @pre User must provide all information correctly in right format
	 * @post New user information must be stored in the database and automatically logged user in
	 */
	public void RegisterNewUser(){
		return;
	}
	
	/**
	 * Logout the currently logged in User
	 * @pre User must have been logged in
	 * @post User must be logged out
	 */
	public void Logout(){
		return;
	}
	
	/**
	 * Log in the user
	 * @pre UserManager must not have any logged in user and user must provide correct credential
	 * @post User must be logged in and UserManager contains information about logged in user
	 */
	public void Login(){
		return;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	
	

}
