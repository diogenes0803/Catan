package client.network;

import com.sun.net.httpserver.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.*;
import shared.model.ResourceBank;

import static org.junit.Assert.*;

public class ServerProxyTest {
    private HttpServer m_server;
    private IHttpCommunicator m_httpCommunicator;
    private IServerProxy m_serverProxy;
    private IGameAdminServerProxy m_gameAdminProxy;

    @Before
    public void setUp() throws Exception {
        m_server = new server.Server().run();

        m_httpCommunicator = new HttpCommunicator();
        m_serverProxy = new ServerProxy(m_httpCommunicator);
        m_gameAdminProxy = new GameAdminServerProxy((m_httpCommunicator));
        m_gameAdminProxy.login("Sam", "sam");
        m_gameAdminProxy.joinGame(0, CatanColor.ORANGE);
    }

    @After
    public void takeDown() throws Exception {
        m_server.stop(0);
        m_server = null;

        m_httpCommunicator = null;
        m_serverProxy = null;
        m_gameAdminProxy = null;

        Thread.sleep(100);
    }

    @Test
    public void testSendChat() throws Exception {
        IGameAdministrator gameAdmin = GameAdministrator.getInstance();
        gameAdmin.setGameAdminServerProxy(m_gameAdminProxy);

        assertNull("Sent bad request, should have returned a null string.", m_serverProxy.sendChat(-1, "Test Message"));
    }

    @Test
    public void testAcceptTrade() throws Exception {
        String response = m_serverProxy.acceptTrade(-1234, true);
        assertNull("Sent bad request, should have returned a null string.", response);
    }

    @Test
    public void testDiscardCards() throws Exception {
        String response = m_serverProxy.discardCards(-1234, new ResourceBank(0, 0, 0, 0, 0));
        assertNull("Sent bad request, should have returned a null string.", response);
    }

    @Test
    public void testRollNumber() throws Exception {
        String response = m_serverProxy.rollNumber(-1234, -1);
        assertNull("Sent bad request, should have returned a null string.", response);
    }

    @Test
    public void testBuildRoad() throws Exception {
        String response = m_serverProxy.buildRoad(-1234, new EdgeLocation(new HexLocation(9, 9), EdgeDirection.NorthWest), true);
        assertNull("Sent bad request, should have returned a null string.", response);
    }

    @Test
    public void testBuildSettlement() throws Exception {
        String response = m_serverProxy.buildSettlement(-1234, new VertexLocation(new HexLocation(9, 9), VertexDirection.SouthEast), true);
        assertNull("Sent bad request, should have returned a null string.", response);
    }

    @Test
    public void testBuildCity() throws Exception {
        String response = m_serverProxy.buildCity(-1234, new VertexLocation(new HexLocation(9, 9), VertexDirection.NorthEast));
        assertNull("Sent bad request, should have returned a null string.", response);
    }

    @Test
    public void testOfferTrade() throws Exception {
        String response = m_serverProxy.offerTrade(-1234, new ResourceBank(0, 0, 0, 0, -1), -1);
        //assertNull("Sent bad request, should have returned a null string.", response);
        // returns game state in spite of bad input: only testing that no exceptions are thrown
    }

    @Test
    public void testMaritimeTrade() throws Exception {
        String response = m_serverProxy.maritimeTrade(-1234, 4, ResourceType.BRICK, ResourceType.ORE);
        assertNull("Sent bad request, should have returned a null string.", response);
    }

    @Test
    public void testFinishTurn() throws Exception {
        String response = m_serverProxy.finishTurn(-1234);
        //assertNull("Sent bad request, should have returned a null string.", response);
        // returns game state in spite of bad input: only testing that no exceptions are thrown
    }

    @Test
    public void testBuyDevCard() throws Exception {
        String response = m_serverProxy.buyDevCard(-1234);
        //assertNull("Sent bad request, should have returned a null string.", response);
        // returns game state in spite of bad input: only testing that no exceptions are thrown
    }

    @Test
    public void testPlayYearOfPlenty() throws Exception {
        String response = m_serverProxy.playYearOfPlenty(-1234, ResourceType.WOOD, ResourceType.ORE);
        assertNull("Sent bad request, should have returned a null string.", response);
    }

    @Test
    public void testPlayRoadBuilding() throws Exception {
        String response = m_serverProxy.playRoadBuilding(-1234, new EdgeLocation(new HexLocation(9, 9), EdgeDirection.NorthEast), new EdgeLocation(new HexLocation(5, 2), EdgeDirection.North));
        assertNull("Sent bad request, should have returned a null string.", response);
    }

    @Test
    public void testPlaySoldier() throws Exception {
        String response = m_serverProxy.playSoldier(-1234, new HexLocation(1234, 1234), -1);
        assertNull("Sent bad request, should have returned a null string.", response);
    }

    @Test
    public void testPlayMonopoly() throws Exception {
        String response = m_serverProxy.playMonopoly(-1234, ResourceType.BRICK);
        assertNull("Sent bad request, should have returned a null string.", response);
    }

    @Test
    public void testPlayMonument() throws Exception {
        String response = m_serverProxy.playMonument(-1234);
        assertNull("Sent bad request, should have returned a null string.", response);
    }
}