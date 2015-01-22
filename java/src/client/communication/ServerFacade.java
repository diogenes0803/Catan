package client.communication;

/**
 * 
 * @author oxbor
 *
 */
public class ServerFacade {
	
	public results userLogin(UserLoginInputParams params){}
	public results registerUser(RegisterUserInputParams params){}
	public results listGames(ListGamesInputParams params){}
	public results createGame(Input_params params){}
	public results joinGame(Input_params params){}
	public results saveGame(Input_params params){}
	public results loadGame(Input_params params){}
	public results getModel(Input_params params){}
	public results resetGame(Input_params params){}
	public results getCommands(Input_params params){}
	public results executeCommands(Input_params params){}
	public results listAI(Input_params params){}
	public results changeLogLevel(Input_params){}
	public results move(Object o){}

}
