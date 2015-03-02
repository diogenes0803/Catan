/**
 * 
 */
package client.state;

/**
 * @author campbeln
 *
 */
public class StateManager {

	private static StateManager instance = new StateManager();
	private IState state;

	public static StateManager getInstance() {
		return instance;
	}
	
	public IState getState() {
		return state;
	}

	public void setState(IState state) {
		this.state = state;
	}
}
