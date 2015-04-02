package server.plugin;

import server.command.ICommand;
import server.persistence.ICommandsDAO;
import server.persistence.PersistenceException;
import shared.model.IGame;
import shared.model.ModelException;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


public class SQLiteCommandsDAO extends AbstractSQLiteDAO implements ICommandsDAO {

    protected SQLiteCommandsDAO(SQLitePersistenceManager manager) {
        super(manager);
    }

    @Override
    public void saveCommand(ICommand command) throws PersistenceException {

        String checkpointQuery = "select commandData from commands where gameId = ?";
        List<ICommand> commands = super.readFromDB(checkpointQuery, command.getGame().getID(), "commandData");

        int commandsSaved = commands.size();

        final String SAVE_COMMAND_SQL = "insert into commands (gameId, commandData) values (?, ?)";

        if (commandsSaved < getPersistenceManager().getCommandsBetweenCheckpoints()) {
            super.writeToDB(SAVE_COMMAND_SQL, command.getGame().getID(), command);
        } else {
            String sql = "update games set gameData = ? where gameId = ?";
            super.updateDB(sql, command.getGame(), command.getGame().getID());

            sql = "delete from commands where gameId = ?";
            super.deleteFromDB(sql, command.getGame().getID());

            super.writeToDB(SAVE_COMMAND_SQL, command.getGame().getID(), command);
        }
    }

    @Override
    public void loadCommands(IGame game) throws PersistenceException {
        String query = "select commandData from commands where gameId = ?";
        List<ICommand> commands = super.readFromDB(query, game.getID(), "commandData");

        SortedMap<Integer, ICommand> orderedCommands = new TreeMap<>();

        for (int i = 0; i < commands.size(); ++i) {
            orderedCommands.put(i, commands.get(i));
        }

        try {
            for (ICommand command : orderedCommands.values()) {
                command.setGameAndPlayers(game);
                command.execute();
            }
        } catch (ModelException e) {
            throw new PersistenceException("Failed executing stored command.", e);
        }

        String sqlQuery = "update games set gameData = ? where gameId = ?";
        super.updateDB(sqlQuery, game, game.getID());

        sqlQuery = "delete from commands where gameId = ?";
        super.deleteFromDB(sqlQuery, game.getID());
    }
}