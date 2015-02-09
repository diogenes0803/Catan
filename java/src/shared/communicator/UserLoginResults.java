package shared.communicator;
/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class UserLoginResults extends ResponseBodyContainer{
	
	String responseBody;
    private String name;
    private String password;
    private int id; //not same as player index;
	
	UserLoginResults(String responseBody)
	{
	    super(responseBody);
		name = "";
		password = "";
		id = 0;
	}

	public UserLoginResults() {
	    super("");
	}


    public void setName(String _name) {
        this.name = _name;
    }

    public void setPassword(String _password) {
        this.password = _password;
    }

    public void setPlayerId(int _id) {
        this.id = _id;
    }
	
	
    public String getName(){return name;}
    public String getPassword(){return password;}
    public int getPlayerId(){return id;}
    
}
