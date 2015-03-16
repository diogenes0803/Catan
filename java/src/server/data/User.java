package server.data;

/**
 * Class that represents a User on the server side
 * @author oxbor
 *
 */
public class User {
	private String userName;
	private String password;
	private int userID;
	
	public User(String userName, String password, int UserID)
	{
		this.userName = userName;
		this.password = password;
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	

}
