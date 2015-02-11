/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.communication.ServerProxy;
import shared.communicator.*;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.models.CatanModel;
import shared.models.Game;
import shared.models.ResourceList;

/**
 * @author campbeln
 *
 */
public class ServerProxyTest {
    private ServerProxy serverProxy = new ServerProxy("localhost","8081");
    private UserName goodUser;
    private UserName badUser;
    private String goodUsername;
    private String goodPassword;
    
    @Before
    public void setUp() throws Exception {
        goodUsername = "Sam";
        goodPassword = "sam";
        
    }

    @After
    public void tearDown() throws Exception {
    }
    
    

    /**
     * Test method for {@link client.communication.ServerProxy#ServerProxy(shared.models.CatanModel)}.
     */
    /*
    @Test
    public void test_1_UserName() {
    	
        System.out.println("Testing Good Users");
        boolean assertionOccured = false;
        try{
            goodUser = new UserName(goodUsername);
        }catch(AssertionError e){
            assertionOccured = true;
        } 
        assertFalse("Error: '"+goodUsername+"' was not accepted as a valid name.", assertionOccured);
        
        
      //should fail on each bad try:
        System.out.println("Testing Bad Users:\n");
        
        String[] tests = {"'","`","(",")","{","}", "[","]",
                          "-","+","=",".",":","\"","@"," ",
                          "<",">","?","%","$","#","~",",",
                          "\n","&","*","^","/",";","|","\\",
                          "�","�","�","�","�","�","�","Bad Wolf"};
        
        for(int i=0; i < tests.length; i++){
          String testString = tests[i];
          assertionOccured = false;
          try{
            badUser = new UserName(testString);
          }catch(AssertionError e){
             assertionOccured = true;
          }    
          assertTrue("UserName wrongly accepted the non-AlphanumericCharacter '"+testString+"'", 
                assertionOccured);
        }
        
       
        
    }//end testUser
    */
    /**
     * Test method for {@link client.communication.ServerProxy#registerUser(shared.communicator.RegisterUserParams)}.
     */
    @Test
    public void test_2_RegisterUser() {
       // System.out.println("Testing Registering of GoodUser: "+ "bob1" + " with password: "+ goodPassword);
       //use already tested goodUserName
        RegisterUserParams params = new RegisterUserParams("bob5", goodPassword);
        
        RegisterUserResults results = serverProxy.registerUser(params);
        
       // assertTrue("Error: Username "+ goodUser +" was not accepted.",results.isSuccess());
        
        System.out.println("Testing RegisterUser for Connection");
        assertTrue("Error: RegisterUser connection didn't work", results.isSuccess());
        
    }

    /**
     * Test method for {@link client.communication.ServerProxy#userLogin(shared.communicator.UserLoginParams)}.
     */
    @Test
    public void test_3_UserLogin() {
        UserLoginParams params = new UserLoginParams(goodUsername, goodPassword);
        
        UserLoginResults results = serverProxy.userLogin(params);
        
        //if (!results.isSuccess()) {
        //  fail("User jonathan failed to login");
        //}
        
        //assertTrue("message to display if boolean is false", boolean)
        assertTrue("Jonathan ",results.isSuccess());
        
        System.out.println("Testing UserLogin for Connection");
        assertTrue("Error: UserLogin connection didn't work", results.isSuccess());
        
    }
    
    /**
     * Test method for {@link client.communication.ServerProxy#createGame(shared.communicator.CreateGameParams)}.
     */
    @Test
    public void test_4_CreateGame() {
        CreateGameParams params = new CreateGameParams(true,false,true,"test_game1");
        
        CreateGameResults results = serverProxy.createGame(params);
        System.out.println("Testing CreateGame for Connection");
        assertTrue("Error: CreateGame connection didn't work", results.isSuccess());
    }
    

    /**
     * Test method for {@link client.communication.ServerProxy#listGames()}.
     */
    @Test
    public void test_5_ListGames() {
        //see if the game we created in test 4 showed up.
         ListGamesResults results = serverProxy.listGames();
         System.out.println("Testing ListGames for Connection");
         assertTrue("Error: ListGames connection didn't work", results.isSuccess());
        
    	
    }


    /**
     * Test method for {@link client.communication.ServerProxy#joinGame(shared.communicator.JoinGameParams)}.
     */
    @Test
    public void test_6_JoinGame() {
    	 UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
         UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params = new JoinGameParams(0, "red");
    	
    	JoinGameResults results = serverProxy.joinGame(params);
    	
    	System.out.println("Testing JoinGame for Connection");
        assertTrue("Error: JoinGame connection didn't work", results.isSuccess());
        
    }

    /**
     * Test method for {@link client.communication.ServerProxy#saveGame(shared.communicator.SaveGameParams)}.
     */
    @Test
    public void test_7_SaveGame() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results2 = serverProxy.joinGame(params2);
    	SaveGameParams params = new SaveGameParams(0, "test_save");
    	
    	SaveGameResults results = serverProxy.saveGame(params);
    	
    	System.out.println("Testing SaveGame for Connection");
        assertTrue("Error: SaveGame connection didn't work", results.isSuccess());
    }

    /**
     * Test method for {@link client.communication.ServerProxy#loadGame(shared.communicator.LoadGameParams)}.
     */
    @Test
    public void test_8_LoadGame() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	LoadGameParams params = new LoadGameParams("test_save");
    	LoadGameResults results = serverProxy.loadGame(params);
    	
    	System.out.println("Testing LoadGame for Connection");
        assertTrue("Error: LoadGame connection didn't work", results.isSuccess());
    }

    /**
     * Test method for {@link client.communication.ServerProxy#getModel()}.
     */
    @Test
    public void test_9_GetModel() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
         UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params);
    	
    	CatanModel model = serverProxy.getModel();
    	System.out.println("Testing GetModel Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: GetModel connection didn't work", value);
    }

    /**
     * Test method for {@link client.communication.ServerProxy#resetGame()}.
     */
    @Test
    public void test_10_ResetGame() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params);
    	
    	CatanModel model = serverProxy.resetGame();
    	System.out.println("Testing ResetGame Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: ResetGame connection didn't work", value);
    	
    }

    /**
     * Test method for {@link client.communication.ServerProxy#getCommands(shared.communicator.GetCommandsParams)}.
     */
    @Test
    public void test_11_GetCommands() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results2 = serverProxy.joinGame(params2);
    	
        GetCommandsResults results = serverProxy.getCommands();
        System.out.println("Testing GetCommands for Connection");
        assertTrue("Error: GetCommands connection didn't work", results.isSuccess());
    }

    /**
     * Test method for {@link client.communication.ServerProxy#executeCommands(shared.communicator.ExecuteCommandsParams)}.
     */
    @Test
    public void test_12_ExecuteCommands() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);
    	GetCommandsResults getCommandsResults = serverProxy.getCommands();
    	
    	ArrayList<String> list = new ArrayList<String>();
    	
    	ExecuteCommandsParams params = new ExecuteCommandsParams(list);
    	
    	CatanModel model = serverProxy.executeCommands(params);
    	
    	System.out.println("Testing ExecuteCommands Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: ExecuteCommands connection didn't work", value);
    	
    }

    /**
     * Test method for {@link client.communication.ServerProxy#listAI(shared.communicator.ListAIParams)}.
     */
    @Test
    public void test_13_ListAI() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);
    	
        ListAIResults results2 = serverProxy.listAI();
        
        System.out.println("Testing ListAI for Connection");
        assertTrue("Error: ListAI connection didn't work", results2.isSuccess());
    }

    /**
     * Test method for {@link client.communication.ServerProxy#changeLogLevel(shared.communicator.ChangeLogLevelParams)}.
     */
    @Test
    public void test_14_ChangeLogLevel() {
        ChangeLogLevelParams params = new ChangeLogLevelParams("INFO");
        
        ChangeLogLevelResults result = serverProxy.changeLogLevel(params);
        System.out.println("Testing ChangeLogLevel for Connection");
        assertTrue("Error: ChangeLogLevel connection didn't work", result.isSuccess());
        
    }

    /**
     * Test method for {@link client.communication.ServerProxy#sendChat(shared.communicator.SendChatParams)}.
     */
    @Test
    public void test_15_SendChat() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);
    	
        SendChatParams params = new SendChatParams("hello", 0);
        params.setType("sendChat");
        
        CatanModel model = serverProxy.sendChat(params);
        
        System.out.println("Testing SendChat Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: SendChat connection didn't work", value);
        
        
    }

    /**
     * Test method for {@link client.communication.ServerProxy#acceptTrade(shared.communicator.AcceptTradeParams)}.
     */
    @Test
    public void test_16_AcceptTrade() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);
    	
       AcceptTradeParams params = new AcceptTradeParams(0,false );
        params.setType("acceptTrade");
        
        CatanModel model = serverProxy.acceptTrade(params);
        
        System.out.println("Testing AcceptTrade Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: AcceptTrade connection didn't work", value);
    }

    /**
     * Test method for {@link client.communication.ServerProxy#discardCards(shared.communicator.DiscardCardsParams)}.
     */
    @Test
    public void test_17_DiscardCards() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);
    	
    	ResourceList list = new ResourceList(0,0,0,0,0);
       DiscardCardsParams params = new  DiscardCardsParams(0,list );
        params.setType("discardCards");
        
        CatanModel model = serverProxy.discardCards(params);
        
        System.out.println("Testing DiscardCards Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: DiscardCards connection didn't work", value);
    }

    /**
     * Test method for {@link client.communication.ServerProxy#rollNumber(shared.communicator.RollNumberParams)}.
     */
    @Test
    public void test_18_RollNumber() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);
    	
        RollNumberParams params = new RollNumberParams(0,4 );
        params.setType("rollNumber");
        
        CatanModel model = serverProxy.rollNumber(params);
        
        System.out.println("Testing RollNumber Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: RollNumber connection didn't work", value);
    }

    /**
     * Test method for {@link client.communication.ServerProxy#buildRoad(shared.communicator.BuildRoadParams)}.
     */
    @Test
    public void test_19_BuildRoad() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);

    	HexLocation hex = new HexLocation(0,0);
    	
    	EdgeLocation edge = new EdgeLocation(hex, EdgeDirection.NorthWest );
       BuildRoadParams params = new  BuildRoadParams(0,edge,true );
        params.setType("buildRoad");
        
        CatanModel model = serverProxy.buildRoad(params);
        
        System.out.println("Testing BuildRoad Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: BuildRoad connection didn't work", value);
    }

    /**
     * Test method for {@link client.communication.ServerProxy#buildSettlement(shared.communicator.BuildSettlementParams)}.
     */
    @Test
    public void test_20_BuildSettlement() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);

    	HexLocation hex = new HexLocation(-1,-1);
    	
    	VertexLocation edge = new VertexLocation(hex, VertexDirection.NorthWest );
       BuildSettlementParams params = new  BuildSettlementParams(0,edge,true );
        params.setType("buildSettlement");
        
        CatanModel model = serverProxy.buildSettlement(params);
        
        System.out.println("Testing BuildSettlement Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: BuildSettlement connection didn't work", value);
    }

    /**
     * Test method for {@link client.communication.ServerProxy#buildCity(shared.communicator.BuildCityParams)}.
     */
    @Test
    public void test_21_BuildCity() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);

    	HexLocation hex = new HexLocation(-1,-1);
    	
    	VertexLocation edge = new VertexLocation(hex, VertexDirection.NorthWest );
       BuildCityParams params = new  BuildCityParams(0,edge);
        params.setType("buildCity");
        
        CatanModel model = serverProxy.buildCity(params);
        
        System.out.println("Testing BuildCity Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: BuildCity connection didn't work", value);
    }

    /**
     * Test method for {@link client.communication.ServerProxy#offerTrade(shared.communicator.OfferTradeParams)}.
     */
    @Test
    public void test_22_OfferTrade() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);
    	
    	ResourceList list = new ResourceList(1,1,1,1,1);
       OfferTradeParams params = new OfferTradeParams(1,list,1);
        params.setType("offerTrade");
        
        CatanModel model = serverProxy.offerTrade(params);
        
        System.out.println("Testing OfferTrade Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: OfferTrade connection didn't work", value);
    }

    /**
     * Test method for {@link client.communication.ServerProxy#maritimeTrade(shared.communicator.MaritimeTradeParams)}.
     */
    @Test
    public void test_23_MaritimeTrade() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);
    	
       MaritimeTradeParams params = new MaritimeTradeParams(0, 1, "wheat", "ore");
        params.setType("maritimeTrade");
        
        CatanModel model = serverProxy.maritimeTrade(params);
        
        System.out.println("Testing MaritimeTrade Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: MaritimeTrade connection didn't work", value);
    }

    /**
     * Test method for {@link client.communication.ServerProxy#robPlayer(shared.communicator.RobPlayerParams)}.
     */
    @Test
    public void test_24_RobPlayer() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);
    	
    	HexLocation hex = new HexLocation(-1,-1);
      RobPlayerParams params = new RobPlayerParams(0, 1, hex);
        params.setType("robPlayer");
        
        CatanModel model = serverProxy.robPlayer(params);
        
        System.out.println("Testing RobPlayer Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: RobPlayer connection didn't work", value);
    }

    /**
     * Test method for {@link client.communication.ServerProxy#finishTurn(shared.communicator.FinishTurnParams)}.
     */
    @Test
    public void test_25_FinishTurn() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);
    	
 
      FinishTurnParams params = new FinishTurnParams(0);
        params.setType("finishTurn");
        
        CatanModel model = serverProxy.finishTurn(params);
        
        System.out.println("Testing FinishTurn Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: FinishTurn connection didn't work", value);
    }

    /**
     * Test method for {@link client.communication.ServerProxy#buyDevCard(shared.communicator.BuyDevCardParams)}.
     */
    @Test
    public void test_26_BuyDevCard() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);
    	
      BuyDevCardParams params = new BuyDevCardParams(0);
        params.setType("buyDevCard");
        
        CatanModel model = serverProxy.buyDevCard(params);
        
        System.out.println("Testing BuyDevCard Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: BuyDevCard connection didn't work", value);
    }

    /**
     * Test method for {@link client.communication.ServerProxy#playSoldier(shared.communicator.PlaySoldierParams)}.
     */
    @Test
    public void test_27_PlaySoldier() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);
    	
    	HexLocation hex = new HexLocation(1,-1);
      PlaySoldierParams params = new PlaySoldierParams(0, 1, hex);
        params.setType("Soldier");
        
        CatanModel model = serverProxy.playSoldier(params);
        
        System.out.println("Testing PlaySoldier Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: PlaySoldier connection didn't work", value);
    }

    /**
     * Test method for {@link client.communication.ServerProxy#yearOfPlenty(shared.communicator.YearOfPlentyParams)}.
     */
    @Test
    public void test_28_YearOfPlenty() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);
    	
      YearOfPlentyParams params = new YearOfPlentyParams(0, ResourceType.WHEAT, ResourceType.WHEAT);
        params.setType("Year_of_Plenty");
        
        CatanModel model = serverProxy.yearOfPlenty(params);
        
        System.out.println("Testing Year of Plenty Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: Year of Plenty connection didn't work", value);
    }

    /**
     * Test method for {@link client.communication.ServerProxy#roadBuilding(shared.communicator.RoadBuildingParams)}.
     */
    @Test
    public void test_29_RoadBuilding() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);
    	
    	HexLocation hex = new HexLocation(1,-1);
    	EdgeLocation edge1 = new EdgeLocation(hex, EdgeDirection.SouthWest );
    	EdgeLocation edge2 = new EdgeLocation(hex, EdgeDirection.NorthWest );
      RoadBuildingParams params = new RoadBuildingParams(0, edge1, edge2);
        params.setType("Road_Building");
        
        CatanModel model = serverProxy.roadBuilding(params);
        
        System.out.println("Testing RoadBuilding Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: RoadBuilding connection didn't work", value);
    }

    /**
     * Test method for {@link client.communication.ServerProxy#monopoly(shared.communicator.MonopolyParams)}.
     */
    @Test
    public void test_30_Monopoly() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);
    	
      MonopolyParams params = new MonopolyParams(0, "ore");
        params.setType("Monopoly");
        
        CatanModel model = serverProxy.monopoly(params);
        
        System.out.println("Testing Monopoly Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: Monopoly connection didn't work", value);
    }

    /**
     * Test method for {@link client.communication.ServerProxy#monument(shared.communicator.MonumentParams)}.
     */
    @Test
    public void test_31_Monument() {
    	UserLoginParams params1 = new UserLoginParams(goodUsername, goodPassword);
    	UserLoginResults results1 = serverProxy.userLogin(params1);
    	JoinGameParams params2 = new JoinGameParams(0, "red");
    	JoinGameResults results = serverProxy.joinGame(params2);
    	
      MonumentParams params = new MonumentParams(0);
        params.setType("Monument");
        
        CatanModel model = serverProxy.monument(params);
        
        System.out.println("Testing Monument Function for connection");
        boolean value = false;
		if (model.getGameManager().getGame() != null)
		{
			value = true;
		}
        assertTrue("Error: Monument connection didn't work", value);
    }
    /**
     * Test method for {@link client.communication.ServerProxy#updateModel()}.
     */
    @Test
    public void test_35_UpdateModel() {
    	System.out.println("UpdateModel is only used in the mock Server");
        assertTrue("Only used in MockServer", true); // TODO
    }

}
