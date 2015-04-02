package server.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.model.GameModelFacade;
import shared.model.IGame;
import shared.model.IPlayer;
import shared.model.ModelInitializer;

import static org.junit.Assert.assertTrue;


public class RoadBuildingCommandTest {
    IGame game;
    IPlayer player;

    @Before
    public void setUp() throws Exception {
        game = initAGame("sample/state_playing.json");
        player = game.getPlayerByIndex(0);
        game.setCurrentPlayer(player);
    }

    @After
    public void tearDown() throws Exception {
        game = null;
        player = null;
    }

    @Test
    public void testPlayRoadBuilding() throws Exception {
        EdgeLocation edge1 = new EdgeLocation(1, 1, EdgeDirection.NorthWest);
        EdgeLocation edge2 = new EdgeLocation(1, 1, EdgeDirection.SouthWest);
        new RoadBuildingCommand(game, player, edge1, edge2).execute();
        assertTrue("Road should be placed at (1, 1) NorthWest", game.getMap().getRoad(edge1) != null);
        assertTrue("Road should be placed at (1, 1) SouthWest", game.getMap().getRoad(edge2) != null);
    }

    @Test(expected = IllegalCommandException.class)
    public void testPlayInvalidRoadBuilding() throws Exception {
        EdgeLocation edge1 = new EdgeLocation(1, 1, EdgeDirection.NorthEast);
        EdgeLocation edge2 = new EdgeLocation(1, 1, EdgeDirection.NorthWest);
        new RoadBuildingCommand(game, player, edge1, edge2).execute();
    }

    private IGame initAGame(String jsonTestFile) throws Exception {
        String emptyBoardJSON = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(jsonTestFile)));
        ModelInitializer initModel = new ModelInitializer();
        initModel.initializeClientModel(emptyBoardJSON, 0);
        return GameModelFacade.instance().getGame();
    }
}
