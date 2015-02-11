/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.communication.ServerProxy;
import shared.communicator.*;
import shared.models.CatanModel;
import shared.models.Game;

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
    @Test
    public void test_1_UserName() {
    	/*
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
        */
       
        
    }//end testUser
    
    /**
     * Test method for {@link client.communication.ServerProxy#registerUser(shared.communicator.RegisterUserParams)}.
     */
    @Test
    public void test_2_RegisterUser() {
        System.out.println("Testing Registering of GoodUser: "+ "bob1" + " with password: "+ goodPassword);
       //use already tested goodUserName
        RegisterUserParams params = new RegisterUserParams("bob5", goodPassword);
        
        RegisterUserResults results = serverProxy.registerUser(params);
        
       // assertTrue("Error: Username "+ goodUser +" was not accepted.",results.isSuccess());
        
        System.out.println("Return from registerUser "+ results.isSuccess());
        
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
        
        System.out.println("User logged in? "+results.isSuccess());
        
    }
    
    /**
     * Test method for {@link client.communication.ServerProxy#createGame(shared.communicator.CreateGameParams)}.
     */
    @Test
    public void test_4_CreateGame() {
        CreateGameParams params = new CreateGameParams(true,false,true,"test_game1");
        
        CreateGameResults results = serverProxy.createGame(params);
        System.out.println("Did create Game Work "+results.isSuccess());
    }
    

    /**
     * Test method for {@link client.communication.ServerProxy#listGames()}.
     */
    @Test
    public void test_5_ListGames() {
        //see if the game we created in test 4 showed up.
         ListGamesResults results = serverProxy.listGames();
         System.out.println("List games returned fine " + results.isSuccess());
        
    	
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
    	
    	System.out.println("Join Game result: "+results.isSuccess());
        
    }

    /**
     * Test method for {@link client.communication.ServerProxy#saveGame(shared.communicator.SaveGameParams)}.
     */
    @Test
    public void test_7_SaveGame() {
    	SaveGameParams params = new SaveGameParams(0, "test_save");
    	
    	SaveGameResults results = serverProxy.saveGame(params);
    	
    	System.out.println("SaveGame worked: "+results.isSuccess());
    }

    /**
     * Test method for {@link client.communication.ServerProxy#loadGame(shared.communicator.LoadGameParams)}.
     */
    @Test
    public void test_8_LoadGame() {
        fail("Not yet implemented"); // TODO
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
    	System.out.println("GetModel: "+ model.getGameManager().getGame().getGameId());
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
    	System.out.println("ResetGame: "+ model.getGameManager().getGame().getGameId());
    	
    }

    /**
     * Test method for {@link client.communication.ServerProxy#getCommands(shared.communicator.GetCommandsParams)}.
     */
    @Test
    public void test_11_GetCommands() {
        GetCommandsResults results = serverProxy.getCommands();
        System.out.println("GetCommands worked: "+results.isSuccess());
    }

    /**
     * Test method for {@link client.communication.ServerProxy#executeCommands(shared.communicator.ExecuteCommandsParams)}.
     */
    @Test
    public void test_12_ExecuteCommands() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#listAI(shared.communicator.ListAIParams)}.
     */
    @Test
    public void test_13_ListAI() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#changeLogLevel(shared.communicator.ChangeLogLevelParams)}.
     */
    @Test
    public void test_14_ChangeLogLevel() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#sendChat(shared.communicator.SendChatParams)}.
     */
    @Test
    public void test_15_SendChat() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#acceptTrade(shared.communicator.AcceptTradeParams)}.
     */
    @Test
    public void test_16_AcceptTrade() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#discardCards(shared.communicator.DiscardCardsParams)}.
     */
    @Test
    public void test_17_DiscardCards() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#rollNumber(shared.communicator.RollNumberParams)}.
     */
    @Test
    public void test_18_RollNumber() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#buildRoad(shared.communicator.BuildRoadParams)}.
     */
    @Test
    public void test_19_BuildRoad() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#buildSettlement(shared.communicator.BuildSettlementParams)}.
     */
    @Test
    public void test_20_BuildSettlement() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#buildCity(shared.communicator.BuildCityParams)}.
     */
    @Test
    public void test_21_BuildCity() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#offerTrade(shared.communicator.OfferTradeParams)}.
     */
    @Test
    public void test_22_OfferTrade() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#maritimeTrade(shared.communicator.MaritimeTradeParams)}.
     */
    @Test
    public void test_23_MaritimeTrade() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#robPlayer(shared.communicator.RobPlayerParams)}.
     */
    @Test
    public void test_24_RobPlayer() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#finishTurn(shared.communicator.FinishTurnParams)}.
     */
    @Test
    public void test_25_FinishTurn() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#buyDevCard(shared.communicator.BuyDevCardParams)}.
     */
    @Test
    public void test_26_BuyDevCard() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#playSoldier(shared.communicator.PlaySoldierParams)}.
     */
    @Test
    public void test_27_PlaySoldier() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#yearOfPlenty(shared.communicator.YearOfPlentyParams)}.
     */
    @Test
    public void test_28_YearOfPlenty() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#roadBuilding(shared.communicator.RoadBuildingParams)}.
     */
    @Test
    public void test_29_RoadBuilding() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#monopoly(shared.communicator.MonopolyParams)}.
     */
    @Test
    public void test_30_Monopoly() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#monument(shared.communicator.MonumentParams)}.
     */
    @Test
    public void test_31_Monument() {
        fail("Not yet implemented"); // TODO
    }


    /**
     * Test method for {@link client.communication.ServerProxy#distributeCards(int)}.
     */
    @Test
    public void test_32_DistributeCards() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#startGame()}.
     */
    @Test
    public void test_33_StartGame() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#leaveGame()}.
     */
    @Test
    public void test_34_LeaveGame() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link client.communication.ServerProxy#updateModel()}.
     */
    @Test
    public void test_35_UpdateModel() {
        fail("Not yet implemented"); // TODO
    }

}
