package server.plugin;

import server.persistence.AbstractPersistenceManager;
import server.persistence.PersistenceException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public abstract class AbstractFolderDAO {
    private FolderPersistenceManager local_persistenceManager;
    private Path local_dataDir;

    protected AbstractFolderDAO(FolderPersistenceManager manager, String subdirectory) throws PersistenceException {
        local_persistenceManager = manager;

        local_dataDir = local_persistenceManager.getRootDirectory().resolve(subdirectory);
    }

    protected Path getDirectory() throws PersistenceException {
        AbstractPersistenceManager.ensureDirectoryExists(local_dataDir);
        return local_dataDir;
    }

    protected FolderPersistenceManager getPersistenceManager() {
        return local_persistenceManager;
    }


    public void clear() throws PersistenceException {
        deleteDirectoryContents(getDirectory());
    }
        

    public static void deleteDirectoryContents(Path directory) throws PersistenceException {
        if (Files.isDirectory(directory)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory)) {
                for (Path path : directoryStream) {
                    // delete everything, ignoring subfolders
                    if (Files.isDirectory(path)) {
                        deleteDirectoryContents(path);
                    }
                    Files.deleteIfExists(path);
                }
            }
            catch (IOException e) {
                throw new PersistenceException("Failed to delete persistence data.", e);
            }
        }
    }



    protected void writeFile(Object object, String fileOrSubdirectroyName, String... fileOrSubdirectoryNames) throws PersistenceException {
        Path file = getDirectory().resolve(Paths.get(fileOrSubdirectroyName, fileOrSubdirectoryNames));
        writeFile(object, file);
    }


    protected static void writeFile(Object object, Path file) throws PersistenceException {
        try (ObjectOutputStream writer = new ObjectOutputStream(Files.newOutputStream(file))) {
            writer.writeObject(object);
        }
        catch (IOException e) {
            throw new PersistenceException("Failed serializing object.", e);
        }
    }


    @SuppressWarnings("unchecked")
    protected static <T> T readFile(Path file) throws PersistenceException {
        try (ObjectInputStream reader = new ObjectInputStream(Files.newInputStream(file))) {
            return (T) reader.readObject();
        }
        catch (ClassNotFoundException | IOException e) {
            throw new PersistenceException("Failed reading the file " + file + " from disk.", e);
        }
    }
}
