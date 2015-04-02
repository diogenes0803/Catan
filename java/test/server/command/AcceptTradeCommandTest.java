package server.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.definitions.ResourceType;
import shared.model.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by jeffreybacon on 11/24/14.
 */
public class AcceptTradeCommandTest {
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
    public void testAcceptTrade() throws Exception {
        game.setResourceBank(new ResourceBank(12, 13, 11, 13, 12));
        IPlayer player2 = game.getPlayerByIndex(1);
        game.setCurrentPlayer(player2);
        IResourceBank offer = new ResourceBank(-1, 1, 0, 0, 0);
        ITradeOffer tradeOffer = new TradeOffer(player2, player, offer);
        game.setTradeOffer(tradeOffer);
        new AcceptTradeCommand(game, player, true).execute();
        assertTrue("Player1 should have 4 wood", game.getPlayerByIndex(0).getResources().getCount(ResourceType.WOOD) == 4);
        assertTrue("Player1 should have 6 brick", game.getPlayerByIndex(0).getResources().getCount(ResourceType.BRICK) == 6);
        assertTrue("Player2 should have 1 wood", game.getPlayerByIndex(1).getResources().getCount(ResourceType.WOOD) == 1);
        assertTrue("Player2 should have no brick", game.getPlayerByIndex(1).getResources().getCount(ResourceType.BRICK) == 0);
    }

    @Test(expected = IllegalCommandException.class)
    public void testInvalidAcceptTrade() throws Exception {
        new AcceptTradeCommand(game, player, true).execute();
    }

    private IGame initAGame(String jsonTestFile) throws Exception {
        String emptyBoardJSON = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(jsonTestFile)));
        ModelInitializer initModel = new ModelInitializer();
        initModel.initializeClientModel(emptyBoardJSON, 0);
        return GameModelFacade.instance().getGame();
    }
}
