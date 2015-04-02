package server.facade.stubs;

import server.facade.IJoinGameFacade;
import shared.communication.*;
import shared.model.ModelException;


public class JoinGameFacadeStub implements IJoinGameFacade {

    @Override
    public GameInfo[] list() {
        return new GameInfo[0];
    }


    @Override
    public GameInfo create(CreateGameRequestParams createGame) throws ModelException {
        return null;
    }


    @Override
    public Integer join(JoinGameRequestParams joinGame) throws ModelException {
        return null;
    }


    @Override
    public void save(SaveGameRequestParams saveGame) {
    }


    @Override
    public void load(LoadGameRequestParams loadGame) {
    }
}
