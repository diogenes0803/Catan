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
