package shared.communicator;

import shared.models.CatanModel;

/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class GetCommandsResults {
	CatanModel model;
	
	GetCommandsResults(CatanModel model)
	{
		this.model = model;
	}

	public CatanModel getModel() {
		return model;
	}

	public void setModel(CatanModel model) {
		this.model = model;
	}
	
	

}
