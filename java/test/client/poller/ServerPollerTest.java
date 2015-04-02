package client.poller;

import client.network.IServerProxy;
import client.network.TestServerProxy;
import com.sun.net.httpserver.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServerPollerTest {
    private IServerPoller m_serverPoller;
    private IServerProxy m_mockServerProxy;

    private HttpServer m_server;

    @Before
    public void setUp() throws Exception {
        m_server = new server.Server().run();
    }

    @After
    public void takeDown() throws Exception {
        m_server.stop(0);
        Thread.sleep(100);
    }

    @Test
    public void testUpdateGame() throws Exception {
        m_mockServerProxy = new TestServerProxy(true);
        m_serverPoller = ServerPoller.getInstance();
        m_serverPoller.setProxy(m_mockServerProxy);
        m_serverPoller.startPolling();

        Thread.sleep(11500);

        assertEquals("Poller should have polled 3 times in 11 seconds", 3, m_serverPoller.getPollCount());
    }
}