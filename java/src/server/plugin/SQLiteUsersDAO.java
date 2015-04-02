package server.plugin;

import server.persistence.IUsersDAO;
import server.persistence.PersistenceException;
import shared.model.IUser;
import shared.model.IUserManager;
import shared.model.UserManager;

import java.util.List;


public class SQLiteUsersDAO extends AbstractSQLiteDAO implements IUsersDAO {

    public SQLiteUsersDAO(SQLitePersistenceManager manager) {
        super(manager);
    }

    @Override
    public void addUser(IUser newUser) throws PersistenceException {
        String query = "insert into users (userId, userData) values (?, ?)";
        super.writeToDB(query, newUser.getId(), newUser);
    }

    @Override
    public IUserManager loadUsers() throws PersistenceException {
        String query = "select * from users";
        List<IUser> users = super.readFromDB(query, -1, "userData");

        IUserManager userManager = new UserManager();

        try {
            for (IUser user : users) {
                userManager.loadUser(user);
            }
        } catch (Exception ex) {
            throw new PersistenceException(ex);
        }

        return userManager;
    }
}
