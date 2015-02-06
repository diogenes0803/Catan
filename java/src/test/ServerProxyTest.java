/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.Test;

import client.communication.ServerProxy;
import shared.communicator.*;

/**
 * @author campbeln
 *
 */
public class ServerProxyTest {
	
	private ServerProxy serverProxy = new ServerProxy();

	/**
	 * Test method for {@link client.communication.ServerProxy#ServerProxy(shared.models.CatanModel)}.
	 */
	@Test
	public void testServerProxy() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#userLogin(shared.communicator.UserLoginParams)}.
	 */
	@Test
	public void testUserLogin() {
		UserName username = new UserName("johnathan");
		UserLoginParams params = new UserLoginParams(username, "changeme");
		
		UserLoginResults results = serverProxy.userLogin(params);
		
		if (!results.isSuccess()) {
			fail("User failed to login");
		}
		
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#registerUser(shared.communicator.RegisterUserParams)}.
	 */
	@Test
	public void testRegisterUser() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#listGames()}.
	 */
	@Test
	public void testListGames() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#createGame(shared.communicator.CreateGameParams)}.
	 */
	@Test
	public void testCreateGame() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#joinGame(shared.communicator.JoinGameParams)}.
	 */
	@Test
	public void testJoinGame() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#saveGame(shared.communicator.SaveGameParams)}.
	 */
	@Test
	public void testSaveGame() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#loadGame(shared.communicator.LoadGameParams)}.
	 */
	@Test
	public void testLoadGame() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#getModel()}.
	 */
	@Test
	public void testGetModel() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#resetGame()}.
	 */
	@Test
	public void testResetGame() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#getCommands(shared.communicator.GetCommandsParams)}.
	 */
	@Test
	public void testGetCommands() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#executeCommands(shared.communicator.ExecuteCommandsParams)}.
	 */
	@Test
	public void testExecuteCommands() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#listAI(shared.communicator.ListAIParams)}.
	 */
	@Test
	public void testListAI() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#changeLogLevel(shared.communicator.ChangeLogLevelParams)}.
	 */
	@Test
	public void testChangeLogLevel() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#sendChat(shared.communicator.SendChatParams)}.
	 */
	@Test
	public void testSendChat() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#acceptTrade(shared.communicator.AcceptTradeParams)}.
	 */
	@Test
	public void testAcceptTrade() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#discardCards(shared.communicator.DiscardCardsParams)}.
	 */
	@Test
	public void testDiscardCards() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#rollNumber(shared.communicator.RollNumberParams)}.
	 */
	@Test
	public void testRollNumber() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#buildRoad(shared.communicator.BuildRoadParams)}.
	 */
	@Test
	public void testBuildRoad() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#buildSettlement(shared.communicator.BuildSettlementParams)}.
	 */
	@Test
	public void testBuildSettlement() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#buildCity(shared.communicator.BuildCityParams)}.
	 */
	@Test
	public void testBuildCity() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#offerTrade(shared.communicator.OfferTradeParams)}.
	 */
	@Test
	public void testOfferTrade() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#maritimeTrade(shared.communicator.MaritimeTradeParams)}.
	 */
	@Test
	public void testMaritimeTrade() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#robPlayer(shared.communicator.RobPlayerParams)}.
	 */
	@Test
	public void testRobPlayer() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#finishTurn(shared.communicator.FinishTurnParams)}.
	 */
	@Test
	public void testFinishTurn() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#buyDevCard(shared.communicator.BuyDevCardParams)}.
	 */
	@Test
	public void testBuyDevCard() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#playSoldier(shared.communicator.PlaySoldierParams)}.
	 */
	@Test
	public void testPlaySoldier() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#yearOfPlenty(shared.communicator.YearOfPlentyParams)}.
	 */
	@Test
	public void testYearOfPlenty() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#roadBuilding(shared.communicator.RoadBuildingParams)}.
	 */
	@Test
	public void testRoadBuilding() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#monopoly(shared.communicator.MonopolyParams)}.
	 */
	@Test
	public void testMonopoly() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#monument(shared.communicator.MonumentParams)}.
	 */
	@Test
	public void testMonument() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#rollDice(shared.communicator.MoveParams)}.
	 */
	@Test
	public void testRollDice() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#distributeCards(int)}.
	 */
	@Test
	public void testDistributeCards() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#startGame()}.
	 */
	@Test
	public void testStartGame() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#leaveGame()}.
	 */
	@Test
	public void testLeaveGame() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link client.communication.ServerProxy#updateModel()}.
	 */
	@Test
	public void testUpdateModel() {
		fail("Not yet implemented"); // TODO
	}

}
