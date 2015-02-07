package shared.communicator;
/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class RegisterUserResults {
	String responseBody;
	boolean success;
	
	RegisterUserResults(String responseBody)
	{
		this.responseBody = responseBody;
	}

	public RegisterUserResults() {
		// TODO Auto-generated constructor stub
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	
}
