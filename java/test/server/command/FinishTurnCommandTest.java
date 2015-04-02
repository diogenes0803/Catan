package server.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.definitions.DevCardType;
import shared.model.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Spencer Weight - 11/24/2014.
 */
public class FinishTurnCommandTest {
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
    public void testIncrementVersion() throws Exception {
        // get the version of the model
        int preModelVersion = game.getVersion();

        // call finish turn
        new FinishTurnCommand(game, player).execute();

        // the version number should have increased
        int postModelVersion = game.getVersion();

        // check if post is one more than pre
        assertEquals("The version number did not increment by one", preModelVersion, postModelVersion - 1);
    }

    @Test
    public void testMoveDevCards() throws Exception{
        // purchase a dev card
        DevCardType dc = DevCardType.MONUMENT;
        int preNewDCSize = 0;
        while(dc == DevCardType.MONUMENT) {
            dc = game.getDevCards().drawCard(game.getRandom());
            player.getNewDevCards().add(dc);
            preNewDCSize = player.getNewDevCards().getCount();
        }

        // call finish turn
        new FinishTurnCommand(game, player).execute();

        // check if the dev card has moved
        int postNewDCSize = player.getNewDevCards().getCount();

        assertTrue("No dev cards moved from the new hand to the old", postNewDCSize < preNewDCSize);
    }

    private IGame initAGame(String jsonTestFile) throws Exception {
        String emptyBoardJSON = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(jsonTestFile)));
        ModelInitializer initModel = new ModelInitializer();
        initModel.initializeClientModel(emptyBoardJSON, 0);
        return GameModelFacade.instance().getGame();
    }
}
