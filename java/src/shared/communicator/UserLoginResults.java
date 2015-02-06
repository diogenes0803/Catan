package shared.communicator;
/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class UserLoginResults {
	
	String responseBody;
	
	UserLoginResults(String responseBody)
	{
		this.responseBody = responseBody;
	}

	public UserLoginResults() {
		// TODO Auto-generated constructor stub
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
	
	

}
