package shared.communicator;

import java.util.List;

/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class ExecuteCommandsParams {
	List<String> commands;
	
	public ExecuteCommandsParams(List<String> commands)
	{
		this.commands = commands;
	}

	public List<String> getCommands() {
		return commands;
	}

	public void setCommands(List<String> commands) {
		this.commands = commands;
	}
	
	


}
