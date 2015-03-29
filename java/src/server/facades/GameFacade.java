package server.facades;

import shared.communicator.AddAIRequestParams;
import shared.communicator.GameModelParam;
import shared.models.Game;
import shared.models.ModelException;

/**
 * A facade to support /game operations
 * @author kentcoble
 */

public class GameFacade implements Facade {
    
    public GameFacade() {

    }

    /**
     * Fetch the model
     * Swagger URL Equivalent: /game/model (get request)
     *
     * @param param an integer with the id of the game to get the model of
     * @return Game object containing a pointer to the model
     */
    //@Override
    public Game model(GameModelParam param) {
    	return null;
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
        return false;
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
