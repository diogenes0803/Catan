/**
 * 
 */
package client.state;

import shared.communicator.*;

/**
 * @author campbeln
 *
 */
public interface IState {
	
	void acceptTrade(StateManager manager, AcceptTradeParams params);
	
	void discardCards(StateManager manager, DiscardCardsParams params);
	
	void rollNumber(StateManager manager, RollNumberParams params);
	
	void buildRoad(StateManager manager, BuildRoadParams params);
	
	void buildSettlement(StateManager manager, BuildSettlementParams params);
	
	void buildCity(StateManager manager, BuildCityParams params);
	
	void offerTrade(StateManager manager, OfferTradeParams params);
	
	void maritimeTrade(StateManager manager, MaritimeTradeParams params);
	
	void robPlayer(StateManager manager, RobPlayerParams params);
	
	void finishTurn(StateManager manager, FinishTurnParams params);
	
	void buyDevCard(StateManager manager, BuyDevCardParams params);
	
	void playSoldier(StateManager manager, PlaySoldierParams params);
	
	void yearOfPlenty(StateManager manager, YearOfPlentyParams params);
	
	void roadBuilding(StateManager manager, RoadBuildingParams params);
	
	void monopoly(StateManager manager, MonopolyParams params);
	
	void monument(StateManager manager, MonumentParams params);
}
