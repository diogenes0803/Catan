package server.facade;

import client.network.*;
import com.sun.net.httpserver.HttpServer;
import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Spencer Weight - 11/23/2014.
 */
public class UserFacadeTest {

    private IGameAdministrator m_gameAdmin;
    private HttpServer m_server;

    @After
    public void tearDown() throws Exception {
        m_gameAdmin = null;
        m_server.stop(0);
        Thread.sleep(100);
    }

    @Before
    public void setUp() throws Exception {
        m_server = new server.Server().run();
        m_gameAdmin = GameAdministrator.getInstance();
    }

    @Test
    public void testRegister() {
        String user = "testUser";
        String pass = "abc123";
        boolean successfulRegister = createAUser(user, pass);

        assertTrue("Register did not succeed", successfulRegister);

        boolean userDoesNotExist = createAUser(user, pass);

        assertFalse("There is already an existing user with that name", userDoesNotExist);
    }

    @Test
    public void testLogin() throws Exception {
        String user2 = "user2";
        String pass2 = "123abc";
        assertFalse("The user has not been registered and the login " +
                "of the user returned true when it should have been false",
                m_gameAdmin.login(user2, pass2));

        assertTrue("The user was not created", createAUser(user2, pass2));
        assertTrue("The user was unable to login after successfully being registered", loginAttempt(user2, pass2));
    }

    //************************//
    // private helper methods //
    //************************//

    private boolean createAUser(String u, String p) {
        boolean successfulRegister = false;
        try {
            successfulRegister = m_gameAdmin.register(u, p);
        } catch (NetworkException e) {
            System.out.println(e.getMessage() + " : User failed to register");
        }

        return successfulRegister;
    }

    private boolean loginAttempt(String u, String p) {
        boolean successfulLogin = false;
        try {
            successfulLogin = m_gameAdmin.login(u, p);
        } catch (NetworkException e) {
            System.out.println(e.getMessage());
        }

        return successfulLogin;
    }
}
