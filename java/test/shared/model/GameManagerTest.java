package shared.model;

import client.data.GameInfo;
import client.network.GameAdministrator;
import client.network.IGameAdministrator;
import com.sun.net.httpserver.HttpServer;
import org.junit.*;
import shared.definitions.CatanColor;

import static org.junit.Assert.assertTrue;

/**
 * Created by Spencer Weight - 11/19/2014.
 */
public class GameManagerTest {

    private IGameAdministrator m_gameAdmin;
    private String m_gameName;
    private int m_newGameIndex;
    private static String m_username;
    private static String m_pwd;

    private HttpServer m_server;

    @BeforeClass
    public static void beforeRunningTests() throws Exception{
        m_username = "Spencer";
        m_pwd = "spencer";
    }

    @AfterClass
    public static void afterRunningTests() throws Exception{
        m_username = "";
        m_pwd = "";
    }

    @Before
    public void setUp() throws Exception {
        m_server = new server.Server().run();

        m_gameAdmin = GameAdministrator.getInstance();
        m_gameName = "testGame";
        m_newGameIndex = -1;

        createAUser(m_username, m_pwd);
    }

    @After
    public void tearDown() throws Exception {
        m_server.stop(0);
        Thread.sleep(100);

        m_gameAdmin = null;
        m_gameName = null;
        m_newGameIndex = -1;
    }

    @Test
    public void testCreateGame() throws Exception {
        createAGame();
    }

    @Test
    public void testJoinGame() throws Exception {
        createAGame();
        loginAUser(m_username, m_pwd);

        boolean joinedGame = m_gameAdmin.joinGame(m_newGameIndex, CatanColor.BLUE);
        String gameTitle = m_gameAdmin.listGames().get(m_newGameIndex).getTitle();

        assertTrue("Failed to join game index: " + m_newGameIndex,joinedGame);
        System.out.println("Successfully joined a game");
        assertTrue("Joined a game with an unexpected title: " + gameTitle, gameTitle.equals(m_gameName));
        System.out.println("Joined game " + gameTitle);
    }

    //************************//
    // private helper methods //
    //************************//

    private void createAUser(String u, String p) throws Exception {
        m_gameAdmin.register(u, p);
    }

    private void loginAUser(String u, String p) throws Exception{
        m_gameAdmin.login(u, p);
    }

    private void createAGame() throws Exception {
        boolean randomPorts = false;
        boolean randomTiles = false;
        boolean randomNumber = false;

        int numGamesBefore = m_gameAdmin.listGames().size();

        GameInfo newGameInfo = m_gameAdmin.createGame(randomPorts, randomTiles, randomNumber, m_gameName);

        int numGamesAfter = m_gameAdmin.listGames().size();

        assertTrue("Number of games did not increase: " + numGamesBefore + " -> " + numGamesAfter,
                (numGamesBefore + 1) == numGamesAfter);

        int newGameIndex = m_gameAdmin.listGames().indexOf(newGameInfo);

//        for(GameInfo gi : m_gameAdmin.listGames()) {
//            System.out.println(gi.getTitle());
//        }

        assertTrue("There was a problem creating a new game in the GameManger", m_gameAdmin.listGames().get(newGameIndex) != null);
        assertTrue("The wrong game was retrieved", m_gameAdmin.listGames().get(newGameIndex).getTitle().equals("testGame"));

        m_newGameIndex = newGameIndex;
    }
}
