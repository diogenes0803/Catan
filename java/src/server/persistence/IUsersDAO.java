package server.persistence;

import shared.model.IUser;
import shared.model.IUserManager;


public interface IUsersDAO {

    public void addUser(IUser newUser) throws PersistenceException;


    public IUserManager loadUsers() throws PersistenceException;
}
