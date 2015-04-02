package shared.model;

import com.sun.net.httpserver.HttpServer;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * @author Wyatt
 */
public class UserManagerTest {
    private IUserManager userManager;
    String name;
    String password;
    int id = -1234;
    IUser user1, user2;
    IUser returnedUser1, returnedUser2;

    private HttpServer m_server;

    @BeforeClass
    public static void initializeTests() throws Exception{
    }

    @Before
    public void setUp() throws Exception {
        m_server = new server.Server().run();

        userManager = new UserManager();

        name = "This is a test.";
        password = "This is not a test. This is for reals.";
        id = -1234;

        // check that a user is successfully added
        user1 = new User(name, password, id);
        returnedUser1 = null;

        user2 = new User("This other Guy", "has a password", -5546);
        returnedUser2 = null;
    }

    @After
    public void tearDown() throws Exception {
        m_server.stop(0);

        userManager = null;

        name = null;
        password = null;

        user1 = null;
        returnedUser1 = null;

        user2 = null;
        returnedUser2 = null;

        Thread.sleep(100);
    }

    @Test
    public void testCreateUser() throws Exception {
        // check that a user is successfully added
        assertNotNull(returnedUser1 = userManager.createUser(name, password));
        assertEquals(user1.getUsername(), returnedUser1.getUsername());
        assertEquals(user1.getPassword(), returnedUser1.getPassword());

        // repeated username -- not allowed
        assertNull(userManager.createUser(name, "Seriously cool stuff, this is"));
        assertNull(userManager.createUser(name, password));

        // add another user
        assertNotNull(returnedUser2 = userManager.createUser(user2.getUsername(), user2.getPassword()));
        assertEquals(user2.getUsername(), returnedUser2.getUsername());
        assertEquals(user2.getPassword(), returnedUser2.getPassword());

        // check for repeat IDs
        assertNotEquals(user1.getId(), user2.getId());
    }

    @Test
    public void testLoginUser() throws Exception {
        assertNull(userManager.loginUser(user1.getUsername(), user2.getPassword()));
        userManager.createUser(user1.getUsername(), user1.getPassword());

        assertNotNull(returnedUser1 = userManager.loginUser(user1.getUsername(), user1.getPassword()));

        Field f = user1.getClass().getDeclaredField("playerID");
        f.setAccessible(true);
        f.setInt(user1, returnedUser1.getId());

        assertEquals(user1, returnedUser1);

        assertNotNull(returnedUser2 = userManager.createUser(user2.getUsername(), user2.getPassword()));

        f = user2.getClass().getDeclaredField("playerID");
        f.setAccessible(true);
        f.setInt(user2, returnedUser2.getId());

        assertEquals(user2, returnedUser2);
    }

    @Test
    public void testDoesUserExist() throws Exception {
        assertFalse(userManager.doesUserExist("nope, he doesn't"));
        assertFalse(userManager.doesUserExist(user1.getUsername()));

        assertNotNull(userManager.createUser(user1.getUsername(), user1.getPassword()));
        assertTrue(userManager.doesUserExist(user1.getUsername()));
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetUser() throws Exception {
        assertNotNull(returnedUser1 = userManager.createUser(user1.getUsername(), user1.getPassword()));
        assertEquals(returnedUser1, userManager.getUser(returnedUser1.getId()));

        exception.expect(ModelException.class);
        userManager.getUser(-12345);
    }
}
