package server.persistence;

import org.junit.Test;
import server.plugin.*;
import shared.model.*;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.assertTrue;

/**
 * Created by Spencer Weight - 12/6/2014.
 */
public class FolderDAOTests {
    @Test
    public void folderUserDAOTest() {
        boolean usersWritten = true;

        try {
            FolderPersistenceManager pm = new FolderPersistenceManager(10);
            IUsersDAO folderUser = new FolderUsersDAO(pm);

            IUser myBuddy = new User("myBuddy", "abc123", 12);
            IUser myBuddy1 = new User("myBuddy1", "abc123", 13);
            IUser myBuddy2 = new User("myBuddy2", "abc123", 14);
            IUser myBuddy3 = new User("myBuddy3", "abc123", 15);
            folderUser.addUser(myBuddy);
            folderUser.addUser(myBuddy1);
            folderUser.addUser(myBuddy2);
            folderUser.addUser(myBuddy3);

        } catch (PersistenceException e) {
            System.out.println("Failed to write to disk\n" + e.getMessage());
            e.printStackTrace();
            usersWritten = false;
        }

        assertTrue("The user file was not written", usersWritten);

        boolean userRead = true;
        boolean userExists = true;
        boolean userExists1 = true;
        boolean userExists2 = true;
        boolean userExists3 = true;

        try {
            FolderPersistenceManager pm = new FolderPersistenceManager(10);
            IUsersDAO folderUser = new FolderUsersDAO(pm);

            IUserManager um = folderUser.loadUsers();

            userExists = um.doesUserExist("myBuddy");
            userExists1 = um.doesUserExist("myBuddy1");
            userExists2 = um.doesUserExist("myBuddy2");
            userExists3 = um.doesUserExist("myBuddy3");
        } catch (PersistenceException e) {
            System.out.println("Failed to read user from disk\n" + e.getMessage());
            e.printStackTrace();
            userRead = false;
        }

        assertTrue("The user file was not read", userRead);
        assertTrue("The user file was read, but the user manager reported some users as not existing",
                userExists && userExists1 && userExists2 && userExists3);
    }
}
