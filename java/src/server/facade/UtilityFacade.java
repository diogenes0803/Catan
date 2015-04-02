package server.facade;

import java.util.logging.Level;
import java.util.logging.Logger;


public class UtilityFacade implements IUtilityFacade{
    private static Logger logger = Logger.getLogger("catanserver");

    public UtilityFacade() {}


    @Override
    public void changeLogLevel(Level lvl) {
        logger.setLevel(lvl);
        logger.fine("Now logging at " + logger.getLevel() + " annoyance level.");
    }
}
