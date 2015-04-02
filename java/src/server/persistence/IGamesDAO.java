package server.persistence;

import shared.model.IGame;
import shared.model.IGameManager;


public interface IGamesDAO {

    public void saveGame(IGame game) throws PersistenceException;


    public IGameManager loadGames() throws PersistenceException;
}
