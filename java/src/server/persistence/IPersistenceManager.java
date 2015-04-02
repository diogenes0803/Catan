package server.persistence;


public interface IPersistenceManager {

    public int getCommandsBetweenCheckpoints();


    public void startTransaction() throws PersistenceException;


    public void endTransaction(boolean commit);


    public void clear() throws PersistenceException;


    public IUsersDAO createUsersDAO() throws PersistenceException;


    public IGamesDAO createGamesDAO() throws PersistenceException;


    public ICommandsDAO createCommandsDAO() throws PersistenceException;
}
