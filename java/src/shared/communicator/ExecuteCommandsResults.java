package shared.communicator;

import shared.models.CatanModel;

/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class ExecuteCommandsResults {
	CatanModel model;
	
	public ExecuteCommandsResults(CatanModel model)
	{
		this.model = model;
	}

	public ExecuteCommandsResults() {
		// TODO Auto-generated constructor stub
	}

	public CatanModel getModel() {
		return model;
	}

	public void setModel(CatanModel model) {
		this.model = model;
	}
	

}
