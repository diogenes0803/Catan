package shared.communicator;
/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class SaveGameResults {
	String status;
	Boolean success;
	
	public SaveGameResults(String status)
	{
		this.status = status;
	}

	public SaveGameResults() {
		// TODO Auto-generated constructor stub
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	

}
