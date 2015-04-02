package server.plugin;

import server.persistence.*;


public class FolderPersistenceManager extends AbstractPersistenceManager {
    public FolderPersistenceManager(int commandsBetweenCheckpoints) throws PersistenceException {
        super(commandsBetweenCheckpoints);
    }


    public void startTransaction() {
        // no transactions for folder stuff
    }


    public void endTransaction(boolean commit) {
        // no transactions for folder stuff
    }


    public void clear() throws PersistenceException {
        new FolderGamesDAO(this).clear();
        new FolderUsersDAO(this).clear();
        new FolderCommandsDAO(this).clear();
    }


    public IUsersDAO createUsersDAO() throws PersistenceException {
        return new FolderUsersDAO(this);
    }


    public IGamesDAO createGamesDAO() throws PersistenceException {
        return new FolderGamesDAO(this);
    }


    public ICommandsDAO createCommandsDAO() throws PersistenceException {
        return new FolderCommandsDAO(this);
    }

}
