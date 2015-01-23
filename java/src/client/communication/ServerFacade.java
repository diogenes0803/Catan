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
	 * @pre username is not null, password is not null
	 * 
	 * @post If the passed­in (username, password) pair is valid,
	 * 	1. The server returns an HTTP 200 success response with “Success” in the body.
	 * 	2. The HTTP response headers set the catan.user cookie to contain the identity of the
	 * 	logged­in player. The cookie uses ”Path=/”, and its value contains a url­encoded JSON object of
	 * 	the following form: { “name”: STRING, “password”: STRING, “playerID”: INTEGER }. For
	 * 	example, { “name”: “Rick”, “password”: “secret”, “playerID”: 14 }.
	 * 	If the passed­in (username, password) pair is not valid, or the operation fails for any other
	 * 	reason,
	 * 	1. The server returns an HTTP 400 error response, and the body contains an error
	 * 	message.
	 */
	public UserLoginResults userLogin(UserLoginParams params){
		return null;
	}
	
	/**
	 * @pre username is not null, password is not null, The specified username is not already in use.
	 * 
	 * @post If there is no existing user with the specified username,
	 * 1. A new user account has been created with the specified username and password.
	 * 2. The server returns an HTTP 200 success response with “Success” in the body.
	 * 3. The HTTP response headers set the catan.user cookie to contain the identity of the
	 * logged­in player. The cookie uses ”Path=/”, and its value contains a url­encoded JSON object of
	 * the following form: { “name”: STRING, “password”: STRING, “playerID”: INTEGER }. For
	 * example, { “name”: “Rick”, “password”: “secret”, “playerID”: 14 }.
	 * If there is already an existing user with the specified name, or the operation fails for any other
	 * reason,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public RegisterUserResults registerUser(RegisterUserParams params){
		return null;
	}
	
	/**
	 * @pre None.
	 * 
	 * @post If the operation succeeds,
	 * 1. The server returns an HTTP 200 success response.
	 * 2. The body contains a JSON array containing a list of objects that contain information
	 * about the server’s games
	 * If the operation fails,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public ListGamesResults listGames(ListGamesParams params){
		return null;
	}
	
	/**
	 * @pre name != null 
	 * randomTiles, randomNumbers, and randomPorts contain valid boolean values
	 * 
	 * @post If the operation succeeds,
	 * 1. A new game with the specified properties has been created
	 * 2. The server returns an HTTP 200 success response.
	 * 3. The body contains a JSON object describing the newly created game
	 * If the operation fails,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public CreatGameResults createGame(CreateGameParams params){
		return null;
	}
	
	/**
	 * @pre 1. The user has previously logged in to the server (i.e., they have a valid catan.user HTTP cookie).
	 * 2. The player may join the game because
	 * 2.a They are already in the game, OR
	 * 2.b There is space in the game to add a new player
	 * 3. The specified game ID is valid
	 * 4. The specified color is valid (red, green, blue, yellow, puce, brown, white, purple,
	 * orange)
	 * 
	 * @post If the operation succeeds,
	 * 1. The server returns an HTTP 200 success response with “Success” in the body.
	 * 2. The player is in the game with the specified color (i.e. calls to /games/list method will
	 * show the player in the game with the chosen color).
	 * 3. The server response includes the “Set­cookie” response header setting the catan.game
	 * HTTP cookie
	 * If the operation fails,1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public JoinGameResults joinGame(JoinGameParams params){
		return null;
	}
	
	/**
	 * @pre 1. The specified game ID is valid
	 * 2. The specified file name is valid (i.e., not null or empty)
	 * 
	 * @post If the operation succeeds,
	 * 1. The server returns an HTTP 200 success response with “Success” in the body.
	 * 2. The current state of the specified game (including its ID) has been saved to the
	 * specified file name in the server’s saves/ directory
	 * If the operation fails,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public SaveGameResults saveGame(SaveGameParams params){
		return null;
	}
	
	/**
	 * @pre 1. A previously saved game file with the specified name exists in the server’s saves/directory.
	 * 
	 * @post If the operation succeeds,1. The server returns an HTTP 200 success response with “Success” in the body.
	 * 2. The game in the specified file has been loaded into the server and its state restored
	 * (including its ID).
	 * If the operation fails,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error message.
	 */
	public LoadGameResults loadGame(LoadGameParams params){
		return null;
	}
	
	/**
	 * @pre 1. The caller has previously logged in to the server and joined a game (i.e., they have
	 * valid catan.user and catan.game HTTP cookies).
	 * 2. If specified, the version number is included as the “version” query parameter in the
	 * request URL, and its value is a valid integer.
	 * 
	 * @post If the operation succeeds,
	 * 1. The server returns an HTTP 200 success response.
	 * 2. The response body contains JSON data
	 * a. The full client model JSON is returned if the caller does not provide a version
	 * number, or the provide version number does not match the version on the server
	 * b. “true” (true in double quotes) is returned if the caller provided a version number,
	 * and the version number matched the version number on the server
	 * If the operation fails,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public GetModelResults getModel(GetModelParams params){
		return null;
	}
	
	/**
	 * @pre 1. The caller has previously logged in to the server and joined a game (i.e., they have
	 * valid catan.user and catan.game HTTP cookies).
	 * 
	 * @post If the operation succeeds,
	 * 1. The game’s command history has been cleared out
	 * 2. The game’s players have NOT been cleared out
	 * 3. The server returns an HTTP 200 success response.
	 * 4. The body contains the game’s updated client model JSON
	 * If the operation fails,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public ResetGameResults resetGame(ResetGameParams params){
		return null;
	}
	
	/**
	 * @pre 1. The caller has previously logged in to the server and joined a game (i.e., they have
	 * valid catan.user and catan.game HTTP cookies).
	 * 
	 * @post If the operation succeeds,
	 * 1. The server returns an HTTP 200 success response.
	 * 2. The body contains a JSON array of commands that have been executed in the game.
	 * This command array is suitable for passing back to the /game/command [POST] method to
	 * restore the state of the game later (after calling /game/reset to revert the game to its initial state).
	 * If the operation fails,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public GetCommandsResults getCommands(GetCommandsParams params){
		return null;
	}
	
	/**
	 * @pre 1. The caller has previously logged in to the server and joined a game (i.e., they have
	 * valid catan.user and catan.game HTTP cookies).
	 * 
	 * @post If the operation succeeds,
	 * 1. The passed­in command list has been applied to the game.
	 * 2. The server returns an HTTP 200 success response.
	 * 3. The body contains the game’s updated client model JSON
	 * If the operation fails,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public ExecuteCommandsResults executeCommands(ExecuteCommandsParams params){
		return null;
	}
	
	/**
	 * @pre None.
	 * 
	 * @post If the operation succeeds,
	 * 1. The server returns an HTTP 200 success response.
	 * 2. The body contains a JSON string array enumerating the different types of AI players.
	 * These are the values that may be passed to the /game/addAI method.
	 */
	public ListAIResults listAI(ListAIParams params){
		return null;
	}
	
	/**
	 * @pre 1.The caller specifies a valid logging level. Valid values include: SEVERE, WARNING,
	 * INFO, CONFIG, FINE, FINER, FINEST
	 * @post If the operation succeeds,
	 * 1. The server returns an HTTP 200 success response with “Success” in the body.
	 * 2. The Server is using the specified logging level
	 * If the operation fails,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
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
