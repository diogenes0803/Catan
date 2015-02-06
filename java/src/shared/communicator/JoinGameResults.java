package shared.communicator;
/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class JoinGameResults {
	String status;
	
	JoinGameResults(String status)
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
