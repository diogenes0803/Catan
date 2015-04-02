package server.facade;

import shared.communication.AddAIRequestParams;
import shared.communication.GameModelParam;
import shared.model.Game;
import shared.model.IGame;
import shared.model.ModelException;


public interface IGameFacade {


    public IGame model(GameModelParam theModel) throws ModelException;


    public Game reset();


    public Game postCommands();


    public Game getCommands();


    public boolean addAI(AddAIRequestParams addAI) throws ModelException;


    public String[] listAI();
}
