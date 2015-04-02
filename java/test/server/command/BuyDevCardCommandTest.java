package server.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.model.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by jeffreybacon on 11/24/14.
 */
public class BuyDevCardCommandTest {
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
    public void testBuyDevCard() throws Exception {
        game.setResourceBank(new ResourceBank());
        game.getPlayerByIndex(0).removeResources(new ResourceBank(1, 1, 1, 1, 1));
        assertTrue("Player1 should have 15 dev cards", game.getPlayerByIndex(0).getAllDevCards().getCount() == 15);
        new BuyDevCardCommand(game, player).execute();
        assertTrue("Player1 should have 16 dev cards", game.getPlayerByIndex(0).getAllDevCards().getCount() == 16);
    }

    @Test(expected = IllegalCommandException.class)
    public void testInvalidBuyDevCard() throws Exception {
        game.setGameState(GameState.Rolling);
        new BuyDevCardCommand(game, player).execute();
    }

    private IGame initAGame(String jsonTestFile) throws Exception {
        String emptyBoardJSON = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(jsonTestFile)));
        ModelInitializer initModel = new ModelInitializer();
        initModel.initializeClientModel(emptyBoardJSON, 0);
        return GameModelFacade.instance().getGame();
    }
}
