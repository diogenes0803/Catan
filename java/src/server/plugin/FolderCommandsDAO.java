package server.plugin;

import server.command.ICommand;
import server.persistence.AbstractPersistenceManager;
import server.persistence.ICommandsDAO;
import server.persistence.PersistenceException;
import shared.model.IGame;
import shared.model.ModelException;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.SortedMap;
import java.util.TreeMap;


public class FolderCommandsDAO extends AbstractFolderDAO implements ICommandsDAO {
    public FolderCommandsDAO(FolderPersistenceManager folderPersistenceManager) throws PersistenceException {
        super(folderPersistenceManager, "commands");
    }

    @Override
    public void saveCommand(ICommand command) throws PersistenceException {
        // write the new command to the disk
        String gameFolder = Integer.toString(command.getGame().getID());
        AbstractPersistenceManager.ensureDirectoryExists(getDirectory().resolve(gameFolder));
        saveCheckpoint(command.getGame());
        writeFile(command, gameFolder, Integer.toString(command.getGame().getModelVersion()));
    }

    @Override
    public void loadCommands(IGame game) throws PersistenceException {
        SortedMap<Integer, ICommand> orderedCommands = new TreeMap<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(getGameFolder(game))) {
            // read in all of the commands
            for (Path path : directoryStream) {
                ICommand command = readFile(path);
                orderedCommands.put(Integer.parseInt(path.getFileName().toString()), command);
            }
        }
        catch (IOException e) {
            throw new PersistenceException("Failed reading command.", e);
        }

        // execute commands on the game in order
        try {
            for (ICommand command : orderedCommands.values()) {
                command.setGameAndPlayers(game);
                command.execute();
            }
        }
        catch (ModelException e) {
            throw new PersistenceException("Failed executing command.", e);
        }

        // after all the commands have been applied to the game
        // save the game and clear the folder
        saveGameAndClear(game);
    }

    private void saveCheckpoint(IGame game) throws PersistenceException {
        // check the number of commands in the commands directory for the game
        int numCommands = 0;
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(getGameFolder(game))) {
            // if the number of saved commands is N or greater
            for (Path p : directoryStream) {
                numCommands++;
            }
        } catch (IOException e) {
            throw new PersistenceException("Failed to read in the commands directory", e);
        }

        // save game and clear the folder
        if (numCommands >= getPersistenceManager().getCommandsBetweenCheckpoints()) {
            saveGameAndClear(game);
        }
    }

    private void saveGameAndClear(IGame game) throws PersistenceException {
        getPersistenceManager().createGamesDAO().saveGame(game);
        deleteDirectoryContents(getGameFolder(game));
    }

    private Path getGameFolder(IGame game) throws PersistenceException {
        return getDirectory().resolve(Integer.toString(game.getID()));
    }
}
