package server.command;

import shared.locations.*;
import shared.model.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jeffreybacon on 11/16/14.
 */
public class BuildCityCommandTest {
    IGame game;
    IPlayer player;

    @Before
    public void setUp() throws Exception {
        game = initAGame("sample/empty_board.json");
        player = game.getPlayerByIndex(0);
        game.setCurrentPlayer(player);
    }

    @After
    public void tearDown() throws Exception {
        game = null;
        player = null;
    }

    @Test
    public void testPlaceCity() throws Exception {
        game.getMap().placeRoad(player.buildRoad(true), new EdgeLocation(0, 0, EdgeDirection.NorthWest));
        game.getMap().placeSettlement(player.buildSettlement(true), new VertexLocation(0, 0, VertexDirection.West));
        game.setGameState(GameState.Playing);
        game.getResourceBank().subtract(Prices.CITY);
        player.addResources(Prices.CITY);
        VertexLocation location = new VertexLocation(0, 0, VertexDirection.West);
        new BuildCityCommand(game, player, location).execute();
        assertTrue("City should be placed", game.getMap().getTownAt(location) instanceof City);
    }

    @Test(expected = IllegalCommandException.class)
    public void testPlaceInvalidCity() throws Exception {
        VertexLocation location = new VertexLocation(0, 0, VertexDirection.West);
        new BuildCityCommand(game, player, location).execute();
    }

    private IGame initAGame(String jsonTestFile) throws Exception {
        String emptyBoardJSON = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(jsonTestFile)));
        ModelInitializer initModel = new ModelInitializer();
        initModel.initializeClientModel(emptyBoardJSON, 0);
        return GameModelFacade.instance().getGame();
    }
}
