/**
 * 
 */
package server.facades;

import java.awt.List;
import java.util.ArrayList;

import server.Server;
import server.commands.BuildSettlementCommand;
import server.commands.Command;
import server.model.ServerModel;
import shared.communicator.*;
import shared.models.CatanModel;
import shared.models.GameManager;

/**
 * Facade containing the server's implementation of all Move methods
 * @author campbeln
 */
public class MovesFacade implements Facade {
	
	private ArrayList<Command> commands = new ArrayList<Command>();
	
	/**
	 * @param params Data holder containing schema required for this function
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel sendChat(SendChatParams params) {
		//TODO sendChat method
		return null;
	}
	
	/**
	 * @param params Data holder containing schema required for this function
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel rollNumber(RollNumberParams params) {
		//TODO rollNumber method
		return null;
	}
	
	/**
	 * @param params Data holder containing schema required for this function
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel robPlayer(RobPlayerParams params) {
		//TODO robPlayer method
		return null;
	}
	
	/**
	 * @param params Data holder containing schema required for this function
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel finishTurn(FinishTurnParams params) {
		//TODO finishTurn method
		return null;
	}
	
	/**
	 * @param params Data holder containing schema required for this function
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel buyDevCard(BuyDevCardParams params) {
		//TODO buyDevCard method
		return null;
	}
	
	/**
	 * @param params Data holder containing schema required for this function
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel yearOfPlenty(YearOfPlentyParams params) {
		//TODO yearOfPlenty method
		return null;
	}
	
	/**
	 * @param params Data holder containing schema required for this function
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel roadBuilding(RoadBuildingParams params) {
		//TODO roadBuilding method
		return null;
	}
	
	/**
	 * @param params Data holder containing schema required for this function
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel playSoldier(PlaySoldierParams params) {
		//TODO playSoldier method
		return null;
	}

	/**
	 * @param params Data holder containing schema required for this function
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel monopoly(MonopolyParams params) {
		//TODO monopoly method
		return null;
	}
	
	 /**
	 * @param params Data holder containing schema required for this function
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel monument(MonumentParams params) {
		 //TODO monument method
		 return null;
	 }
	 
	 /**
	 * @param params Data holder containing schema required for this function
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel buildRoad(BuildRoadParams params) {
		 //TODO buildRoad method
		return null;
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
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel buildCity(BuildCityParams params) {
		 //TODO buildCity method
		 return null;
	 }
	 
	 /**
	 * @param params Data holder containing schema required for this function
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel offerTrade(OfferTradeParams params) {
		 //TODO offerTrade method
		 return null;
	 }
	 
	 /**
	 * @param params Data holder containing schema required for this function
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel acceptTrade(AcceptTradeParams params) {
		 //TODO acceptTrade method
		 return null;
	 }
	 
	 /**
	 * @param params Data holder containing schema required for this function
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel maritimeTrade(MaritimeTradeParams params) {
		 //TODO maritimeTrade method
		 return null;
	 }
	 
	 /**
	 * @param params Data holder containing schema required for this function
	 * @return CatanModel object containing the updated Game model
	 */
	public CatanModel discardCards(DiscardCardsParams params) {
		 //TODO discardCards method
		 return null;
	 }

}
