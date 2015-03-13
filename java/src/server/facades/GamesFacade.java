/**
 * 
 */
package server.facades;

import shared.communicator.CreateGameParams;
import shared.communicator.CreateGameResults;
import shared.communicator.JoinGameParams;
import shared.communicator.JoinGameResults;
import shared.communicator.LoadGameParams;
import shared.communicator.LoadGameResults;
import shared.communicator.SaveGameParams;
import shared.communicator.SaveGameResults;

/**
 * @author campbeln
 *
 */
public class GamesFacade implements Facade {

public GamesFacade(){}
	
	public void list() {
		
	}
	
	public CreateGameResults create(CreateGameParams thisParams) {
		CreateGameResults thisResult = new CreateGameResults();
		return thisResult;
	}
	
	public JoinGameResults join(JoinGameParams thisParams) {
		JoinGameResults thisResult = new JoinGameResults();
		return thisResult;
	}
	
	public SaveGameResults save(SaveGameParams thisParams) {
		SaveGameResults thisResult = new SaveGameResults();
		return thisResult;
	}
	
	public LoadGameResults load(LoadGameParams thisParams) {
		LoadGameResults thisResult = new LoadGameResults();
		return thisResult;
	}
}
