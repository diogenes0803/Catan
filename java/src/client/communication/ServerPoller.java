package client.communication;

/**
 * <p>Description: The Server Poller polls the server at regular intervals in order to update
 the current Model state. Is activated when client gui launches.</p>
 * @author Nate Campbell, dbilleter
 */

import java.util.Timer;
import java.util.TimerTask;

public class ServerPoller {
	
	private Timer timer;
	private ServerStandinInterface server;
	
	public final int START_DELAY = 1 * 1000; //Waits 1 second to start running
    public final int CHECK_FREQUENCY = 1 * 1000; //check every 1 second
	
	public ServerPoller(ServerStandinInterface server) {
		this.server = server;
		timer = new Timer();
		
		
	}
	
	/**
	 * @pre Nothing.
	 * @post <p>Asks the server to update the current Model state.<br> 
	 The server checks to see if anything actually needs to be updated.</p>
	 */
	public void updateModel() {
	    
		this.server.getModel();
	}
	
	/**
	 * Stops poller.
	 * @pre to be called when client Jframe exits.
	 */
	public void stopTimer(){
	    timer.cancel();
	    timer.purge();
	}

	public void startTimer() {
		timer.schedule(new TimerTask() {
		    @Override
		    public void run() {
		      updateModel();
		    }
		  }, START_DELAY, CHECK_FREQUENCY );	
		
	}
	
}//end class


