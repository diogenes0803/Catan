package client.communication;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLDecoder;

import shared.communicator.AcceptTradeParams;
import shared.communicator.BuildCityParams;
import shared.communicator.BuildRoadParams;
import shared.communicator.BuildSettlementParams;
import shared.communicator.BuyDevCardParams;
import shared.communicator.ChangeLogLevelParams;
import shared.communicator.ChangeLogLevelResults;
import shared.communicator.CreateGameParams;
import shared.communicator.CreateGameResults;
import shared.communicator.DiscardCardsParams;
import shared.communicator.ExecuteCommandsParams;
import shared.communicator.FinishTurnParams;
import shared.communicator.GetCommandsResults;
import shared.communicator.JoinGameParams;
import shared.communicator.JoinGameResults;
import shared.communicator.ListAIParams;
import shared.communicator.ListAIResults;
import shared.communicator.ListGamesResults;
import shared.communicator.LoadGameParams;
import shared.communicator.LoadGameResults;
import shared.communicator.MaritimeTradeParams;
import shared.communicator.MonopolyParams;
import shared.communicator.MonumentParams;
import shared.communicator.OfferTradeParams;
import shared.communicator.PlaySoldierParams;
import shared.communicator.RegisterUserParams;
import shared.communicator.RegisterUserResults;
import shared.communicator.RoadBuildingParams;
import shared.communicator.RobPlayerParams;
import shared.communicator.RollNumberParams;
import shared.communicator.SaveGameParams;
import shared.communicator.SaveGameResults;
import shared.communicator.SendChatParams;
import shared.communicator.UserLoginParams;
import shared.communicator.UserLoginResults;
import shared.communicator.YearOfPlentyParams;
import shared.models.CatanModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Description: Holds the Client Communicator and the Server Facade
 * @author oxbor, campbeln, dbilleter
 *
 */
@SuppressWarnings("unused")
public class ServerProxy implements ServerStandinInterface, ServerInterface{
    private ClientCommunicator clientComm;
    private final int HTTP_OK = HttpURLConnection.HTTP_OK;
    private String playerCookie;
    private String gameCookie;
    
    public ServerProxy(String host, String port){   
        clientComm = new ClientCommunicator(host, port);
        playerCookie = null;
        gameCookie = null;
    }//end constructor
    
    
    private void createCookie(String cookie_name, String content) {
        // TODO Auto-generated method stub
        
    }
    
    private String getCookie(String cookie_name){
        return null;
    }
    
    
    //if successful, set cookie and return
    @Override
    public UserLoginResults userLogin(UserLoginParams params) {
        assert(params != null);
        
        UserLoginResults result = new UserLoginResults();

        try{
          HttpURLResponse response = clientComm.get("/user/login", params, playerCookie);

          result.setSuccess(response.getResponseCode() == HTTP_OK);
          result.setResponseBody(result.getResponseBody());
          if(result.isSuccess()){
              String new_cookie = response.getCookie("catan.user");
              playerCookie = new_cookie;
              try {
                String json_cookie = URLDecoder.decode(new_cookie, "UTF-8");
               
                JsonObject je = (JsonObject)new JsonParser().parse(json_cookie);
                result.setName(je.get("name").getAsString());
                result.setPassword(je.get("password").getAsString());
                result.setPlayerId(je.get("playerID").getAsInt());
                
            } catch (UnsupportedEncodingException e) {
                //should never happen as long as UTF-8 is a valid encoding.
                e.printStackTrace();
            }
          }
        
        }catch(ClientException e){
            result.setResponseBody(e.toString());
            result.setSuccess(false);
            System.out.println(e.toString());

        }
        return result;
    }

    


    //will register user and log them in at the same time.
    @Override
    public RegisterUserResults registerUser(RegisterUserParams params) {

        RegisterUserResults result = new RegisterUserResults();
        try{
            HttpURLResponse response = clientComm.get("/user/register", params, playerCookie+";PATH=/");
            result.setSuccess(response.getResponseCode() == HTTP_OK);
            result.setResponseBody(result.getResponseBody());
            
            if(result.isSuccess()){
                String new_cookie = response.getCookie("catan.user");
                playerCookie = "catan.user="+new_cookie;
                try {
                  String json_cookie = URLDecoder.decode(new_cookie, "UTF-8");
              
                  JsonObject je = (JsonObject)new JsonParser().parse(json_cookie);
                  result.setName(je.get("name").getAsString());
                  result.setPassword(je.get("password").getAsString());
                  result.setPlayerId(je.get("playerID").getAsInt());
                  
              } catch (UnsupportedEncodingException e) {
                  //should never happen as long as UTF-8 is a valid encoding.
                  e.printStackTrace();
              }
            }
            
        }catch(ClientException e){
            result.setResponseBody(e.toString());
            result.setSuccess(false);
            System.out.println(e.toString());

        }
    return result;
}

    //get a list of game_info objects
    @Override
    public ListGamesResults listGames() {

        ListGamesResults result = new ListGamesResults();
        try{
            HttpURLResponse response = clientComm.get("/games/list", "", playerCookie+"; "+gameCookie);
            
            result.setSuccess(response.getResponseCode() == HTTP_OK);
            result.setResponseBody(result.getResponseBody());
            
            if(result.isSuccess()){
                JsonArray ja = (JsonArray)new JsonParser().parse(result.getResponseBody()); //array of games
                for(int i=0; i< ja.size(); i++){
                    
                    JsonObject game = (JsonObject)ja.get(i);
                    String game_name = game.get("name").getAsString();
                    int game_id = game.get("id").getAsInt();
                    
                    JsonArray playerArray = (JsonArray) game.get("players");
                }
            }
            
            
        }catch(ClientException e){
            result = new ListGamesResults();
            result.setSuccess(false);
            System.out.println(e.toString());

        }
        return result;
    }

    @Override
    public CreateGameResults createGame(CreateGameParams params) {

    	CreateGameResults results = new CreateGameResults();
    	
    	try {
			results.setSuccess(clientComm.post("/user/create", params, playerCookie+"; "+gameCookie));
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			results.setSuccess(false);
		}

    	
        return results;
    }

    @Override
    public JoinGameResults joinGame(JoinGameParams params) {

    	JoinGameResults results = new JoinGameResults();
    	
    	try {
			results.setSuccess(clientComm.post("/user/join", params, playerCookie+"; "+gameCookie));     
               
                 //playerCookie = json_cookie;
                 //JsonObject je = (JsonObject)new JsonParser().parse(json_cookie);
                 if(results.isSuccess())
                     gameCookie = "catan.game="+params.getId();

                 
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			results.setSuccess(false);
		}
    	
        return results;
    }

    @Override
    public SaveGameResults saveGame(SaveGameParams params) {

    	SaveGameResults results = new SaveGameResults();
    	
    	try {
			results.setSuccess(clientComm.post("/user/save", params, playerCookie+"; "+gameCookie));
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			results.setSuccess(false);
		}
    	
        return results;
    }

    @Override
    public LoadGameResults loadGame(LoadGameParams params) {

    	LoadGameResults results = new LoadGameResults();
    	
    	try {
			results.setSuccess(clientComm.post("/user/load", params, playerCookie+"; "+gameCookie));
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			results.setSuccess(false);
		}
    	
        return results;
    }

    @Override
    public CatanModel getModel() {
    	CatanModel results = new CatanModel();

    	try {
			HttpURLResponse response = clientComm.get("/games/model", null, playerCookie+"; "+gameCookie);
			// TODO turn response into a CatanModel
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel resetGame() {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/reset", null, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public GetCommandsResults getCommands() {

    	GetCommandsResults results = new GetCommandsResults();
    	
    	try {
    		HttpURLResponse response = clientComm.get("/user/commands", null, playerCookie+"; "+gameCookie);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			results.setSuccess(false);
		}
    	
        return results;
    }

    @Override
    public CatanModel executeCommands(ExecuteCommandsParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/commands", params, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public ListAIResults listAI() {

    	ListAIResults results = new ListAIResults();
    	
    	try {
			results.setSuccess(clientComm.post("/user/listAI", null, playerCookie+"; "+gameCookie));
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			results.setSuccess(false);
		}
    	
        return results;
    }

    @Override
    public ChangeLogLevelResults changeLogLevel(ChangeLogLevelParams params) {

    	ChangeLogLevelResults results = new ChangeLogLevelResults();
    	
    	try {
			results.setSuccess(clientComm.post("/user/changeloglevel", params, playerCookie+"; "+gameCookie));
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			results.setSuccess(false);
		}
		    	
        return results;
    }

    @Override
    public CatanModel sendChat(SendChatParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/sendChat", params, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel acceptTrade(AcceptTradeParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/acceptTrade", params, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel discardCards(DiscardCardsParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/discardCards", params, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel rollNumber(RollNumberParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/rollNumber", params, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel buildRoad(BuildRoadParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/buildRoad", params, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel buildSettlement(BuildSettlementParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/buildSettlement", params, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel buildCity(BuildCityParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/buildCity", params, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel offerTrade(OfferTradeParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/offerTrade", params, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel maritimeTrade(MaritimeTradeParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/maritimeTrade", params, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel robPlayer(RobPlayerParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/robPlayer", params, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel finishTurn(FinishTurnParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/finishTurn", params, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel buyDevCard(BuyDevCardParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/buyDevCard", params, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel playSoldier(PlaySoldierParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/Soldier", params, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel yearOfPlenty(YearOfPlentyParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/Year_of_Plenty", params, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel roadBuilding(RoadBuildingParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/Road_Building", params, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel monopoly(MonopolyParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/Monopoly", params, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public CatanModel monument(MonumentParams params) {
    	CatanModel results = new CatanModel();

    	try {
			if(clientComm.post("/games/Monument", params, playerCookie+"; "+gameCookie)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
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
    public boolean updateModel() {
    	HttpURLResponse response = new HttpURLResponse();
    	try {
			response = clientComm.get("/games/model", null, playerCookie+"; "+gameCookie);
			
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			response.setResponseCode(400);
			
		}
    	
        return response.getResponseCode() == HTTP_OK;
        
    }

	@Override
	public AddAIResults AddAI(String params) {
		// TODO Auto-generated method stub
		return null;
	}

}
