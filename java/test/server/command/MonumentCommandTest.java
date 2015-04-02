package server.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.model.IGame;
import shared.model.IPlayer;
import shared.model.ModelInitializer;
import shared.model.GameState;
import shared.model.GameModelFacade;

import static org.junit.Assert.assertTrue;

/**
 * Created by jeffreybacon on 11/22/14.
 */
public class MonumentCommandTest {
    IGame game;
    IPlayer player;

    @Before
    public void setUp() throws Exception {
        game = initAGame("sample/dev_cards.json");
        player = game.getPlayerByIndex(0);
        game.setCurrentPlayer(player);
    }

    @After
    public void tearDown() throws Exception {
        game = null;
        player = null;
    }

    @Test
    public void testPlayMonument() throws Exception {
        assertTrue("Player1 should have 0 victory points", game.getPlayerByIndex(0).calculateVictoryPoints() == 0);
        new MonumentCommand(game, player).execute();
        assertTrue("Player1 should have 1 victory point", game.getPlayerByIndex(0).calculateVictoryPoints() == 1);
    }

    @Test(expected = IllegalCommandException.class)
    public void testPlayInvalidMonument() throws Exception {
        game.setGameState(GameState.Rolling);
        new MonumentCommand(game, player).execute();
    }

    private IGame initAGame(String jsonTestFile) throws Exception {
        String emptyBoardJSON = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(jsonTestFile)));
        ModelInitializer initModel = new ModelInitializer();
        initModel.initializeClientModel(emptyBoardJSON, 0);
        return GameModelFacade.instance().getGame();
    }
}
