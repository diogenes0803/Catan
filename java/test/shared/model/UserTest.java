package shared.model;

import org.junit.*;
import org.junit.rules.ExpectedException;
import server.Server;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * @author Wyatt
 */
public class UserTest {
    String name;
    String password;
    int id;

    @Before
    public void setUp() throws Exception {
        name = "ALKSdjlskjd dfgnm,brtgljkebn";
        password = "oerijf erjkldfkjgklj 54rkjl 3k5n4 nmk";
        id = -1234;
    }

    @After
    public void tearDown() throws Exception {
        name = null;
        password = null;
    }

    @Test
    public void testCreateUser() throws Exception {
        IUser user = new User(name, password, id);

        assertEquals(name, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(id, user.getId());
    }
}
