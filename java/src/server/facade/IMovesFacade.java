package server.facade;

import server.command.IllegalCommandException;
import shared.communication.*;
import shared.model.IGame;
import shared.model.ModelException;


public interface IMovesFacade {


    public IGame sendChat(SendChatParams sendChat) throws IllegalCommandException, ModelException;


    public IGame rollNumber(RollNumberParams rollNumber) throws IllegalCommandException, ModelException;


    public IGame robPlayer(RobbingParams robbing) throws IllegalCommandException, ModelException;


    public IGame finishTurn(PlayerIndexParam playerIndex) throws IllegalCommandException, ModelException;


    public IGame buyDevCard(PlayerIndexParam playerIndex) throws IllegalCommandException, ModelException;


    public IGame yearOfPlenty(YearOfPlentyParams yearOfPlenty) throws IllegalCommandException, ModelException;


    public IGame roadBuilding(RoadBuildingParams roadBuilding) throws IllegalCommandException, ModelException;


    public IGame soldier(RobbingParams robbing) throws IllegalCommandException, ModelException;


    public IGame monopoly(MonopolyParams monopoly) throws IllegalCommandException, ModelException;


    public IGame monument(PlayerIndexParam playerIndex) throws IllegalCommandException, ModelException;


    public IGame buildRoad(BuildRoadParams buildRoad) throws IllegalCommandException, ModelException;


    public IGame buildSettlement(BuildSettlementParams buildSettlement) throws IllegalCommandException, ModelException;


    public IGame buildCity(BuildCityParams buildCity) throws IllegalCommandException, ModelException;


    public IGame offerTrade(OfferTradeParams offerTrade) throws IllegalCommandException, ModelException;


    public IGame acceptTrade(AcceptTradeParams acceptTrade) throws IllegalCommandException, ModelException;


    public IGame maritimeTrade(MaritimeTradeParams maritimeTrade) throws IllegalCommandException, ModelException;


    public IGame discardCards(DiscardCardParams discardCards) throws IllegalCommandException, ModelException;
}
