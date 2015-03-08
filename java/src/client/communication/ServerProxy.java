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
import shared.definitions.CatanColor;
import shared.models.CatanModel;
import shared.models.Game;
import shared.models.GameManager;
import shared.models.jsonholder.JsonModelHolder;
import client.data.GameInfo;
import client.data.PlayerInfo;

import com.google.gson.Gson;
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
    public static final int UNINITIALIZED_MODEL = -1;//-1 mean model not yet initialized
    public static final String PATH = "; Path=/";
    private final int HTTP_OK = HttpURLConnection.HTTP_OK;
    
    
    private ClientCommunicator clientComm;  
    
    //Two Cookies Needed
    private String playerCookie;
    private String gameCookie;
    private int version;
    private int currentGameId;
    private PlayerInfo localPlayer;
    private int pollerCallCount;
    

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
	public PlayerInfo getlocalPlayer() {
		return localPlayer;
	}
	public int getPollerCallCount() {
		return pollerCallCount;
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
            HttpURLResponse response = clientComm.post("/user/login", params, playerCookie);

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
                    localPlayer = new PlayerInfo();
                    localPlayer.setId(result.getPlayerId());
                    localPlayer.setName(result.getName());

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
            HttpURLResponse response = clientComm.post("/user/register", params, playerCookie);
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
                    localPlayer = new PlayerInfo();
                    localPlayer.setId(result.getPlayerId());
                    localPlayer.setName(result.getName());
                    

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
        	
            HttpURLResponse response = clientComm.get("/games/list", playerCookie);

          
            result.setSuccess(response.getResponseCode() == HTTP_OK);
            
            result.setResponseBody(response.getResponseBody());
            
            if(result.isSuccess()){
                JsonArray ja = (JsonArray)new JsonParser().parse(result.getResponseBody()); //array of games
                GameInfo[] games = new GameInfo[ja.size()];
                for(int i=0; i< ja.size(); i++){

                    JsonObject game = (JsonObject)ja.get(i);
                    String game_name = game.get("title").getAsString();
                    int game_id = game.get("id").getAsInt();
                    
                    JsonArray playerArray = (JsonArray) game.get("players");
                    
                    GameInfo catanGame = new GameInfo();
                    catanGame.setId(game_id);
                    catanGame.setTitle(game_name);
                    for(int j=0; j<playerArray.size(); j++) {
                    	if (playerArray.get(j) != null)
                    	{
                    		JsonObject player = (JsonObject)playerArray.get(j);
                    		//Takes care of the case where there's a game waiting for players to join
                    		if (player.get("id") != null)
                    		{
		                    	int playerId = player.get("id").getAsInt();
		                    	CatanColor color = CatanColor.getCatanColor(player.get("color").getAsString());
		                    	String name = player.get("name").getAsString();
		                    	PlayerInfo thisPlayer = new PlayerInfo();
		                    	thisPlayer.setId(playerId);
		                    	thisPlayer.setColor(color);
		                    	thisPlayer.setName(name);
		                    	catanGame.addPlayer(thisPlayer);
                    		}
                    	}
                    }
                    games[i] = catanGame;
                }
                result.setGames(games);
            }
           
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

        JoinGameResults result = new JoinGameResults();
      
        try {//only uses player cookie
        	HttpURLResponse response = clientComm.post("/games/join", params, playerCookie);   

            result.setSuccess(response.getResponseCode() == HTTP_OK);
            gameCookie = "catan.game="+params.getId();
            currentGameId = params.getId();
            result.setResponseBody(response.getResponseBody());

        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.out.println(e.toString());
            result.setSuccess(false);
        }

        return result;
    }

    //===================================================================================
    @Override
    public CreateGameResults createGame(CreateGameParams params) {
        CreateGameResults results = new CreateGameResults();

        try {
        	HttpURLResponse response = clientComm.post("/games/create", params, playerCookie+"; "+gameCookie);
        	Gson gson = new Gson();
        	results = gson.fromJson(response.getResponseBody().toString(), CreateGameResults.class);
        	
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

        SaveGameResults result = new SaveGameResults();

        try {
        	HttpURLResponse response = clientComm.post("/games/save", params, playerCookie);   

            result.setSuccess(response.getResponseCode() == HTTP_OK);
            
            result.setResponseBody(response.getResponseBody());
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }
    //===================================================================================
    
    @Override
    public LoadGameResults loadGame(LoadGameParams params) {

        LoadGameResults result = new LoadGameResults();

        try {
            HttpURLResponse response = clientComm.post("load", params, playerCookie);   

            result.setSuccess(response.getResponseCode() == HTTP_OK);
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
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
            HttpURLResponse response = clientComm.get("/game/model"+strVersion, playerCookie+"; "+gameCookie);
            result_model = getCatanModel(response);
        } catch (ClientException e) {
                      
            System.out.println(e);
            
        }
        pollerCallCount++;

        return result_model;
    }
    //===================================================================================
    
    private CatanModel getCatanModel(HttpURLResponse response) {
        CatanModel result_model = new CatanModel();
    	Gson gson = new Gson();
        if(response.getResponseBody().equals("\"true\""))
        	return null;
		JsonModelHolder modelHolder = gson.fromJson(response.getResponseBody().toString(), JsonModelHolder.class);
		Game thisGame = modelHolder.buildCatanGame();
		GameManager manager = new GameManager();
		result_model.setGameManager(manager);
		result_model.getGameManager().setGame(thisGame);
		version = result_model.getGameManager().getGame().getVersion();
        return result_model;
    }
    
    @Override
    public CatanModel resetGame() {
        CatanModel results = new CatanModel();

        try {
            HttpURLResponse response = clientComm.post("/game/reset", null, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
            
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
            HttpURLResponse response = clientComm.get("/game/commands", playerCookie+"; "+gameCookie);
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
        	HttpURLResponse response = clientComm.post("/game/commands", params, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
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
        	 HttpURLResponse response = clientComm.get("/game/listAI", playerCookie+"; "+gameCookie);
    		Gson gson = new Gson();
        	results = gson.fromJson(response.getResponseBody().toString(), ListAIResults.class);
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

        ChangeLogLevelResults result = new ChangeLogLevelResults();

        try {
        	HttpURLResponse response = clientComm.post("/util/changeLogLevel", params, playerCookie+"; "+gameCookie);
            result.setSuccess(response.getResponseCode() == HTTP_OK);
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }
    //===================================================================================
    
    @Override
    public CatanModel sendChat(SendChatParams params) {
        CatanModel results = new CatanModel();

        try {
        	HttpURLResponse response = clientComm.post("/moves/sendChat", params, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
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
        	HttpURLResponse response = clientComm.post("/moves/acceptTrade", params, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
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
        	HttpURLResponse response = clientComm.post("/moves/discardCards", params, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
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
        	HttpURLResponse response = clientComm.post("/moves/rollNumber", params, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
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
        	HttpURLResponse response = clientComm.post("/moves/buildRoad", params, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
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
        	HttpURLResponse response = clientComm.post("/moves/buildSettlement", params, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
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
        	HttpURLResponse response = clientComm.post("/moves/buildCity", params, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
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
        	HttpURLResponse response = clientComm.post("/moves/offerTrade", params, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
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
        	HttpURLResponse response = clientComm.post("/moves/maritimeTrade", params, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
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
        	HttpURLResponse response = clientComm.post("/moves/robPlayer", params, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
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
        	HttpURLResponse response = clientComm.post("/moves/finishTurn", params, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
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
        	HttpURLResponse response = clientComm.post("/moves/buyDevCard", params, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
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
        	HttpURLResponse response = clientComm.post("/moves/Soldier", params, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
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
        	HttpURLResponse response = clientComm.post("/moves/Year_Of_Plenty", params, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
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
        	HttpURLResponse response = clientComm.post("/moves/Road_Building", params, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
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
        	HttpURLResponse response = clientComm.post("/moves/Monopoly", params, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
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
        	HttpURLResponse response = clientComm.post("/moves/monument", params, playerCookie+"; "+gameCookie);
            results = getCatanModel(response);
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        return results;
    }
    //===================================================================================
    //===================================================================================


    @Override
    public AddAIResults AddAI(String params) {
    	AddAIResults result = new AddAIResults();
        
    	try {
    		HttpURLResponse response = clientComm.post("/game/addAI", params, playerCookie+"; "+gameCookie);
            result.setSuccess(response.getResponseCode() == HTTP_OK);
			
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return result;
    }

}
