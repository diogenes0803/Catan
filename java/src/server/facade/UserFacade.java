package server.facade;

import server.persistence.IPersistenceManager;
import server.persistence.PersistenceException;
import shared.communication.CredentialsParams;
import shared.model.IUser;
import shared.model.IUserManager;

import java.util.logging.Level;
import java.util.logging.Logger;


public class UserFacade implements IUserFacade {
    private static Logger logger = Logger.getLogger("catanserver");

    private IUserManager local_userManager;
    private IPersistenceManager local_persistenceManager;

    public UserFacade(IUserManager userManager, IPersistenceManager persistenceManager) {
        local_userManager = userManager;
        local_persistenceManager = persistenceManager;
    }


    @Override
    public IUser login(CredentialsParams creds) {
        return local_userManager.loginUser(creds.username, creds.password);
    }


    @Override
    public IUser register(CredentialsParams creds) {
        IUser user = local_userManager.createUser(creds.username, creds.password);

        if (user != null) {
            try {
                local_persistenceManager.startTransaction();
                local_persistenceManager.createUsersDAO().addUser(user);
                local_persistenceManager.endTransaction(true);
            }
            catch (PersistenceException e) {
                local_persistenceManager.endTransaction(false);
                logger.log(Level.WARNING, "Failed to store newly registered user.", e);
            }
        }

        return user;
    }
}
