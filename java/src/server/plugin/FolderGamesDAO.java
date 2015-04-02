package server.plugin;

import server.persistence.IGamesDAO;
import server.persistence.PersistenceException;
import shared.model.GameManager;
import shared.model.IGame;
import shared.model.IGameManager;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;


public class FolderGamesDAO extends AbstractFolderDAO implements IGamesDAO {
    FolderGamesDAO(FolderPersistenceManager manager) throws PersistenceException {
        super(manager, "games");
    }

    @Override
    public void saveGame(IGame game) throws PersistenceException {
        writeFile(game, Integer.toString(game.getID()));
    }

    @Override
    public IGameManager loadGames() throws PersistenceException {
        // TODO: need to load commands for each game!
        IGameManager gameManager = new GameManager();

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(getDirectory())) {
            for (Path path : directoryStream) {
                IGame game = readFile(path);
                getPersistenceManager().createCommandsDAO().loadCommands(game);
                gameManager.loadGame(game);
            }
        }
        catch (IOException e) {
            throw new PersistenceException(e);
        }

        return gameManager;
    }
}
