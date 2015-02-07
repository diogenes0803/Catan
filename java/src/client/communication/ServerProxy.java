package client.communication;

import shared.communicator.*;
import shared.models.CatanModel;

/**
 * Description: Holds the Client Communicator and the Server Facade
 * @author oxbor, campbeln, dbilleter
 *
 */
@SuppressWarnings("unused")
public class ServerProxy implements ServerStandinInterface, ServerInterface{
	private ClientCommunicator clientComm;
	
	public ServerProxy(){	
		clientComm = new ClientCommunicator("localhost", 8081);
	}//end constructor

    @Override
    public UserLoginResults userLogin(UserLoginParams params) {
        
    	UserLoginResults results = new UserLoginResults();
    	
    	try {
			results.setSuccess(clientComm.post("/user/login", params));
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			results.setSuccess(false);
		}
    	
        return results;
    }

    @Override
    public RegisterUserResults registerUser(RegisterUserParams params) {

    	RegisterUserResults results = new RegisterUserResults();
    	
    	try {
			results.setSuccess(clientComm.post("/user/register", params));
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			results.setSuccess(false);
		}
    	
        return results;
    }

    @Override
    public ListGamesResults listGames() {

    	ListGamesResults results = new ListGamesResults();
    	
    	try {
			results.setResponse(clientComm.get("/games/list", null));
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CreateGameResults createGame(CreateGameParams params) {

    	CreateGameResults results = new CreateGameResults();
    	
    	try {
			results.setSuccess(clientComm.post("/user/create", params));
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			results.setSuccess(false);
		}

    	
        return results;
    }

    @Override
    public JoinGameResults joinGame(JoinGameParams params) {

    	JoinGameResults results = new JoinGameResults();
    	
    	try {
			results.setSuccess(clientComm.post("/user/join", params));
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			results.setSuccess(false);
		}
    	
        return results;
    }

    @Override
    public SaveGameResults saveGame(SaveGameParams params) {

    	SaveGameResults results = new SaveGameResults();
    	
    	try {
			results.setSuccess(clientComm.post("/user/save", params));
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			results.setSuccess(false);
		}
    	
        return results;
    }

    @Override
    public LoadGameResults loadGame(LoadGameParams params) {

    	LoadGameResults results = new LoadGameResults();
    	
    	try {
			results.setSuccess(clientComm.post("/user/load", params));
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			results.setSuccess(false);
		}
    	
        return results;
    }

    @Override
    public CatanModel getModel() {
    	CatanModel results = new CatanModel();

    	try {
			String response = clientComm.get("/games/model", null);
			// TODO turn response into a CatanModel
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel resetGame() {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/reset", null)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public GetCommandsResults getCommands(GetCommandsParams params) {

    	GetCommandsResults results = new GetCommandsResults();
    	
    	try {
			results.setResponse(clientComm.get("/user/commands", null));
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			results.setSuccess(false);
		}
    	
        return results;
    }

    @Override
    public CatanModel executeCommands(ExecuteCommandsParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/commands", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public ListAIResults listAI(ListAIParams params) {

    	ListAIResults results = new ListAIResults();
    	
    	try {
			results.setSuccess(clientComm.post("/user/listAI", params));
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			results.setSuccess(false);
		}
    	
        return results;
    }

    @Override
    public ChangeLogLevelResults changeLogLevel(ChangeLogLevelParams params) {

    	ChangeLogLevelResults results = new ChangeLogLevelResults();
    	
    	try {
			results.setSuccess(clientComm.post("/user/changeloglevel", params));
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			results.setSuccess(false);
		}
		    	
        return results;
    }

    @Override
    public CatanModel sendChat(SendChatParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/sendChat", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel acceptTrade(AcceptTradeParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/acceptTrade", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel discardCards(DiscardCardsParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/discardCards", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel rollNumber(RollNumberParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/rollNumber", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel buildRoad(BuildRoadParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/buildRoad", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel buildSettlement(BuildSettlementParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/buildSettlement", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel buildCity(BuildCityParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/buildCity", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel offerTrade(OfferTradeParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/offerTrade", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel maritimeTrade(MaritimeTradeParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/maritimeTrade", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel robPlayer(RobPlayerParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/robPlayer", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel finishTurn(FinishTurnParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/finishTurn", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel buyDevCard(BuyDevCardParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/buyDevCard", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel playSoldier(PlaySoldierParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/Soldier", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel yearOfPlenty(YearOfPlentyParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/Year_of_Plenty", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel roadBuilding(RoadBuildingParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/Road_Building", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel monopoly(MonopolyParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/Monopoly", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel monument(MonumentParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/Monument", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel rollDice(MoveParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/rollNumber", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public void distributeCards(int diceSum) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void startGame() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void leaveGame() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateModel() {
        // TODO Auto-generated method stub
        
    }

}
