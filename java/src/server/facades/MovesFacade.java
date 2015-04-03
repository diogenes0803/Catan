/**
 * 
 */
package server.facades;

import java.util.ArrayList;

import server.Server;
import server.commands.AcceptTradeCommand;
import server.commands.BuildCityCommand;
import server.commands.BuildRoadCommand;
import server.commands.BuildSettlementCommand;
import server.commands.BuyDevCardCommand;
import server.commands.Command;
import server.commands.DiscardCardsCommand;
import server.commands.FinishTurnCommand;
import server.commands.MaritimeTradeCommand;
import server.commands.MonopolyCommand;
import server.commands.MonumentCommand;
import server.commands.OfferTradeCommand;
import server.commands.PlaySoldierCommand;
import server.commands.RoadBuildingCommand;
import server.commands.RobPlayerCommand;
import server.commands.RollNumberCommand;
import server.commands.SendChatCommand;
import server.commands.YearOfPlentyCommand;
import server.model.ServerModel;
import shared.communicator.AcceptTradeParams;
import shared.communicator.BuildCityParams;
import shared.communicator.BuildRoadParams;
import shared.communicator.BuildSettlementParams;
import shared.communicator.BuyDevCardParams;
import shared.communicator.DiscardCardsParams;
import shared.communicator.FinishTurnParams;
import shared.communicator.MaritimeTradeParams;
import shared.communicator.MonopolyParams;
import shared.communicator.MonumentParams;
import shared.communicator.OfferTradeParams;
import shared.communicator.PlaySoldierParams;
import shared.communicator.RoadBuildingParams;
import shared.communicator.RobPlayerParams;
import shared.communicator.RollNumberParams;
import shared.communicator.SendChatParams;
import shared.communicator.YearOfPlentyParams;
import shared.models.CatanModel;
import shared.models.Game;
import shared.models.GameManager;

/**
 * Facade containing the server's implementation of all Move methods
 * @author campbeln
 */
public class MovesFacade implements Facade {
	
	private ArrayList<Command> commands = new ArrayList<Command>();
	
	/**
	 * @param params Data holder containing schema required for this function
	 * @param gameId 
	 * @return CatanModel object containing the updated Game model
	 */
	public void sendChat(SendChatParams params, int gameId) {
		 
		SendChatCommand command = new SendChatCommand(params, gameId);
		
		//should probably check canDo() stuff here
		
		command.execute();
		
		//Need to figure how to pass the game stuff and model back
		
		 return;
	}
	
	/**
	 * @param params Data holder containing schema required for this function
	 * @param gameId 
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel rollNumber(RollNumberParams params, int gameId) {
		 
		RollNumberCommand command = new RollNumberCommand(params, gameId);
		
		//should probably check canDo() stuff here
		ServerModel game = Server.models.get(gameId);
		
		command.execute();
		
		//Need to figure how to pass the game stuff and model back
		CatanModel model = new CatanModel();
		GameManager manager = new GameManager();
		manager.setGame(game);
		model.setGameManager(manager);
		
		
		 return model;
	}
	
	/**
	 * @param params Data holder containing schema required for this function
	 * @param gameId 
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel robPlayer(RobPlayerParams params, int gameId) {
		 
		RobPlayerCommand command = new RobPlayerCommand(params, gameId);
		
		//should probably check canDo() stuff here
		ServerModel game = Server.models.get(gameId);
		
		command.execute();
		
		//Need to figure how to pass the game stuff and model back
		CatanModel model = new CatanModel();
		GameManager manager = new GameManager();
		manager.setGame(game);
		model.setGameManager(manager);
		
		
		 return model;
	}
	
	/**
	 * @param params Data holder containing schema required for this function
	 * @param gameId 
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel finishTurn(FinishTurnParams params, int gameId) {
		 
		FinishTurnCommand command = new FinishTurnCommand(params, gameId);
		
		//should probably check canDo() stuff here
		ServerModel game = Server.models.get(gameId);
		
		command.execute();
		
		//Need to figure how to pass the game stuff and model back
		CatanModel model = new CatanModel();
		GameManager manager = new GameManager();
		manager.setGame(game);
		model.setGameManager(manager);
		
		
		 return model;
	}
	
	/**
	 * @param params Data holder containing schema required for this function
	 * @param gameId 
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel buyDevCard(BuyDevCardParams params, int gameId) {
		 
		BuyDevCardCommand command = new BuyDevCardCommand(params, gameId);
		
		//should probably check canDo() stuff here
		ServerModel game = Server.models.get(gameId);
		
		command.execute();
		
		//Need to figure how to pass the game stuff and model back
		CatanModel model = new CatanModel();
		GameManager manager = new GameManager();
		manager.setGame(game);
		model.setGameManager(manager);
		
		
		 return model;
	}
	
	/**
	 * @param params Data holder containing schema required for this function
	 * @param gameId 
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel yearOfPlenty(YearOfPlentyParams params, int gameId) {
		 
		YearOfPlentyCommand command = new YearOfPlentyCommand(params, gameId);
		
		//should probably check canDo() stuff here
		ServerModel game = Server.models.get(gameId);
		
		command.execute();
		
		//Need to figure how to pass the game stuff and model back
		CatanModel model = new CatanModel();
		GameManager manager = new GameManager();
		manager.setGame(game);
		model.setGameManager(manager);
		
		
		 return model;
	}
	
	/**
	 * @param params Data holder containing schema required for this function
	 * @param gameId 
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel roadBuilding(RoadBuildingParams params, int gameId) {
		 
		RoadBuildingCommand command = new RoadBuildingCommand(params, gameId);
		
		//should probably check canDo() stuff here
		ServerModel game = Server.models.get(gameId);
		
		command.execute();
		
		//Need to figure how to pass the game stuff and model back
		CatanModel model = new CatanModel();
		GameManager manager = new GameManager();
		manager.setGame(game);
		model.setGameManager(manager);
		
		
		 return model;
	}
	
	/**
	 * @param params Data holder containing schema required for this function
	 * @param gameId 
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel playSoldier(PlaySoldierParams params, int gameId) {
		 
		PlaySoldierCommand command = new PlaySoldierCommand(params, gameId);
		
		//should probably check canDo() stuff here
		ServerModel game = Server.models.get(gameId);
		
		command.execute();
		
		//Need to figure how to pass the game stuff and model back
		CatanModel model = new CatanModel();
		GameManager manager = new GameManager();
		manager.setGame(game);
		model.setGameManager(manager);
		
		
		 return model;
	}

	/**
	 * @param params Data holder containing schema required for this function
	 * @param gameId 
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel monopoly(MonopolyParams params, int gameId) {
		 
		MonopolyCommand command = new MonopolyCommand(params, gameId);
		
		//should probably check canDo() stuff here
		ServerModel game = Server.models.get(gameId);
		
		command.execute();
		
		//Need to figure how to pass the game stuff and model back
		CatanModel model = new CatanModel();
		GameManager manager = new GameManager();
		manager.setGame(game);
		model.setGameManager(manager);
		
		
		 return model;
	}
	
	 /**
	 * @param params Data holder containing schema required for this function
	 * @param gameId 
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel monument(MonumentParams params, int gameId) {
		 
		MonumentCommand command = new MonumentCommand(params, gameId);
		
		//should probably check canDo() stuff here
		ServerModel game = Server.models.get(gameId);
		
		command.execute();
		
		//Need to figure how to pass the game stuff and model back
		CatanModel model = new CatanModel();
		GameManager manager = new GameManager();
		manager.setGame(game);
		model.setGameManager(manager);
		
		
		 return model;
	 }
	 
	 /**
	 * @param params Data holder containing schema required for this function
	 * @param gameId 
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel buildRoad(BuildRoadParams params, int gameId) {
		
		BuildRoadCommand command = new BuildRoadCommand(params, gameId);
		
		//should probably check canDo() stuff here
		ServerModel game = Server.models.get(gameId);
		
		command.execute();
		
		//Need to figure how to pass the game stuff and model back
		CatanModel model = new CatanModel();
		GameManager manager = new GameManager();
		manager.setGame(game);
		model.setGameManager(manager);
		
		
		 return model;
	 }
	 
	 /**
	 * @param params Data holder containing schema required for this function
	 * @param gameId 
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel buildSettlement(BuildSettlementParams params, int gameId) {
		 
		BuildSettlementCommand command = new BuildSettlementCommand(params, gameId);
		
		//should probably check canDo() stuff here
		ServerModel game = Server.models.get(gameId);
		
		command.execute();
		
		//Need to figure how to pass the game stuff and model back
		CatanModel model = new CatanModel();
		GameManager manager = new GameManager();
		manager.setGame(game);
		model.setGameManager(manager);
		
		
		 return model;
	 }
	 
	 /**
	 * @param params Data holder containing schema required for this function
	 * @param gameId 
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel buildCity(BuildCityParams params, int gameId) {
		 
		BuildCityCommand command = new BuildCityCommand(params, gameId);
		
		//should probably check canDo() stuff here
		ServerModel game = Server.models.get(gameId);
		
		command.execute();
		
		//Need to figure how to pass the game stuff and model back
		CatanModel model = new CatanModel();
		GameManager manager = new GameManager();
		manager.setGame(game);
		model.setGameManager(manager);
		
		
		 return model;
	 }
	 
	 /**
	 * @param params Data holder containing schema required for this function
	 * @param gameId 
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel offerTrade(OfferTradeParams params, int gameId) {
		 
		OfferTradeCommand command = new OfferTradeCommand(params, gameId);
		
		//should probably check canDo() stuff here
		ServerModel game = Server.models.get(gameId);
		
		command.execute();
		
		//Need to figure how to pass the game stuff and model back
		CatanModel model = new CatanModel();
		GameManager manager = new GameManager();
		manager.setGame(game);
		model.setGameManager(manager);
		
		
		 return model;
	 }
	 
	 /**
	 * @param params Data holder containing schema required for this function
	 * @param gameId 
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel acceptTrade(AcceptTradeParams params, int gameId) {
		 
		AcceptTradeCommand command = new AcceptTradeCommand(params, gameId);
		
		//should probably check canDo() stuff here
		ServerModel game = Server.models.get(gameId);
		
		command.execute();
		
		//Need to figure how to pass the game stuff and model back
		CatanModel model = new CatanModel();
		GameManager manager = new GameManager();
		manager.setGame(game);
		model.setGameManager(manager);
		
		
		 return model;
	 }
	 
	 /**
	 * @param params Data holder containing schema required for this function
	 * @param gameId 
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel maritimeTrade(MaritimeTradeParams params, int gameId) {
		 
		MaritimeTradeCommand command = new MaritimeTradeCommand(params, gameId);
		
		//should probably check canDo() stuff here
		ServerModel game = Server.models.get(gameId);
		
		command.execute();
		
		//Need to figure how to pass the game stuff and model back
		CatanModel model = new CatanModel();
		GameManager manager = new GameManager();
		manager.setGame(game);
		model.setGameManager(manager);
		
		
		 return model;
	 }
	 
	 /**
	 * @param params Data holder containing schema required for this function
	 * @param gameId 
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel discardCards(DiscardCardsParams params, int gameId) {
		 
		DiscardCardsCommand command = new DiscardCardsCommand(params, gameId);
		
		//should probably check canDo() stuff here
		ServerModel game = Server.models.get(gameId);
		
		command.execute();
		
		//Need to figure how to pass the game stuff and model back
		CatanModel model = new CatanModel();
		GameManager manager = new GameManager();
		manager.setGame(game);
		model.setGameManager(manager);
		
		
		 return model;
	 }

}
