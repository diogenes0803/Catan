package client.communication;

/**
 * Description: The Server Poller polls the server at regular intervals in order to update
 * the current Model state.
 * @author Nate Campbell
 */

import java.util.Timer;
import java.util.TimerTask;

public class ServerPoller {
	
	private Timer timer;
	private ServerFacade facade;
	
	public ServerPoller() {
		
	}
	
	/**
	 * @pre Nothing.
	 * @post Asks the server to update the current Model state. The server checks to see if anything actually needs to be updated.
	 */
	public void updateModel() {
		
	}
}

class CheckTask extends TimerTask {
	
	/**
	 * @pre Nothing.
	 * @post Calls updateModel according to the scheduled interval.
	 */
	public void run() {
		
	}
}
