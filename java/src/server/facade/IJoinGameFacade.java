package server.facade;

import server.command.IllegalCommandException;
import shared.communication.*;
import shared.model.ModelException;

import java.io.IOException;


public interface IJoinGameFacade {


    public GameInfo[] list();


    public GameInfo create(CreateGameRequestParams createGame) throws ModelException;


    public Integer join(JoinGameRequestParams joinGame) throws ModelException, IllegalCommandException;


    public void save(SaveGameRequestParams saveGame) throws IOException, ModelException;


    public void load(LoadGameRequestParams loadGame) throws IOException;
}
