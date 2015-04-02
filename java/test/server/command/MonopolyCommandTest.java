package server.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.definitions.ResourceType;
import shared.model.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by jeffreybacon on 11/22/14.
 */
public class MonopolyCommandTest {
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
    public void testPlayMonopoly() throws Exception {
        game.setResourceBank(new ResourceBank());
        game.getPlayerByIndex(0).removeResources(new ResourceBank(1, 1, 1, 1, 1));
        new MonopolyCommand(game, player, ResourceType.BRICK).execute();
        assertTrue("Player1 should have 19 brick", game.getPlayerByIndex(0).getResources().getCount(ResourceType.BRICK) == 19);
        assertTrue("Player2 should have no brick", game.getPlayerByIndex(1).getResources().getCount(ResourceType.BRICK) == 0);
        assertTrue("Player3 should have no brick", game.getPlayerByIndex(2).getResources().getCount(ResourceType.BRICK) == 0);
        assertTrue("Player4 should have no brick", game.getPlayerByIndex(3).getResources().getCount(ResourceType.BRICK) == 0);
    }

    @Test(expected = IllegalCommandException.class)
    public void testPlayInvalidMonopoly() throws Exception {
        game.setGameState(GameState.Rolling);
        new MonopolyCommand(game, player, ResourceType.BRICK).execute();
    }

    private IGame initAGame(String jsonTestFile) throws Exception {
        String emptyBoardJSON = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(jsonTestFile)));
        ModelInitializer initModel = new ModelInitializer();
        initModel.initializeClientModel(emptyBoardJSON, 0);
        return GameModelFacade.instance().getGame();
    }
}
