package server.persistence;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import server.plugin.NoPersistenceManager;


public class PersistenceManagerLoader implements IPersistenceManagerLoader {
    private static Logger logger = Logger.getLogger("catanserver");

    private Class<? extends IPersistenceManager> local_persistenceManagerClass;

    public PersistenceManagerLoader() throws InvalidPluginException {
        try {
            AbstractPersistenceManager.ensureDirectoryExists(Paths.get("plugins"));
        }
        catch (PersistenceException e) {
            throw new InvalidPluginException(e);
        }

        // default to no persistence
        local_persistenceManagerClass = NoPersistenceManager.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void loadPersistencePlugin(String option) throws InvalidPluginException {
        logger.entering("server.persistence.PersistenceManagerLoader", "loadPersistencePlugin");
        if (option == null) return;

        try {
            Path jarFile = Paths.get("plugins", option.concat(".jar"));
            if (!Files.isRegularFile(jarFile)) {
                throw new InvalidPluginException("Cannot read the file \"" + jarFile.toAbsolutePath() + "\".");
            }
            ClassLoader loader = URLClassLoader.newInstance(new URL[]{jarFile.toUri().toURL()}, getClass().getClassLoader());
            local_persistenceManagerClass = (Class<? extends IPersistenceManager>) loader.loadClass("server.plugin." + option);

            logger.fine("Using the " + option + " plugin for data persistence.");
        }
        catch (ClassNotFoundException | MalformedURLException e) {
            throw new InvalidPluginException("Error loading the persistence server.plugin \"" + option + "\".", e);
        }
        finally {
            logger.exiting("server.persistence.PersistenceManagerLoader", "loadPersistencePlugin");
        }
    }

    @Override
    public IPersistenceManager createPersistenceManager(int commandsBetweenCheckpoints) throws InvalidPluginException {
        assert local_persistenceManagerClass != null;

        try {
            Constructor<? extends IPersistenceManager> ctor = local_persistenceManagerClass.getDeclaredConstructor(Integer.TYPE);
            return (IPersistenceManager) ctor.newInstance(commandsBetweenCheckpoints);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new InvalidPluginException("Failed to instantiate the persistence manager.", e);
        }
        catch (NoSuchMethodException e) {
            throw new InvalidPluginException("Plugin does not provide the correct constructor.", e);
        }
    }
}
