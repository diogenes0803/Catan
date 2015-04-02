package server.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.model.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by jeffreybacon on 11/24/14.
 */
public class RollNumberCommandTest {
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
    public void testRollNumber() throws Exception {
        game.setGameState(GameState.Rolling);
        new RollNumberCommand(game, player, 7).execute();
        assertTrue("Game state should be either Robbing or Discarding", game.getGameState() == GameState.Robbing || game.getGameState() == GameState.Discarding);
        game.setGameState(GameState.Rolling);
        new RollNumberCommand(game, player, 6).execute();
        assertTrue("Game state should be Playing", game.getGameState() == GameState.Playing);
    }

    @Test(expected = IllegalCommandException.class)
    public void testInvalidRollNumber() throws Exception {
        new RollNumberCommand(game, player, 1).execute();
    }

    private IGame initAGame(String jsonTestFile) throws Exception {
        String emptyBoardJSON = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(jsonTestFile)));
        ModelInitializer initModel = new ModelInitializer();
        initModel.initializeClientModel(emptyBoardJSON, 0);
        return GameModelFacade.instance().getGame();
    }
}
