package client.communication;

import java.util.Timer;
import java.util.TimerTask;

public class ServerPoller {
	
	private Timer timer;
	private int versionID;
	private ServerFacade facade;
	
	public ServerPoller() {
		
	}
	
	public boolean isDifferent() {
		
	}
	
	public void updateModel() {
		
	}
}

class CheckTask extends TimerTask {
	/*
	 * Will run isDifferent, if true it calls updateModel.
	 */
	public void run() {
		
	}
}
