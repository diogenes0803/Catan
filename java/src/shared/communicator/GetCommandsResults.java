package shared.communicator;

import java.util.List;

import shared.models.CatanModel;

/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class GetCommandsResults extends ResponseBodyContainer {
	List<String> commands;
	boolean success;
	
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

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	
}
