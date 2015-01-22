package client.communication;

import shared.communicator.ChangeLogLevelParams;
import shared.communicator.ChangeLogLevelResults;
import shared.communicator.CreatGameResults;
import shared.communicator.CreateGameParams;
import shared.communicator.ExecuteCommandsParams;
import shared.communicator.ExecuteCommandsResults;
import shared.communicator.GetCommandsParams;
import shared.communicator.GetCommandsResults;
import shared.communicator.GetModelParams;
import shared.communicator.GetModelResults;
import shared.communicator.JoinGameParams;
import shared.communicator.JoinGameResults;
import shared.communicator.ListAIParams;
import shared.communicator.ListAIResults;
import shared.communicator.ListGamesParams;
import shared.communicator.ListGamesResults;
import shared.communicator.LoadGameParams;
import shared.communicator.LoadGameResults;
import shared.communicator.MoveResults;
import shared.communicator.RegisterUserParams;
import shared.communicator.RegisterUserResults;
import shared.communicator.ResetGameParams;
import shared.communicator.ResetGameResults;
import shared.communicator.SaveGameParams;
import shared.communicator.SaveGameResults;
import shared.communicator.UserLoginParams;
import shared.communicator.UserLoginResults;

/**
 * Description: The Server Facade takes in parameter objects for the commands that the server is able to perform,
 * passes them to the client communicator and then receives a results objects back containing the server's response 
 * @author oxbor
 *
 */
public class ServerFacade {
	
	/**
	 * @postIf the passed­in (username, password) pair is valid,
	*1. The server returns an HTTP 200 success response with “Success” in the body.
	*2. The HTTP response headers set the catan.user cookie to contain the identity of the
	*logged­in player. The cookie uses ”Path=/”, and its value contains a url­encoded JSON object of
	*the following form: { “name”: STRING, “password”: STRING, “playerID”: INTEGER }. For
example, { “name”: “Rick”, “password”: “secret”, “playerID”: 14 }.
If the passed­in (username, password) pair is not valid, or the operation fails for any other
reason,
1. The server returns an HTTP 400 error response, and the body contains an error
message.
	 */
	public UserLoginResults userLogin(UserLoginParams params){
		return null;
	}
	/**
	 * 
	 * @post
	 */
	public RegisterUserResults registerUser(RegisterUserParams params){
		return null;
	}
	/**
	 * 
	 * @post
	 */
	public ListGamesResults listGames(ListGamesParams params){
		return null;
	}
	/**
	 * 
	 * @post
	 */
	public CreatGameResults createGame(CreateGameParams params){
		return null;
	}
	/**
	 * 
	 * @post
	 */
	public JoinGameResults joinGame(JoinGameParams params){
		return null;
	}
	/**
	 * 
	 * @post
	 */
	public SaveGameResults saveGame(SaveGameParams params){
		return null;
	}
	/**
	 * 
	 * @post
	 */
	public LoadGameResults loadGame(LoadGameParams params){
		return null;
	}
	/**
	 * 
	 * @post
	 */
	public GetModelResults getModel(GetModelParams params){
		return null;
	}
	/**
	 * 
	 * @post
	 */
	public ResetGameResults resetGame(ResetGameParams params){
		return null;
	}
	/**
	 * 
	 * @post
	 */
	public GetCommandsResults getCommands(GetCommandsParams params){
		return null;
	}
	/**
	 * 
	 * @post
	 */
	public ExecuteCommandsResults executeCommands(ExecuteCommandsParams params){
		return null;
	}
	/**
	 * 
	 * @post
	 */
	public ListAIResults listAI(ListAIParams params){
		return null;
	}
	/**
	 * 
	 * @post
	 */
	public ChangeLogLevelResults changeLogLevel(ChangeLogLevelParams params){
		return null;
	}
	/**
	 * 
	 * @post
	 */
	public MoveResults move(Object o){
		return null;
	}

}
