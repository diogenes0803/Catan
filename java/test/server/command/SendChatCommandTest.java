package server.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.model.*;

import static org.junit.Assert.*;

public class SendChatCommandTest {

    IGame game;
    IPlayer player;

    @Before
    public void setUp() throws Exception {
        game = initAGame("sample/empty_board.json");
        player = game.getPlayerByIndex(0);
    }

    @Test
    public void SendChatTest() throws Exception {

        int chatHistorySizeBefore = game.getChatHistory().getLogEntries().size();
        new SendChatCommand(game, player, "I sent a chat!").execute();
        int chatHistorySizeAfter = game.getChatHistory().getLogEntries().size();

        assertNotEquals(chatHistorySizeBefore, chatHistorySizeAfter);
        assertEquals(game.getChatHistory().getLogEntries().get(0).getColor(), player.getColor());
        assertEquals(game.getChatHistory().getLogEntries().get(0).getMessage(), "I sent a chat!");
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