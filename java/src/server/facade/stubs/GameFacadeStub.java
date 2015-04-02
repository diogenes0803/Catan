package server.facade.stubs;

import server.facade.IGameFacade;
import shared.communication.AddAIRequestParams;
import shared.communication.GameModelParam;
import shared.model.Game;
import shared.model.IGame;
import shared.model.ModelException;


public class GameFacadeStub implements IGameFacade {

    @Override
    public IGame model(GameModelParam theModel) throws ModelException {
        return null;
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
    public boolean addAI(AddAIRequestParams addAI) throws ModelException {
        return false;
    }


    @Override
    public String[] listAI() {
        return new String[0];
    }
}
