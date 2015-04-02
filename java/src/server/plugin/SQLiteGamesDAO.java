package server.plugin;

import server.persistence.IGamesDAO;
import server.persistence.PersistenceException;
import shared.model.GameManager;
import shared.model.IGame;
import shared.model.IGameManager;

import java.util.List;


public class SQLiteGamesDAO extends AbstractSQLiteDAO implements IGamesDAO {

    protected SQLiteGamesDAO(SQLitePersistenceManager manager) {
        super(manager);
    }

    @Override
    public void saveGame(IGame game) throws PersistenceException {
        String readSql = "select * from games where gameId = ?";
        List<IGame> games = readFromDB(readSql, game.getID(), "gameData");
        if(games.size() > 0) {
            String sql = "update games set gameData = ? where gameId = ?";
            updateDB(sql, game, game.getID());
        } else {
            String sql = "insert into games (gameId, gameData) values (?, ?)";
            writeToDB(sql, game.getID(), game);
        }
    }

    @Override
    public IGameManager loadGames() throws PersistenceException {
        IGameManager gameManager = new GameManager();

        String sql = "select * from games";
        List<IGame> games = readFromDB(sql, -1, "gameData");

        for (IGame game : games) {
            getPersistenceManager().createCommandsDAO().loadCommands(game);
            gameManager.loadGame(game);
        }

        return gameManager;
    }
}
