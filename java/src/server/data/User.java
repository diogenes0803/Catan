package server.data;

/**
 * Class that represents a User on the server side
 * @author oxbor
 *
 */
public class User {
	private String name;
	private String password;
	private int playerID;
	
	public User(String name, String password, int playerID)
	{
		this.name = name;
		this.password = password;
		this.playerID = playerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
}

	