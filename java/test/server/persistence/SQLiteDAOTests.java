package server.persistence;

import static org.junit.Assert.*;
import org.junit.Test;
import server.plugin.*;
import shared.model.*;

import java.util.Collection;
import java.util.Iterator;

public class SQLiteDAOTests {
    @Test
    public void sqlUserDAOTest() {
        boolean usersWritten = true;

        try {
            SQLitePersistenceManager pm = new SQLitePersistenceManager(10);

            pm.clear();

            pm.startTransaction();

            IUsersDAO sqlUser = new SQLiteUsersDAO(pm);

            IUser myBuddy = new User("myBuddy", "abc123", 12);
            IUser myBuddy1 = new User("myBuddy1", "abc123", 13);
            IUser myBuddy2 = new User("myBuddy2", "abc123", 14);
            IUser myBuddy3 = new User("myBuddy3", "abc123", 15);
            sqlUser.addUser(myBuddy);
            sqlUser.addUser(myBuddy1);
            sqlUser.addUser(myBuddy2);
            sqlUser.addUser(myBuddy3);

            pm.endTransaction(true);

        } catch (PersistenceException e) {
            System.out.println("Failed to write to SQLite database\n" + e.getMessage());
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
            SQLitePersistenceManager pm = new SQLitePersistenceManager(10);

            pm.startTransaction();

            IUsersDAO sqlUser = new SQLiteUsersDAO(pm);

            IUserManager um = sqlUser.loadUsers();

            userExists = um.doesUserExist("myBuddy");
            userExists1 = um.doesUserExist("myBuddy1");
            userExists2 = um.doesUserExist("myBuddy2");
            userExists3 = um.doesUserExist("myBuddy3");

            pm.endTransaction(true);

        } catch (PersistenceException e) {
            System.out.println("Failed to read user from disk\n" + e.getMessage());
            e.printStackTrace();
            userRead = false;
        }

        assertTrue("The user file was not read", userRead);
        assertTrue("The user file was read, but the user manager reported some users as not existing",
                userExists && userExists1 && userExists2 && userExists3);
    }

    @Test
    public void sqlGamesDAOTest() {
        boolean gamesWritten = true;

        try {
            SQLitePersistenceManager pm = new SQLitePersistenceManager(10);
            pm.startTransaction();

            IGamesDAO sqlGames = pm.createGamesDAO();

            IGame game1;
            IGame game2;
            IGame game3;
            try {
                game1 = new Game("First Game", 1, true, false, true);
                game2 = new Game("My Game", 2, true, true, true);
                game3 = new Game("Another Game", 3, false, false, false);
            } catch (ModelException e) {
                throw new PersistenceException(e);
            }

            sqlGames.saveGame(game1);
            sqlGames.saveGame(game2);
            sqlGames.saveGame(game3);

            pm.endTransaction(true);

        } catch (PersistenceException e) {
            System.out.println("Failed to write to SQLite database\n" + e.getMessage());
            e.printStackTrace();
            gamesWritten = false;
        }

        assertTrue("The games file was not written", gamesWritten);

        boolean gamesRead = true;
        boolean game1Exists = false;
        boolean game2Exists = false;
        boolean game3Exists = false;

        try {
            SQLitePersistenceManager pm = new SQLitePersistenceManager(10);
            pm.startTransaction();

            IGamesDAO sqlGames = pm.createGamesDAO();

            IGameManager gm = sqlGames.loadGames();

            Collection<IGame> games = gm.listGames();

            Iterator iterator = games.iterator();

            game1Exists = (((IGame)iterator.next()).getName().equals("First Game"));
            game2Exists = (((IGame)iterator.next()).getName().equals("My Game"));
            game3Exists = (((IGame)iterator.next()).getName().equals("Another Game"));

            pm.endTransaction(true);

        } catch (PersistenceException e) {
            System.out.println("Failed to read user from disk\n" + e.getMessage());
            e.printStackTrace();
            gamesRead = false;
        }

        assertTrue("The games db was not read", gamesRead);
        assertTrue("The games db was read, but the GameManager does not contain all of the written games", game1Exists && game2Exists && game3Exists);

        boolean gamesUpdated = true;

        try {
            SQLitePersistenceManager pm = new SQLitePersistenceManager(10);
            pm.startTransaction();

            IGamesDAO sqlGames = pm.createGamesDAO();

            IGame game1;
            IGame game2;
            IGame game3;
            try {
                game1 = new Game("Second Game", 1, true, false, true);
                game2 = new Game("Your Game", 2, true, true, true);
                game3 = new Game("The Game", 3, false, false, false);
            } catch (ModelException e) {
                throw new PersistenceException(e);
            }

            sqlGames.saveGame(game1);
            sqlGames.saveGame(game2);
            sqlGames.saveGame(game3);

            pm.endTransaction(true);

        } catch (PersistenceException e) {
            System.out.println("Failed to write to SQLite database\n" + e.getMessage());
            e.printStackTrace();
            gamesUpdated = false;
        }

        assertTrue("The games file was not updated", gamesUpdated);

        boolean gamesReread = true;
        boolean game1Updated = false;
        boolean game2Updated = false;
        boolean game3Updated = false;

        try {
            SQLitePersistenceManager pm = new SQLitePersistenceManager(10);
            pm.startTransaction();

            IGamesDAO sqlGames = pm.createGamesDAO();

            IGameManager gm = sqlGames.loadGames();

            Collection<IGame> games = gm.listGames();

            Iterator iterator = games.iterator();

            game1Updated = (((IGame)iterator.next()).getName().equals("Second Game"));
            game2Updated = (((IGame)iterator.next()).getName().equals("Your Game"));
            game3Updated = (((IGame)iterator.next()).getName().equals("The Game"));

            pm.endTransaction(true);

        } catch (PersistenceException e) {
            System.out.println("Failed to read user from disk\n" + e.getMessage());
            e.printStackTrace();
            gamesReread = false;
        }

        assertTrue("The games db was not read", gamesReread);
        assertTrue("The games db was read, but the GameManager does not contain all of the updated games", game1Updated && game2Updated && game3Updated);
    }

    @Test
    public void sqlCommandsTest() {

        // Make new game

        // Save game to database

        // Write 10 commands

        // Write one more

    }


}