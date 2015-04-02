package shared.model;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author Wyatt
 */
public class LargestArmyTest {
    private IGame m_game;
    private IModelInitializer m_modelInitializer;
    private String m_clientModel;

    @Before
    public void setUp() throws Exception {
        m_modelInitializer = new ModelInitializer();
        m_clientModel = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("sample/test_1.json")));
        m_modelInitializer.initializeClientModel(m_clientModel, 0);
        m_game = GameModelFacade.instance().getGame();
    }

    @Test
    public void testLargestArmy() throws Exception {
        assertNull("No one should have largest army yet.", m_game.getLargestArmy());
        m_game.calculateVictoryPoints();
        assertNull("No one should have largest army yet.", m_game.getLargestArmy());

        // give player 0 largest army with three soldiers
        m_game.getPlayerByIndex(0).giveSoldier();
        m_game.getPlayerByIndex(0).giveSoldier();
        m_game.getPlayerByIndex(2).giveSoldier();
        m_game.calculateVictoryPoints();
        assertNull("No one should has 3 soldiers yet.", m_game.getLargestArmy());

        m_game.getPlayerByIndex(0).giveSoldier();
        m_game.calculateVictoryPoints();
        assertEquals(m_game.getPlayerByIndex(0), m_game.getLargestArmy());

        // steal the largest army from player 0 with four soldiers
        m_game.getPlayerByIndex(1).giveSoldier();
        m_game.getPlayerByIndex(1).giveSoldier();
        m_game.getPlayerByIndex(1).giveSoldier();
        m_game.calculateVictoryPoints();
        assertEquals(m_game.getPlayerByIndex(0), m_game.getLargestArmy());

        m_game.getPlayerByIndex(1).giveSoldier();
        m_game.calculateVictoryPoints();
        assertEquals(m_game.getPlayerByIndex(1), m_game.getLargestArmy());

        // player 0 wins it back with five soldiers
        m_game.getPlayerByIndex(0).giveSoldier();
        m_game.calculateVictoryPoints();
        assertEquals(m_game.getPlayerByIndex(1), m_game.getLargestArmy());

        m_game.getPlayerByIndex(0).giveSoldier();
        m_game.calculateVictoryPoints();
        assertEquals(m_game.getPlayerByIndex(0), m_game.getLargestArmy());

        // player 3 comes out of nowhere and takes largest army with 6!
        m_game.getPlayerByIndex(3).giveSoldier();
        m_game.getPlayerByIndex(3).giveSoldier();
        m_game.getPlayerByIndex(3).giveSoldier();
        m_game.getPlayerByIndex(3).giveSoldier();
        m_game.getPlayerByIndex(3).giveSoldier();
        m_game.getPlayerByIndex(3).giveSoldier();
        m_game.calculateVictoryPoints();
        assertEquals("Player 3 should now have largest army with 6", m_game.getPlayerByIndex(3), m_game.getLargestArmy());
    }
}
