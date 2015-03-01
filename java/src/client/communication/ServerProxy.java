package client.communication;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLDecoder;

import shared.communicator.AcceptTradeParams;
import shared.communicator.AddAIResults;
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
import shared.models.Game;
import shared.models.GameManager;
import shared.models.jsonholder.JsonModelHolder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Description: Holds the Client Communicator and the Server Facade
 * @author oxbor, campbeln, dbilleter
 *
 */
@SuppressWarnings("unused")
public class ServerProxy implements ServerStandinInterface, ServerInterface{
    public static final int UNINITIALIZED_MODEL = -1;//-1 mean model not yet initialized
    public static final String PATH = "; Path=/";
    private final int HTTP_OK = HttpURLConnection.HTTP_OK;
    
    
    private ClientCommunicator clientComm;  
    
    //Two Cookies Needed
    private String playerCookie;
    private String gameCookie;
    private int version;
    private int currentGameId;
    private int myPlayerId;
    

    public String getPlayerCookie() {
		return playerCookie;
	}
	public void setPlayerCookie(String playerCookie) {
		this.playerCookie = playerCookie;
	}
    public String getGameCookie() {
		return gameCookie;
	}
	public void setGameCookie(String gameCookie) {
		this.gameCookie = gameCookie;
	}
	public int getCurrentGameId() {
		return currentGameId;
	}
	public int getMyPlayerId() {
		return myPlayerId;
	}


	private static ServerProxy instance = new ServerProxy();
	private ServerProxy() {
        playerCookie = "";
        gameCookie = "";
        version = UNINITIALIZED_MODEL;
	}
	
	public static ServerProxy getInstance() {
		return instance;
	}
	
	//MUST be called before ServerProxy can be used
	public void initClientComm (String host, String port) {
		instance.clientComm = new ClientCommunicator(host, port);
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
                
                playerCookie = "catan.user="+new_cookie;
                
                //System.out.println("playerCookie:\n\t"+playerCookie);
                try {
                    String json_cookie = URLDecoder.decode(new_cookie, "UTF-8");
                    //System.out.println("json_cookie:\n\t'"+json_cookie+"'");
                    
                    JsonObject je = (JsonObject)new JsonParser().parse(json_cookie);
                    result.setName(je.get("name").getAsString());
                    result.setPassword(je.get("password").getAsString());
                    result.setPlayerId(je.get("playerID").getAsInt());
                    instance.myPlayerId = result.getPlayerId();

                } catch (UnsupportedEncodingException e) {
                    //should never happen as long as UTF-8 is a valid encoding.
                    e.printStackTrace();
                }
            }
        }catch(ClientException e){
            result.setResponseBody(e.toString());
            result.setSuccess(false);
            System.out.println(e.toString());

        }catch(Exception e1){
            result.setResponseBody(e1.toString());
            result.setSuccess(false);
            System.out.println(e1.toString());
        }
        return result;
    }




    //will register user and log them in at the same time.
    @Override
    public RegisterUserResults registerUser(RegisterUserParams params) {

        RegisterUserResults result = new RegisterUserResults();
        try{
            HttpURLResponse response = clientComm.get("/user/register", params, playerCookie);
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
                    instance.myPlayerId = result.getPlayerId();
                    

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
 //===================================================================================
    //get a list of game_info objects
    @Override
    public ListGamesResults listGames() {

    	
        ListGamesResults result = new ListGamesResults();
       
        try{
        	
            HttpURLResponse response = clientComm.get("/games/list", null, playerCookie);

          
            result.setSuccess(response.getResponseCode() == HTTP_OK);
            
            result.setResponseBody(result.getResponseBody());
            
           /* Can implement later
            if(result.isSuccess()){
                JsonArray ja = (JsonArray)new JsonParser().parse(result.getResponseBody()); //array of games
                for(int i=0; i< ja.size(); i++){

                    JsonObject game = (JsonObject)ja.get(i);
                    String game_name = game.get("name").getAsString();
                    int game_id = game.get("id").getAsInt();
                    
                    JsonArray playerArray = (JsonArray) game.get("players");
                    
                    Game catanGame = new Game();
                    catanGame.setGameId(game_id);
                    catanGame.setGameTitle(game_name);
                    
                }
            }
*/
           
        }catch(ClientException e){
            result = new ListGamesResults();
            result.setSuccess(false);
            System.out.println(e.toString());

        }
        return result;
    }
    
    //===================================================================================
    
    
    @Override
    public JoinGameResults joinGame(JoinGameParams params) {

        JoinGameResults results = new JoinGameResults();
      
        try {//only uses player cookie
            results.setSuccess(clientComm.post("/games/join", params, playerCookie));     

            //playerCookie = json_cookie;
            //JsonObject je = (JsonObject)new JsonParser().parse(json_cookie);
            if(results.isSuccess()) {
                gameCookie = "catan.game="+params.getId();
                instance.currentGameId = params.getId();
            }


        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.out.println(e.toString());
            results.setSuccess(false);
        }

        return results;
    }

    //===================================================================================
    @Override
    public CreateGameResults createGame(CreateGameParams params) {
        CreateGameResults results = new CreateGameResults();

        try {
            results.setSuccess(clientComm.post("/games/create", params, playerCookie+"; "+gameCookie));
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            results.setSuccess(false);
        }


        return results;
    }

    //===================================================================================




	@Override
    public SaveGameResults saveGame(SaveGameParams params) {

        SaveGameResults results = new SaveGameResults();

        try {
            results.setSuccess(clientComm.post("/games/save", params, playerCookie+"; "+gameCookie));
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            results.setSuccess(false);
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public LoadGameResults loadGame(LoadGameParams params) {

        LoadGameResults results = new LoadGameResults();

        try {
            results.setSuccess(clientComm.post("/games/load", params, playerCookie+"; "+gameCookie));
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            results.setSuccess(false);
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public CatanModel getModel() {
    	/*
    	 * TODO Get version number from model, and store it in server proxy
    	 */
        CatanModel result_model = new CatanModel();
        String strVersion = "";
        if(version != UNINITIALIZED_MODEL)
            strVersion = "?version="+version;
        try {
            HttpURLResponse response = clientComm.get("/game/model"+strVersion, null, playerCookie+"; "+gameCookie);
            Gson gson = new Gson();
    		JsonModelHolder modelHolder = gson.fromJson(response.getResponseBody().toString(), JsonModelHolder.class);
    		Game thisGame = modelHolder.buildCatanGame();
    		GameManager manager = new GameManager();
    		result_model.setGameManager(manager);
    		result_model.getGameManager().setGame(thisGame);
            // TODO turn response into a CatanModel
        } catch (ClientException e) {
                      
            System.out.println(e);
            
        }

        return result_model;
    }
    //===================================================================================
    
    @Override
    public CatanModel resetGame() {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/game/reset", null, playerCookie+"; "+gameCookie)) {
                results = this.getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public GetCommandsResults getCommands() {

        GetCommandsResults results = new GetCommandsResults();

        try {
            HttpURLResponse response = clientComm.get("/game/commands", null, playerCookie+"; "+gameCookie);
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            results.setSuccess(false);
        }

        return results;
    }
    //===================================================================================
    //command post version
    @Override
    public CatanModel executeCommands(ExecuteCommandsParams params) {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/game/commands", params, playerCookie+"; "+gameCookie)) {
                results = getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public ListAIResults listAI() {

        ListAIResults results = new ListAIResults();

        try {
            results.setSuccess(clientComm.post("/game/listAI", null, playerCookie+"; "+gameCookie));
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            results.setSuccess(false);
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public ChangeLogLevelResults changeLogLevel(ChangeLogLevelParams params) {

        ChangeLogLevelResults results = new ChangeLogLevelResults();

        try {
            results.setSuccess(clientComm.post("/util/changeLogLevel", params, playerCookie+"; "+gameCookie));
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            results.setSuccess(false);
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public CatanModel sendChat(SendChatParams params) {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/moves/sendChat", params, playerCookie+"; "+gameCookie)) {
                results = this.getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public CatanModel acceptTrade(AcceptTradeParams params) {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/moves/acceptTrade", params, playerCookie+"; "+gameCookie)) {
                results = getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public CatanModel discardCards(DiscardCardsParams params) {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/moves/discardCards", params, playerCookie+"; "+gameCookie)) {
                results = getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public CatanModel rollNumber(RollNumberParams params) {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/moves/rollNumber", params, playerCookie+"; "+gameCookie)) {
                results = this.getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public CatanModel buildRoad(BuildRoadParams params) {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/moves/buildRoad", params, playerCookie+"; "+gameCookie)) {
                results = this.getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public CatanModel buildSettlement(BuildSettlementParams params) {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/moves/buildSettlement", params, playerCookie+"; "+gameCookie)) {
                results = this.getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public CatanModel buildCity(BuildCityParams params) {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/moves/buildCity", params, playerCookie+"; "+gameCookie)) {
                results = getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public CatanModel offerTrade(OfferTradeParams params) {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/moves/offerTrade", params, playerCookie+"; "+gameCookie)) {
                results = getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public CatanModel maritimeTrade(MaritimeTradeParams params) {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/moves/maritimeTrade", params, playerCookie+"; "+gameCookie)) {
                results = getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public CatanModel robPlayer(RobPlayerParams params) {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/moves/robPlayer", params, playerCookie+"; "+gameCookie)) {
                results = getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public CatanModel finishTurn(FinishTurnParams params) {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/moves/finishTurn", params, playerCookie+"; "+gameCookie)) {
                results = getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public CatanModel buyDevCard(BuyDevCardParams params) {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/moves/buyDevCard", params, playerCookie+"; "+gameCookie)) {
                results = getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public CatanModel playSoldier(PlaySoldierParams params) {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/moves/Soldier", params, playerCookie+"; "+gameCookie)) {
                results = this.getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public CatanModel yearOfPlenty(YearOfPlentyParams params) {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/moves/Year_of_Plenty", params, playerCookie+"; "+gameCookie)) {
                results = this.getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public CatanModel roadBuilding(RoadBuildingParams params) {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/moves/Road_Building", params, playerCookie+"; "+gameCookie)) {
                results = getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public CatanModel monopoly(MonopolyParams params) {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/moves/Monopoly", params, playerCookie+"; "+gameCookie)) {
                results = getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    
    @Override
    public CatanModel monument(MonumentParams params) {
        CatanModel results = new CatanModel();

        try {
            if(clientComm.post("/moves/Monument", params, playerCookie+"; "+gameCookie)) {
                results = getModel();
            }
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    //===================================================================================
    //Called by poller.
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
