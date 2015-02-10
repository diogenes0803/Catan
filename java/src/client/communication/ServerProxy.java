package client.communication;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLDecoder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
    private final int HTTP_OK = HttpURLConnection.HTTP_OK;
    
    public ServerProxy(String host, String port){   
        clientComm = new ClientCommunicator(host, port);
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
          HttpURLResponse response = clientComm.get("/user/login", params);

          result.setSuccess(response.getResponseCode() == HTTP_OK);
          result.setResponseBody(result.getResponseBody());
          if(result.isSuccess()){
              String new_cookie = response.getCookie("catan.user");
              createCookie("catan.user", new_cookie);
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
            HttpURLResponse response = clientComm.get("/user/register", params);
            result.setSuccess(response.getResponseCode() == HTTP_OK);
            result.setResponseBody(result.getResponseBody());
            
            if(result.isSuccess()){
                String new_cookie = response.getCookie("catan.user");
                createCookie("catan.user", new_cookie);
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
            HttpURLResponse response = clientComm.get("/games/list", "");
            
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
			results.setSuccess(clientComm.post("/user/create", params));
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
			results.setSuccess(clientComm.post("/user/join", params));
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
			results.setSuccess(clientComm.post("/user/save", params));
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
			results.setSuccess(clientComm.post("/user/load", params));
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
			HttpURLResponse response = clientComm.get("/games/model", null);
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
			if(clientComm.post("/games/reset", null)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	
        return results;
    }

    @Override
    public GetCommandsResults getCommands(GetCommandsParams params) {

    	GetCommandsResults results = new GetCommandsResults();
    	
    	try {
			results.setResponseBody(clientComm.get("/user/commands", null));
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
			if(clientComm.post("/games/commands", params)) {
				results = getModel();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
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
			//e.printStackTrace();
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
			//e.printStackTrace();
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
			//e.printStackTrace();
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
			//e.printStackTrace();
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
			//e.printStackTrace();
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
			//e.printStackTrace();
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
			//e.printStackTrace();
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
			//e.printStackTrace();
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
			//e.printStackTrace();
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
			//e.printStackTrace();
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
			//e.printStackTrace();
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
			//e.printStackTrace();
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
			//e.printStackTrace();
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
			//e.printStackTrace();
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
			//e.printStackTrace();
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
			//e.printStackTrace();
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
			//e.printStackTrace();
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
			//e.printStackTrace();
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

}
