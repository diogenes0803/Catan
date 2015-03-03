/**
 *
 */
package test;

import client.communication.MockServer;
import client.communication.ServerPoller;
import client.communication.ServerProxy;
import org.junit.Before;
import org.junit.Test;
import shared.communicator.JoinGameParams;
import shared.communicator.JoinGameResults;
import shared.communicator.UserLoginParams;
import shared.communicator.UserLoginResults;

import java.util.Timer;
import java.util.TimerTask;

import static org.junit.Assert.assertTrue;

/**
 * @author campbeln
 */
public class ServerPollerTest {
    private ServerPoller poller;
    private MockServer mockServer;
    private ServerProxy serverProxy;
    boolean worked;
    int count;
    private String goodUsername;
    private String goodPassword;

    @Before
    public void setUp() throws Exception {
        serverProxy = ServerProxy.getInstance();
        serverProxy.initClientComm("localhost", "8081");
        mockServer = new MockServer();
        poller = new ServerPoller();
        worked = false;
        count = 0;
        goodUsername = "Sam";
        goodPassword = "sam";

    }


    /**
     * Test method for {@link client.communication.ServerPoller#updateModel()}.
     */
    @Test
    public void testUpdateModel() {

        //Connects to the server so that there's a cookie
        UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
        UserLoginResults results1 = serverProxy.userLogin(params1);
        JoinGameParams params = new JoinGameParams(0, "red");
        JoinGameResults results = serverProxy.joinGame(params);

        System.out.println("Now testing update model with the poller");

        //Starts the poller timer
        poller.startTimer();
        Timer timer = new Timer();
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                System.out.println("Testing");
            }
        }, 1, 1 * 1000 + 100);

        try {
            Thread.sleep(1 * 10 * 1000 + 500);                 //1000 milliseconds is one second.
            count = serverProxy.getPollerCallCount();
            poller.stopTimer();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Junit Thread has now woken. Checking for success." + " times run " + count);

        assertTrue("Poller did not call update model like it should have." +
                        " It only called the model " + count + " during a one minute interval.",
                count > 2);

    }

}
