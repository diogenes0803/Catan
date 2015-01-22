package client.communication;

import java.util.Timer;
import java.util.TimerTask;

public class ServerPoller {
	
	private Timer timer;
	private int versionID;
	
	public ServerPoller() {
		
	}
	
	public boolean isDifferent() {
		return false;
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
