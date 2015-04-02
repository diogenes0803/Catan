package server.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.model.*;

import static org.junit.Assert.*;

public class DiscardCardsCommandTest {

    IGame game;
    IPlayer player;

    @Before
    public void setUp() throws Exception {

        game = initAGame("sample/discards_command_test.json");
        player = game.getPlayerByIndex(0);
    }

    @Test
    public void testDiscardingState() throws Exception {

        // Make sure state is in robbing state
        boolean isExceptionThrown = false;
        try {
            new DiscardCardsCommand(game, player, new ResourceBank(1, 1, 1, 1, 1)).execute();
        } catch (IllegalCommandException ex) {
            isExceptionThrown = true;
        }
        assertTrue(isExceptionThrown);

        isExceptionThrown = false;
        game.setGameState(GameState.Discarding);
        try {
            new DiscardCardsCommand(game, player, new ResourceBank(1, 1, 1, 1, 1)).execute();
        } catch (Exception ex) {
            isExceptionThrown = true;
        }
        assertFalse(isExceptionThrown);
    }

    @Test
    public void testPlayerConditions() throws Exception {

        // Make sure player can afford what they discarded
        game.setGameState(GameState.Discarding);
        boolean isExceptionThrown = false;
        try {
            new DiscardCardsCommand(game, player, new ResourceBank(10, 10, 10, 10, 10)).execute();
        } catch (IllegalCommandException ex) {
            isExceptionThrown = true;
        }
        assertTrue(isExceptionThrown);

        // Make sure player needs to discard
        game.setGameState(GameState.Discarding);
        isExceptionThrown = false;
        try {
            new DiscardCardsCommand(game, player, new ResourceBank(2, 2, 2, 3, 3)).execute();
        } catch (IllegalCommandException ex) {
            isExceptionThrown = true;
        }
        assertFalse(isExceptionThrown);

        // Make sure player cannot discard
        player.setDiscarded(false);
        game.setGameState(GameState.Discarding);
        isExceptionThrown = false;
        try {
            new DiscardCardsCommand(game, player, new ResourceBank(2, 1, 1, 1, 1)).execute();
        } catch (IllegalCommandException ex) {
            isExceptionThrown = true;
        }
        assertFalse(isExceptionThrown);

        player.setDiscarded(false);
        game.setGameState(GameState.Discarding);
        isExceptionThrown = false;
        try {
            new DiscardCardsCommand(game, player, new ResourceBank(2, 1, 1, 1, 1)).execute();
        } catch (IllegalCommandException ex) {
            isExceptionThrown = true;
        }
        assertTrue(isExceptionThrown);
    }

    @Test
    public void testDiscarding() throws Exception {

        game.setGameState(GameState.Discarding);
        boolean isExceptionThrown = false;
        try {
            new DiscardCardsCommand(game, player, new ResourceBank(2, 2, 2, 3, 3)).execute();
        } catch (IllegalCommandException ex) {
            isExceptionThrown = true;
        }
        assertFalse(isExceptionThrown);

        // Make sure player actually discarded the cards
        assertEquals(player.getResources().getBrick(), 3);
        assertEquals(player.getResources().getWood(), 3);
        assertEquals(player.getResources().getSheep(), 3);
        assertEquals(player.getResources().getWheat(), 2);
        assertEquals(player.getResources().getOre(), 2);

        // Make sure the game has the cards back.
        assertEquals(game.getResourceBank().getBrick(), 2);
        assertEquals(game.getResourceBank().getWood(), 2);
        assertEquals(game.getResourceBank().getSheep(), 2);
        assertEquals(game.getResourceBank().getWheat(), 3);
        assertEquals(game.getResourceBank().getOre(), 3);
    }
    @After
    public void tearDown() throws Exception {

        game = null;
        player = null;
    }

    private IGame initAGame(String jsonTestFile) throws Exception {
        String emptyBoardJSON = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(jsonTestFile)));
        ModelInitializer initModel = new ModelInitializer();
        initModel.initializeClientModel(emptyBoardJSON, 0);
        return GameModelFacade.instance().getGame();
    }
}