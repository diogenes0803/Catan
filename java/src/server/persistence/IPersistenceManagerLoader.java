package server.persistence;


public interface IPersistenceManagerLoader {

    public void loadPersistencePlugin(String option) throws InvalidPluginException;


    public IPersistenceManager createPersistenceManager(int commandsBetweenCheckpoints) throws InvalidPluginException;
}
