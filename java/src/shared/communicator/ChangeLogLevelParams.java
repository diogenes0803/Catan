package shared.communicator;
/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class ChangeLogLevelParams {
	String logLevel;
	
	public ChangeLogLevelParams(String logLevel)
	{
		this.logLevel = logLevel;
		//Valid values are ALL, SEVERE, WARNING, INFO, CONFIG,FINE,FINER,FINEST,OFF
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	
	

}
