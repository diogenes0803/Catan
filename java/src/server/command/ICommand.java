package server.command;

import shared.model.IGame;
import shared.model.ModelException;

import java.io.Serializable;


public interface ICommand extends Serializable {

    public void execute();


    public void setGameAndPlayers(IGame game) throws ModelException;


    public IGame getGame();
}
