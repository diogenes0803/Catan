package shared.communicator;

import java.util.List;

import shared.models.CatanModel;

/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class GetCommandsResults {
	List<String> commands;
	
	public GetCommandsResults(List<String> commands)
	{
		this.commands = commands;
	}

	public GetCommandsResults() {
		// TODO Auto-generated constructor stub
	}

	public List<String> getCommands() {
		return commands;
	}

	public void setCommands(List<String> commands) {
		this.commands = commands;
	}

	
	
	

}
