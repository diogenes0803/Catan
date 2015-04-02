package server.commands;

import server.Server;
import server.model.ServerModel;
import shared.communicator.AddAIParams;

/**
 * 
 * @author oxbor
 *
 */
public class AddAICommand implements Command {

    AddAIParams params;
    int gameID;

    public AddAICommand(AddAIParams params, int gameID){
        this.params = params;
        this.gameID = gameID;
    }

    @Override
	public void execute() {

        ServerModel game = Server.models.get(gameID);


	}

}
