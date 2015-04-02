package server.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.locations.HexLocation;
import shared.model.*;

import static org.junit.Assert.*;

public class RobPlayerCommandTest {

    IGame game;

    @Before
    public void setUp() throws Exception {
        game = initAGame("sample/robbing_player_test.json");
    }

    @Test
    public void testPreConditions() throws Exception{

        game.setGameState(GameState.Robbing);
        HexLocation robberLocation = game.getMap().getRobber();

        // Test that you can't place a robber on the same location
        boolean isExceptionThrown = false;
        try {
            new RobPlayerCommand(game, game.getPlayerByIndex(0), game.getPlayerByIndex(1), robberLocation).execute();
        } catch (IllegalCommandException ex) {
            isExceptionThrown = true;
        }
        assertTrue(isExceptionThrown);

        // Test that the victim passed in is not null
        isExceptionThrown = false;
        try {
            new RobPlayerCommand(game, game.getPlayerByIndex(0), game.getPlayerByIndex(1), new HexLocation(2, -2)).execute();
        } catch (IllegalCommandException ex) {
            isExceptionThrown = true;
        }
        assertTrue(isExceptionThrown);

        // Test that the victim has resources
        isExceptionThrown = false;
        try {
            new RobPlayerCommand(game, game.getPlayerByIndex(0), game.getPlayerByIndex(1), new HexLocation(2, -2)).execute();
        } catch (IllegalCommandException ex) {
            isExceptionThrown = true;
        }
        assertTrue(isExceptionThrown);
    }

    @Test
    public void testRobbing() throws Exception {

        game.setGameState(GameState.Robbing);

        HexLocation endLocation = new HexLocation(2, -2);
        int robbingPlayerCardCount = game.getPlayerByIndex(0).getResources().getCount();
        int victimCardCount = game.getPlayerByIndex(2).getResources().getCount();

        new RobPlayerCommand(game, game.getPlayerByIndex(0), game.getPlayerByIndex(2), new HexLocation(2, -2)).execute();

        // Test that the robber is moved
        assertEquals(game.getMap().getRobber(), endLocation);

        // Test that the robbing player has one more card
        assertEquals(game.getPlayerByIndex(0).getResources().getCount(), ++robbingPlayerCardCount);

        // Test that the victim has one less card
        assertEquals(game.getPlayerByIndex(0).getResources().getCount(), ++victimCardCount);
    }

    @After
    public void tearDown() throws Exception {

    }

    private IGame initAGame(String jsonTestFile) throws Exception {
        String emptyBoardJSON = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(jsonTestFile)));
        ModelInitializer initModel = new ModelInitializer();
        initModel.initializeClientModel(emptyBoardJSON, 0);
        return GameModelFacade.instance().getGame();
    }

}

/**


 public RobPlayerCommand(IGame game, IPlayer player, IPlayer victim, HexLocation location) throws IllegalCommandException {
 super(game, player, "moved the robber" + (victim != null ? " and robbed " + victim.getName() : ""));

 if (!getGame().getMap().canPlaceRobber(location)) {
 throw new IllegalCommandException(("Player " + player.getName() + "tried to place the robber " +
 "on hex location " + location.toString() + " but the robber is already there"));
 }

 if (victim != null && victim.getResources().getCount() == 0) {
 throw new IllegalCommandException("Player " + player.getName() + " tried to rob from " + victim.getName() + " but they had no cards");
 }

 m_victim = victim;
 m_hexLocation = location;
 }

 /**
 * Takes a random card from the robbed player's hand
 * and moves that card to the robbing player's hand.

public void performAction() {
    getGame().robPlayer(getPlayerByIndex(), m_victim, m_hexLocation);

    assert (getGame().verifyResourceAmount());

 **/