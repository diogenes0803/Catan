package server.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.model.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by jeffreybacon on 11/24/14.
 */
public class OfferTradeCommandTest {
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
    public void testOfferTrade() throws Exception {
        IPlayer player2 = game.getPlayerByIndex(1);
        IResourceBank offer = new ResourceBank(1, -1, 0, 0, 0);
        new OfferTradeCommand(game, player, player2, offer).execute();
        assertTrue("Trade offer should be set", game.getTradeOffer().getOffer().equals(offer));
    }

    @Test(expected = IllegalCommandException.class)
    public void testInvalidOfferTrade() throws Exception {
        new OfferTradeCommand(game, player, player, new ResourceBank(-1, 1, 0, 0, 0)).execute();
    }

    private IGame initAGame(String jsonTestFile) throws Exception {
        String emptyBoardJSON = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(jsonTestFile)));
        ModelInitializer initModel = new ModelInitializer();
        initModel.initializeClientModel(emptyBoardJSON, 0);
        return GameModelFacade.instance().getGame();
    }
}
