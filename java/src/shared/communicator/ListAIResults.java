package shared.communicator;

import java.util.List;

/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class ListAIResults {
	List<String> aITypes;
	boolean success;
	
	public ListAIResults(List<String> aITypes)
	{
		this.aITypes = aITypes;
	}

	public List<String> getaITypes() {
		return aITypes;
	}

	public void setaITypes(List<String> aITypes) {
		this.aITypes = aITypes;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	

}
