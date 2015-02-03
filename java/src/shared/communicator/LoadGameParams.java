package shared.communicator;
/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class LoadGameParams {
	String name;
	
	LoadGameParams(String name)
	{
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
