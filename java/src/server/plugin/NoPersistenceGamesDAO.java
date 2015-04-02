package server.plugin;

import server.persistence.IGamesDAO;
import shared.model.GameManager;
import shared.model.IGame;
import shared.model.IGameManager;


public class NoPersistenceGamesDAO implements IGamesDAO {
    @Override
    public void saveGame(IGame game) {
    }

    @Override
    public IGameManager loadGames() {
        return new GameManager();
    }
}
