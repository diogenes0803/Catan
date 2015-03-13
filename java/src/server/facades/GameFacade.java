package server.facades;

import shared.communicator.AddAIRequestParams;
import shared.communicator.GameModelParam;
import shared.models.*;

/**
 * A facade to support /game operations
 * @author kentcoble
 */

public class GameFacade {

    private GameManager m_gameManager;

    public GameFacade(GameManager m_gameManager) {
        this.m_gameManager = m_gameManager;
    }

    /**
     * Fetch the model
     * Swagger URL Equivalent: /game/model (get request)
     *
     * @param param an integer with the id of the game to get the model of
     * @return Game object containing a pointer to the model
     */
    //@Override
    public Game model(GameModelParam param) throws ModelException {
        //return m_gameManager.getGame(param.getGameId());
        return m_gameManager.getGame();
    }

    /**
     * Resets the current game
     * (Optional)
     * Swagger URL Equivalent: /game/reset
     *
     * @return Game object containing a pointer to the model
     */
    public Game reset() {
        return null;
    }

    /**
     * Send a list of moves to the server
     * (Optional)
     * Swagger URL Equivalent: /game/commands (post request)
     *
     * @return Game object containing a pointer to the model
     */
    public Game postCommands() {
        return null;
    }

    /**
     * Fetch a list of commands for the current game
     * (Optional)
     * Swagger URL Equivalent: /game/commands (get request)
     *
     * @return Game object containing a pointer to the model
     */
    public Game getCommands() {
        return null;
    }

    /**
     * Add an AI player to the game
     * (Optional)
     * Swagger URL Equivalent: /game/addAI
     *
     * @param params JSON wrapper containing the parameters for adding an AI player
     * @return boolean true/false depending on if AI is added or not
     */

    //@Override
    public boolean addAI(AddAIRequestParams params) throws ModelException {

        //Game game = m_gameManager.getGame(params.getGameId());
        //game.joinGame(new User("Hal 9000", "", 9000), shared.definitions.CatanColor.RED);
        //game.joinGame(new User("GLaDOS", "", 1234567), shared.definitions.CatanColor.BLUE);
        //game.joinGame(new User("The MCP", "", 1980), shared.definitions.CatanColor.WHITE);
        return true;
    }

    /**
     * Get a list of the AI Players
     * (Optional)
     * Swagger URL Equivalent: /game/listAI (get request)
     *
     * @return Game object containing a pointer to the model
     */

    public String[] listAI() {
        return new String[]{"Hal 9000", "The MCP", "GLaDOS"};
    }
}
