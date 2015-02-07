package shared.communicator;
/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class JoinGameResults {
	String status;
	boolean success;
	
	public JoinGameResults(String status)
	{
		this.status = status;
	}

	public JoinGameResults() {
		// TODO Auto-generated constructor stub
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	

}
