package server.persistence;

import server.command.ICommand;
import shared.model.IGame;


public interface ICommandsDAO {

    public void saveCommand(ICommand command) throws PersistenceException;


    public void loadCommands(IGame game) throws PersistenceException;
}
