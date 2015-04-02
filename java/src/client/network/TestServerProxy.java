package client.network;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.IResourceBank;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestServerProxy implements IServerProxy {
    private static Logger logger = Logger.getLogger("catan");

    private Collection<Path> m_paths;
    private boolean m_silent;

    public TestServerProxy() {
        this(false);
    }

    public TestServerProxy(boolean silent) {
        m_silent = silent;

        final String pattern = "*.json";
        String dir;

        String cwd = System.getProperty("user.dir");

        // Find the sample directory...
        if (cwd.endsWith("java")) {
            dir = "../sample";
        }
        else if (cwd.endsWith("dist")) {
            dir = "../../sample";
        }
        else if (Files.isDirectory(Paths.get("sample"))) {
            dir = "sample";
        }
        else {
            dir = "../../../sample";
        }

        m_paths = new ArrayList<>();
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(Paths.get(dir), pattern)) {
            for (Path path : dirStream) {
                m_paths.add(path);
            }
        }
        catch (IOException e) {
            logger.log(Level.WARNING, "Failed finding JSON inputs.", e);
            logger.fine("Current directory: " + System.getProperty("user.dir"));
            assert m_paths.size() > 0 : "No JSON was found for the TestServerProxy!";
        }

    }


    @Override
    public String getGameState() throws NetworkException {
        logger.entering("client.network.TestServerProxy", "getGameState");
        String clientModel = null;

        try {
            Path path;
            if (m_silent) {
                path = m_paths.iterator().next();
            }
            else {
                path = (Path) JOptionPane.showInputDialog(null, "Choose the JSON to give to the GUI.",
                        "Choose the Right (JSON)", JOptionPane.QUESTION_MESSAGE, null,
                        m_paths.toArray(), m_paths.iterator().next());
            }

            if (path == null) {
                logger.fine("Updating client model was canceled.");
                return null;
            }

            clientModel = new String(java.nio.file.Files.readAllBytes(path));
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to read sample JSON file in TestServerProxy.", e);
            logger.fine("Current directory: " + System.getProperty("user.dir"));
        }

       logger.exiting("client.network.TestServerProxy", "getGameState");
       return clientModel;
    }


    @Override
    public String resetGame() throws NetworkException {
        return null;
    }


    @Override
    public boolean changeLogLevel(String logLevel) throws NetworkException {
        return false;
    }


    @Override
    public String sendChat(int playerIndex, String message) throws NetworkException {
        return null;
    }


    @Override
    public String acceptTrade(int playerIndex, boolean willAccept) throws NetworkException {
        return null;
    }


    @Override
    public String discardCards(int playerIndex, IResourceBank discardedCards) throws NetworkException {
        return null;
    }


    @Override
    public String rollNumber(int playerIndex, int number) throws NetworkException {
        return null;
    }


    @Override
    public String buildRoad(int playerIndex, EdgeLocation edgeLoc, boolean free) throws NetworkException {
        return null;
    }


    @Override
    public String buildSettlement(int playerIndex, VertexLocation location, boolean free) throws NetworkException {
        return null;
    }


    @Override
    public String buildCity(int playerIndex, VertexLocation location) throws NetworkException {
        return null;
    }


    @Override
    public String offerTrade(int playerIndex, IResourceBank offer, int receiver) throws NetworkException {
        return null;
    }


    @Override
    public String maritimeTrade(int playerIndex, int ratio, ResourceType input, ResourceType output) throws NetworkException {
        return null;
    }


    @Override
    public String finishTurn(int playerIndex) throws NetworkException {
        return null;
    }


    @Override
    public String buyDevCard(int playerIndex) throws NetworkException {
        return null;
    }


    @Override
    public String playYearOfPlenty(int playerIndex, ResourceType resource1, ResourceType resource2) throws NetworkException {
        return null;
    }


    @Override
    public String playRoadBuilding(int playerIndex, EdgeLocation location1, EdgeLocation location2) throws NetworkException {
        return null;
    }


    @Override
    public String playSoldier(int playerIndex, HexLocation location, int victim) throws NetworkException {
        return null;
    }


    @Override
    public String playMonopoly(int playerIndex, ResourceType resource) throws NetworkException {
        return null;
    }


    @Override
    public String playMonument(int playerIndex) throws NetworkException {
        return null;
    }


    @Override
    public String robPlayer(int robbingPlayerIndex, int victimIndex, HexLocation hex) throws NetworkException { return null; }

    @Override
    public int getPlayerId() {
        return 0;
    }
}
