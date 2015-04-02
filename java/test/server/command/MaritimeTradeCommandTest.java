package server.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.definitions.ResourceType;
import shared.model.*;
import shared.model.GameModelFacade;
import shared.model.IGame;
import shared.model.ModelInitializer;

import static org.junit.Assert.*;

public class MaritimeTradeCommandTest {

    IGame game;

    @Before
    public void setUp() throws Exception {

        game = initAGame("sample/maritime_trade_test.json");
    }

    @Test
    public void testPreConditions() throws Exception {

        // Test trying to trade when it is not your turn.
        game.setGameState(GameState.Playing);
        boolean isExceptionThrown = false;
        try {
            new MaritimeTradeCommand(game, game.getPlayerByIndex(0), ResourceType.BRICK, ResourceType.ORE, 4);
        } catch (IllegalCommandException ex) {
            isExceptionThrown = true;
        }
        assertTrue(isExceptionThrown);

        isExceptionThrown = false;
        try {
            new MaritimeTradeCommand(game, game.getPlayerByIndex(2), ResourceType.BRICK, ResourceType.ORE, 4).execute();
        } catch (IllegalCommandException ex) {
            isExceptionThrown = true;
        }
        assertFalse(isExceptionThrown);

        // Test 4:1 trade when you don't have enough resources to do it.
        isExceptionThrown = false;
        try {
            new MaritimeTradeCommand(game, game.getPlayerByIndex(2), ResourceType.BRICK, ResourceType.ORE, 4);
        } catch (IllegalCommandException ex) {
            isExceptionThrown = true;
        }
        assertTrue(isExceptionThrown);

        // Test trading 4:1 of the same resource.
        isExceptionThrown = false;
        try {
            new MaritimeTradeCommand(game, game.getPlayerByIndex(2), ResourceType.ORE, ResourceType.WOOD, 4).execute();
        } catch (IllegalCommandException ex) {
            isExceptionThrown = true;
        }
        assertFalse(isExceptionThrown);

        // Test 3:1 when you don't have a 3:1 port.
        game.setCurrentPlayer(game.getPlayerByIndex(0));
        isExceptionThrown = false;
        try {
            new MaritimeTradeCommand(game, game.getPlayerByIndex(0), ResourceType.WHEAT, ResourceType.WOOD, 3);
        } catch (IllegalCommandException ex) {
            isExceptionThrown = true;
        }
        assertTrue(isExceptionThrown);

        // Test 3:1 when you don't have enough resources.
        game.setCurrentPlayer(game.getPlayerByIndex(3));
        isExceptionThrown = false;
        try {
            new MaritimeTradeCommand(game, game.getPlayerByIndex(3), ResourceType.SHEEP, ResourceType.WOOD, 3);
        } catch (IllegalCommandException ex) {
            isExceptionThrown = true;
        }
        assertTrue(isExceptionThrown);

        // Test 2:1 when you don't have a 2:1 port.
        isExceptionThrown = false;
        try {
            new MaritimeTradeCommand(game, game.getPlayerByIndex(3), ResourceType.ORE, ResourceType.WOOD, 2);
        } catch (IllegalCommandException ex) {
            isExceptionThrown = true;
        }
        assertTrue(isExceptionThrown);

        // Test 2:1 when you don't have enough resources.
        game.setCurrentPlayer(game.getPlayerByIndex(2));
        isExceptionThrown = false;
        try {
            new MaritimeTradeCommand(game, game.getPlayerByIndex(2), ResourceType.SHEEP, ResourceType.WOOD, 2);
        } catch (IllegalCommandException ex) {
            isExceptionThrown = true;
        }
        assertTrue(isExceptionThrown);

    }

    @Test
    public void testMaritimeTrade() throws Exception {

        // Test that the correct resource have been taken and given in a 4:1
        game.setCurrentPlayer(game.getPlayerByIndex(1));
        new MaritimeTradeCommand(game, game.getPlayerByIndex(1), ResourceType.WOOD, ResourceType.SHEEP, 4).execute();
        assertEquals(game.getPlayerByIndex(1).getResources().getWood(), 0);
        assertEquals(game.getPlayerByIndex(1).getResources().getSheep(), 5);
        assertEquals(game.getResourceBank().getWood(), 7);
        assertEquals(game.getResourceBank().getSheep(), 7);

        // Test that the correct resource have been taken and given in a 3:1
        new MaritimeTradeCommand(game, game.getPlayerByIndex(1), ResourceType.BRICK, ResourceType.ORE, 3).execute();
        assertEquals(game.getPlayerByIndex(1).getResources().getBrick(), 1);
        assertEquals(game.getPlayerByIndex(1).getResources().getOre(), 5);
        assertEquals(game.getResourceBank().getBrick(), 6);
        assertEquals(game.getResourceBank().getOre(), 2);

        // Test that the correct resource have been taken and given in a 2:1
        game.setCurrentPlayer(game.getPlayerByIndex(0));
        new MaritimeTradeCommand(game, game.getPlayerByIndex(0), ResourceType.WOOD, ResourceType.ORE, 2).execute();
        assertEquals(game.getPlayerByIndex(0).getResources().getWood(), 2);
        assertEquals(game.getPlayerByIndex(0).getResources().getOre(), 5);
        assertEquals(game.getResourceBank().getWood(), 9);
        assertEquals(game.getResourceBank().getOre(), 1);
    }

    @After
    public void tearDown() throws Exception {

        game = null;
    }

    private IGame initAGame(String jsonTestFile) throws Exception {
        String emptyBoardJSON = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(jsonTestFile)));
        ModelInitializer initModel = new ModelInitializer();
        initModel.initializeClientModel(emptyBoardJSON, 5);
        return GameModelFacade.instance().getGame();
    }


}