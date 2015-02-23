/**
 * 
 */
package client.map;

//import client.state.MapController;
import shared.communicator.*;

/**
 * @author campbeln
 *
 */

//Move this and all other classes to Client.Map (see MapController for explicit action)
public interface IState {
	
	void acceptTrade(MapController manager, AcceptTradeParams params);
	
	void discardCards(MapController manager, DiscardCardsParams params);
	
	void rollNumber(MapController manager, RollNumberParams params);
	
	void buildRoad(MapController manager, BuildRoadParams params);
	
	void buildSettlement(MapController manager, BuildSettlementParams params);
	
	void buildCity(MapController manager, BuildCityParams params);
	
	void offerTrade(MapController manager, OfferTradeParams params);
	
	void maritimeTrade(MapController manager, MaritimeTradeParams params);
	
	void robPlayer(MapController manager, RobPlayerParams params);
	
	void finishTurn(MapController manager, FinishTurnParams params);
	
	void buyDevCard(MapController manager, BuyDevCardParams params);
	
	void playSoldier(MapController manager, PlaySoldierParams params);
	
	void yearOfPlenty(MapController manager, YearOfPlentyParams params);
	
	void roadBuilding(MapController manager, RoadBuildingParams params);
	
	void monopoly(MapController manager, MonopolyParams params);
	
	void monument(MapController manager, MonumentParams params);
}
