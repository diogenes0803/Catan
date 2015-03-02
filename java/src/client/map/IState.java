<<<<<<< HEAD
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
=======
package client.map;

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
import shared.communicator.YearOfPlentyParams;

/**
 * @author campbeln
 */
public interface IState {
	
	void acceptTrade(MapController controller, AcceptTradeParams params);
	
	void discardCards(MapController controller, DiscardCardsParams params);
	
	void rollNumber(MapController controller, RollNumberParams params);
	
	void buildRoad(MapController controller, BuildRoadParams params);
	
	void buildSettlement(MapController controller, BuildSettlementParams params);
	
	void buildCity(MapController controller, BuildCityParams params);
	
	void offerTrade(MapController controller, OfferTradeParams params);
	
	void maritimeTrade(MapController controller, MaritimeTradeParams params);
	
	void robPlayer(MapController controller, RobPlayerParams params);
	
	void finishTurn(MapController controller, FinishTurnParams params);
	
	void buyDevCard(MapController controller, BuyDevCardParams params);
	
	void playSoldier(MapController controller, PlaySoldierParams params);
	
	void yearOfPlenty(MapController controller, YearOfPlentyParams params);
	
	void roadBuilding(MapController controller, RoadBuildingParams params);
	
	void monopoly(MapController controller, MonopolyParams params);
	
	void monument(MapController controller, MonumentParams params);
}
>>>>>>> master
