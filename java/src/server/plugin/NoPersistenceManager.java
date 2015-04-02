package server.plugin;

import server.persistence.*;


public class NoPersistenceManager extends AbstractPersistenceManager {
    public NoPersistenceManager(int commandsBetweenCheckpoints) throws PersistenceException {
        super(commandsBetweenCheckpoints);
    }

    @Override
    public void startTransaction() {
    }

    @Override
    public void endTransaction(boolean commit) {
    }

    @Override
    public void clear() {
    }

    @Override
    public IUsersDAO createUsersDAO() {
        return new NoPersistenceUsersDAO();
    }

    @Override
    public IGamesDAO createGamesDAO() {
        return new NoPersistenceGamesDAO();
    }

    @Override
    public ICommandsDAO createCommandsDAO() {
        return new NoPersistenceCommandsDAO();
    }
}
