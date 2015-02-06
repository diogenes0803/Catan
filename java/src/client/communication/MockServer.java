/**
 * 
 */
package client.communication;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import shared.communicator.*;
import shared.models.CatanModel;
import shared.models.Player;

/**
 * @author campbeln
 *
 */
public class MockServer implements ServerStandinInterface, ServerInterface {
    
	
    private CatanModel model_ptr;
    
    //In case we create a more robust mock server
    private HashMap<String, String> users;

    public MockServer(CatanModel model){
        model_ptr = model;
        users = new HashMap<String, String>();
    }
    

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#userLogin(shared.communicator.UserLoginParams)
	 */
	@Override
	public UserLoginResults userLogin(UserLoginParams params) {
		
		UserLoginResults results = new UserLoginResults();
		
		if (!params.getUserName().equals("John"))
		{
			results.setResponseBody("Failed to login - bad username or password.");
			return results;
		}
		else if (!params.getPassword().equals("Doe"))
		{
			results.setResponseBody("Failed to login - bad username or password.");
			return results;
		}
	
		results.setResponseBody("Success");
		return results;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#registerUser(shared.communicator.RegisterUserParams)
	 */
	@Override
	public RegisterUserResults registerUser(RegisterUserParams params) {
		
		RegisterUserResults results = new RegisterUserResults();
		
		if (params.getUserName().equals("John"))
		{
			results.setResponseBody("Failed to register - someone already has that username.");
			return results;
		}

		results.setResponseBody("Success");
		return results;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#listGames(shared.communicator.ListGamesParams)
	 */
	@Override
	public ListGamesResults listGames() {
		
		ListGamesResults results = new ListGamesResults();
		
		Player player = new Player();
		
		/*
		player.setColor();
		
		
		Player[] players = new Player[4];
		Game game = new game("Default Game", 0, )
		
	    "players": [
	      {
	        "color": "orange",
	        "name": "Sam",
	        "id": 0
	      },
	      {
	        "color": "blue",
	        "name": "Brooke",
	        "id": 1
	      },
	      {
	        "color": "red",
	        "name": "Pete",
	        "id": 10
	      },
	      {
	        "color": "green",
	        "name": "Mark",
	        "id": 11
	      }
	    ]
	  },
	  {
	    "title": "AI Game",
	    "id": 1,
	    "players": [
	      {
	        "color": "orange",
	        "name": "Pete",
	        "id": 10
	      },
	      {
	        "color": "red",
	        "name": "Quinn",
	        "id": -2
	      },
	      {
	        "color": "blue",
	        "name": "Steve",
	        "id": -3
	      },
	      {
	        "color": "green",
	        "name": "Ken",
	        "id": -4
	      }
	    ]
	  },
	  {
	    "title": "Empty Game",
	    "id": 2,
	    "players": [
	      {
	        "color": "orange",
	        "name": "Sam",
	        "id": 0
	      },
	      {
	        "color": "blue",
	        "name": "Brooke",
	        "id": 1
	      },
	      {
	        "color": "red",
	        "name": "Pete",
	        "id": 10
	      },
	      {
	        "color": "green",
	        "name": "Mark",
	        "id": 11
	      }
	    ]
	    */
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#createGame(shared.communicator.CreateGameParams)
	 */
	@Override
	public CreateGameResults createGame(CreateGameParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#joinGame(shared.communicator.JoinGameParams)
	 */
	@Override
	public JoinGameResults joinGame(JoinGameParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#saveGame(shared.communicator.SaveGameParams)
	 */
	@Override
	public SaveGameResults saveGame(SaveGameParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#loadGame(shared.communicator.LoadGameParams)
	 */
	@Override
	public LoadGameResults loadGame(LoadGameParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#getModel(shared.communicator.GetModelParams)
	 */
	@Override
	public GetModelResults getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#resetGame(shared.communicator.ResetGameParams)
	 */
	@Override
	public ResetGameResults resetGame() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#getCommands(shared.communicator.GetCommandsParams)
	 */
	@Override
	public GetCommandsResults getCommands(GetCommandsParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#executeCommands(shared.communicator.ExecuteCommandsParams)
	 */
	@Override
	public ExecuteCommandsResults executeCommands(ExecuteCommandsParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#listAI(shared.communicator.ListAIParams)
	 */
	@Override
	public ListAIResults listAI(ListAIParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#changeLogLevel(shared.communicator.ChangeLogLevelParams)
	 */
	@Override
	public ChangeLogLevelResults changeLogLevel(ChangeLogLevelParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#sendChat(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel sendChat(SendChatParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#acceptTrade(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel acceptTrade(AcceptTradeParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#discardCards(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel discardCards(DiscardCardsParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#rollNumber(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel rollNumber(RollNumberParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#buildRoad(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel buildRoad(BuildRoadParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#buildSettlement(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel buildSettlement(BuildSettlementParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#buildCity(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel buildCity(BuildCityParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#offerTrade(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel offerTrade(OfferTradeParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#maritimeTrade(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel maritimeTrade(MaritimeTradeParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#robPlayer(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel robPlayer(RobPlayerParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#finishTurn(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel finishTurn(FinishTurnParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#buyDevCard(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel buyDevCard(BuyDevCardParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#playSoldier(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel playSoldier(PlaySoldierParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#yearOfPlenty(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel yearOfPlenty(YearOfPlentyParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#roadBuilding(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel roadBuilding(RoadBuildingParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#monopoly(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel monopoly(MonopolyParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#monument(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel monument(MonumentParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#rollDice(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel rollDice(MoveParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#distributeCards(int)
	 */
	@Override
	public void distributeCards(int diceSum) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#startGame()
	 */
	@Override
	public void startGame() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#leaveGame()
	 */
	@Override
	public void leaveGame() {
		// TODO Auto-generated method stub

	}

    @Override
    public void updateModel() {
        // TODO Auto-generated method stub
        
    }
    
    
    //The following two methods are only to be used if we create a more robust mock server
    private void addUser(String userName, String password)
    {
    	users.put(userName, password);
    }
    
    private boolean checkForUser(String userName, String password)
    {
    	String value = users.get(userName);
    	if (value != null)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

}
