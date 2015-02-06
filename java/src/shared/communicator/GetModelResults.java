package shared.communicator;

import shared.models.CatanModel;

/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class GetModelResults {
	CatanModel model;
	
	public GetModelResults(CatanModel model)
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
