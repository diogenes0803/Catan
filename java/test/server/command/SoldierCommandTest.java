package server.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.locations.HexLocation;
import shared.model.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by jeffreybacon on 11/24/14.
 */
public class SoldierCommandTest {
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
    public void testPlaySoldier() throws Exception {
        game.setResourceBank(new ResourceBank(12, 13, 11, 13, 12));
        assertTrue("Player1 should have no soldiers", game.getPlayerByIndex(0).getSoldiers() == 0);
        new SoldierCommand(game, player, game.getPlayerByIndex(1), new HexLocation(-1, -1)).execute();
        assertTrue("Player1 should have 1 soldier", game.getPlayerByIndex(0).getSoldiers() == 1);
    }

    @Test(expected = IllegalCommandException.class)
    public void testPlayInvalidSoldier() throws Exception {
        new SoldierCommand(game, player, game.getPlayerByIndex(1), new HexLocation(0, -2)).execute();
    }

    private IGame initAGame(String jsonTestFile) throws Exception {
        String emptyBoardJSON = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(jsonTestFile)));
        ModelInitializer initModel = new ModelInitializer();
        initModel.initializeClientModel(emptyBoardJSON, 0);
        return GameModelFacade.instance().getGame();
    }
}
