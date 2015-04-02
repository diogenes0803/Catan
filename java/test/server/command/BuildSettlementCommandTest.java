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
public class BuildSettlementCommandTest {
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
    public void testPlaceSettlement() throws Exception {
        game.getMap().placeRoad(player.buildRoad(true), new EdgeLocation(0, 0, EdgeDirection.NorthWest));
        game.setGameState(GameState.Playing);
        game.getResourceBank().subtract(Prices.SETTLEMENT);
        player.addResources(Prices.SETTLEMENT);
        VertexLocation location = new VertexLocation(0, 0, VertexDirection.West);
        boolean free = false;
        new BuildSettlementCommand(game, player, location, free).execute();
        assertTrue("Settlement should be placed", game.getMap().getTownAt(location) != null);
        assertTrue("Player should have 1 victory point", player.getVictoryPoints() == 1);
    }

    @Test
    public void testPlaceFreeSettlement() throws Exception {
        game.getMap().placeRoad(player.buildRoad(true), new EdgeLocation(0, 0, EdgeDirection.NorthWest));
        VertexLocation location = new VertexLocation(0, 0, VertexDirection.West);
        boolean free = true;
        new BuildSettlementCommand(game, player, location, free).execute();
        assertTrue("Settlement should be placed", game.getMap().getTownAt(location) != null);
        assertTrue("Player should have 1 victory point", player.getVictoryPoints() == 1);
    }

    @Test(expected = IllegalCommandException.class)
    public void testPlaceInvalidSettlement() throws Exception {
        VertexLocation location = new VertexLocation(0, 0, VertexDirection.West);
        boolean free = false;
        new BuildSettlementCommand(game, player, location, free).execute();
        assertTrue("Player should have no victory points", player.getVictoryPoints() == 0);
    }

    private IGame initAGame(String jsonTestFile) throws Exception {
        String emptyBoardJSON = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(jsonTestFile)));
        ModelInitializer initModel = new ModelInitializer();
        initModel.initializeClientModel(emptyBoardJSON, 0);
        return GameModelFacade.instance().getGame();
    }
}
