package server.plugin;

import server.persistence.IUsersDAO;
import shared.model.IUser;
import shared.model.IUserManager;
import shared.model.UserManager;


public class NoPersistenceUsersDAO implements IUsersDAO {
    @Override
    public void addUser(IUser newUser) {
    }

    @Override
    public IUserManager loadUsers() {
        return new UserManager();
    }
}
