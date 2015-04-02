package server.command;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jeffreybacon on 11/16/14.
 */
public class BuildRoadCommandTest {
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
    public void testPlaceRoad() throws Exception {
        game.setGameState(GameState.Playing);
        game.getResourceBank().subtract(Prices.ROAD);
        player.addResources(Prices.ROAD);
        game.getMap().placeSettlement(player.buildSettlement(true), new VertexLocation(0, 0, VertexDirection.NorthEast));
        EdgeLocation location = new EdgeLocation(0, 0, EdgeDirection.North);
        boolean free = false;
        new BuildRoadCommand(game, player, location, free).execute();
        assertTrue("Road should be placed.", game.getMap().getRoad(location) != null);
    }

    @Test
    public void testPlaceFreeRoad() throws Exception {
        EdgeLocation location = new EdgeLocation(0, 0, EdgeDirection.North);
        boolean free = true;
        new BuildRoadCommand(game, player, location, free).execute();
        assertTrue("Road should be placed.", game.getMap().getRoad(location) != null);
    }

    @Test
    public void testPlaceLongestRoad() throws Exception {
        assertTrue("Player victory points should be 0.", player.getVictoryPoints() == 0);
        EdgeLocation location = new EdgeLocation(0, 0, EdgeDirection.North);
        boolean free = true;
        new BuildRoadCommand(game, player, location, free).execute();
        assertTrue("Player victory points should be 0.", player.getVictoryPoints() == 0);
        location = new EdgeLocation(0, 0, EdgeDirection.NorthEast);
        new BuildRoadCommand(game, player, location, free).execute();
        assertTrue("Player victory points should be 0.", player.getVictoryPoints() == 0);
        location = new EdgeLocation(0, 0, EdgeDirection.NorthWest);
        new BuildRoadCommand(game, player, location, free).execute();
        assertTrue("Player victory points should be 0.", player.getVictoryPoints() == 0);
        location = new EdgeLocation(0, 0, EdgeDirection.SouthWest);
        new BuildRoadCommand(game, player, location, free).execute();
        assertTrue("Player victory points should be 0.", player.getVictoryPoints() == 0);
        location = new EdgeLocation(0, 0, EdgeDirection.South);
        new BuildRoadCommand(game, player, location, free).execute();
        assertTrue("Player victory points should be 2.", player.getVictoryPoints() == 2);
    }

    @Test(expected = IllegalCommandException.class)
    public void testPlaceInvalidRoad() throws Exception {
        player.addResources(new ResourceBank(1, 1, 0, 0, 0));
        EdgeLocation location = new EdgeLocation(10, 10, EdgeDirection.North);
        boolean free = false;
        new BuildRoadCommand(game, player, location, free).execute();
    }

    @Test
    public void testPlaceBlockedLongestRoad() throws Exception {
        assertTrue("Player victory points should be 0.", player.getVictoryPoints() == 0);
        EdgeLocation location = new EdgeLocation(0, 0, EdgeDirection.North);
        boolean free = true;
        new BuildRoadCommand(game, player, location, free).execute();
        assertTrue("Player victory points should be 0.", player.getVictoryPoints() == 0);
        location = new EdgeLocation(0, 0, EdgeDirection.NorthEast);
        new BuildRoadCommand(game, player, location, free).execute();
        assertTrue("Player victory points should be 0.", player.getVictoryPoints() == 0);
        location = new EdgeLocation(0, 0, EdgeDirection.NorthWest);
        new BuildRoadCommand(game, player, location, free).execute();
        assertTrue("Player victory points should be 0.", player.getVictoryPoints() == 0);
        location = new EdgeLocation(0, 0, EdgeDirection.SouthWest);
        new BuildRoadCommand(game, player, location, free).execute();
        assertTrue("Player victory points should be 0.", player.getVictoryPoints() == 0);

        IPlayer player2 = game.getPlayerByIndex(1);
        game.setCurrentPlayer(player2);
        location = new EdgeLocation(-1, -1, EdgeDirection.North);
        new BuildRoadCommand(game, player2, location, free).execute();
        game.setGameState(GameState.Playing);
        player2.addResources(new ResourceBank(1, 1, 1, 1, 0));
        game.getMap().placeSettlement(player2.buildSettlement(false), new VertexLocation(0, 0, VertexDirection.West));
        game.setGameState(GameState.FirstRound);
        game.setCurrentPlayer(player);

        location = new EdgeLocation(0, 0, EdgeDirection.South);
        new BuildRoadCommand(game, player, location, free).execute();
        assertTrue("Player victory points should be 0.", player.getVictoryPoints() == 0);
    }

    private IGame initAGame(String jsonTestFile) throws Exception {
        String emptyBoardJSON = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(jsonTestFile)));
        ModelInitializer initModel = new ModelInitializer();
        initModel.initializeClientModel(emptyBoardJSON, 0);
        return GameModelFacade.instance().getGame();
    }
}
