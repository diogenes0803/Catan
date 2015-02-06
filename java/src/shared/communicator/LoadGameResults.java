package shared.communicator;
/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class LoadGameResults {
	String status;
	
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
	
	
}
