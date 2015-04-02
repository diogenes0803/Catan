package server.facade;

import shared.communication.AddAIRequestParams;
import shared.communication.GameModelParam;
import shared.model.*;


public class GameFacade implements IGameFacade {
    private IGameManager local_gameManager;

    public GameFacade(IGameManager local_gameManager) {
        this.local_gameManager = local_gameManager;
    }


    @Override
    public IGame model(GameModelParam param) throws ModelException {
        return local_gameManager.getGame(param.getGameId());
    }


    @Override
    public Game reset() {
        return null;
    }


    @Override
    public Game postCommands() {
        return null;
    }


    @Override
    public Game getCommands() {
        return null;
    }


    @Override
    public boolean addAI(AddAIRequestParams params) throws ModelException {
        // TODO: this is temporary test code
        IGame game = local_gameManager.getGame(params.getGameId());
        game.joinGame(new User("Hal 9000", "", 9000), shared.definitions.CatanColor.RED);
        game.joinGame(new User("GLaDOS", "", 1234567), shared.definitions.CatanColor.BLUE);
        game.joinGame(new User("The MCP", "", 1980), shared.definitions.CatanColor.WHITE);
        return true;
    }


    @Override
    public String[] listAI() {
        return new String[]{"Hal 9000", "The MCP", "GLaDOS"};
    }
}
