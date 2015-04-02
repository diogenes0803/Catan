package shared.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
