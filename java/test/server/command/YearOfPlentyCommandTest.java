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
public class YearOfPlentyCommandTest {
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
    public void testPlayYearOfPlenty() throws Exception {
        game.setResourceBank(new ResourceBank(4, 4, 4, 4, 4));
        game.getPlayerByIndex(0).removeResources(new ResourceBank(5, 5, 5, 5, 5));
        new YearOfPlentyCommand(game, player, ResourceType.WOOD, ResourceType.BRICK).execute();
        assertTrue("Player1 should have 1 wood", game.getPlayerByIndex(0).getResources().getCount(ResourceType.WOOD) == 1);
        assertTrue("Player1 should have 1 brick", game.getPlayerByIndex(0).getResources().getCount(ResourceType.BRICK) == 1);
    }

    @Test(expected = IllegalCommandException.class)
    public void testPlayInvalidYearOfPlenty() throws Exception {
        game.setGameState(GameState.Rolling);
        new YearOfPlentyCommand(game, player, ResourceType.WOOD, ResourceType.BRICK).execute();
    }

    private IGame initAGame(String jsonTestFile) throws Exception {
        String emptyBoardJSON = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(jsonTestFile)));
        ModelInitializer initModel = new ModelInitializer();
        initModel.initializeClientModel(emptyBoardJSON, 0);
        return GameModelFacade.instance().getGame();
    }
}
