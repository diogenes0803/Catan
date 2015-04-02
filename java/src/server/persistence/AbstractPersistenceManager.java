package server.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public abstract class AbstractPersistenceManager implements IPersistenceManager {
    private static final Path ROOT_DIR = Paths.get("data");
    private int local_commandsBetweenCheckpoints;

    protected AbstractPersistenceManager(int commandsBetweenCheckpoints) throws PersistenceException {
        local_commandsBetweenCheckpoints = commandsBetweenCheckpoints;

        ensureDirectoryExists(ROOT_DIR);
    }


    public static void ensureDirectoryExists(Path directory) throws PersistenceException {
        if (!Files.isDirectory(directory)) {
            try {
                Files.createDirectory(directory);
            }
            catch (IOException e) {
                throw new PersistenceException("Failed to create persistence subdirectory: " + directory, e);
            }
        }
    }


    public Path getRootDirectory() {
        return ROOT_DIR;
    }

    public final int getCommandsBetweenCheckpoints() {
        return local_commandsBetweenCheckpoints;
    }
}
