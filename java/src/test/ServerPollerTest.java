/**
 * 
 */
package test;

import static org.junit.Assert.assertTrue;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Before;
import org.junit.Test;

import client.communication.MockServer;
import client.communication.ServerPoller;

/**
 * @author campbeln
 *
 */
public class ServerPollerTest {
	private ServerPoller poller;
	private MockServer mockServer;
	boolean worked;
	int count;
	
	@Before
    public void setUp() throws Exception {
		mockServer = new MockServer();
		poller = new ServerPoller(mockServer);
		worked = false;
		count = 0;
    }


	/**
	 * Test method for {@link client.communication.ServerPoller#updateModel()}.
	 */
	@Test
	public void testUpdateModel() {
		System.out.println("Now testing update model with the poller");
		poller.startTimer();
        Timer timer = new Timer();
        
		timer.schedule(new TimerTask() {
		    @Override
		    public void run() {
		    	poller.stopTimer();
		    	count = mockServer.getTimesUpdated();
		    	worked = count >= poller.CHECKS_PER_MINUTE;
		    	
		    	System.out.println("Timer Finished");
		    }
		  }, 1*60*1000+100);
		
		System.out.println("System will wait for 1 minute while we ensure that poller works");
		try {
		    Thread.sleep(1*60*1000+500);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		System.out.println("Junit Thread has now woken. Checking for success");
		assertTrue("Poller did not call update model like it should have."+
                " It only called the model "+count +" during a one minute interval.",
                worked);
		
	   System.out.println("It "+(worked?"works :)":"did not work :("));
	}

}
