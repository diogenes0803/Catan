package shared.communicator;
/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class LoadGameResults {
	String status;
	boolean success;
	
	public LoadGameResults(String status)
	{
		this.status = status;
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
