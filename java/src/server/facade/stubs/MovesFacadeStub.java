package server.facade.stubs;

import server.command.IllegalCommandException;
import server.facade.IMovesFacade;
import shared.communication.*;
import shared.model.IGame;
import shared.model.ModelException;


public class MovesFacadeStub implements IMovesFacade {

    @Override
    public IGame sendChat(SendChatParams sendChat) throws IllegalCommandException, ModelException {
        return null;
    }


    @Override
    public IGame rollNumber(RollNumberParams rollNumber) throws IllegalCommandException, ModelException {
        return null;
    }


    @Override
    public IGame robPlayer(RobbingParams robbing) throws IllegalCommandException, ModelException {
        return null;
    }


    @Override
    public IGame finishTurn(PlayerIndexParam playerIndex) throws IllegalCommandException, ModelException {
        return null;
    }


    @Override
    public IGame buyDevCard(PlayerIndexParam playerIndex) throws IllegalCommandException, ModelException {
        return null;
    }


    @Override
    public IGame yearOfPlenty(YearOfPlentyParams yearOfPlenty) throws IllegalCommandException, ModelException {
        return null;
    }


    @Override
    public IGame roadBuilding(RoadBuildingParams roadBuilding) throws IllegalCommandException, ModelException {
        return null;
    }


    @Override
    public IGame soldier(RobbingParams robbing) throws IllegalCommandException, ModelException {
        return null;
    }


    @Override
    public IGame monopoly(MonopolyParams monopoly) throws IllegalCommandException, ModelException {
        return null;
    }


    @Override
    public IGame monument(PlayerIndexParam playerIndex) throws IllegalCommandException, ModelException {
        return null;
    }


    @Override
    public IGame buildRoad(BuildRoadParams buildRoad) throws IllegalCommandException, ModelException {
        return null;
    }


    @Override
    public IGame buildSettlement(BuildSettlementParams buildSettlement) throws IllegalCommandException, ModelException {
        return null;
    }


    @Override
    public IGame buildCity(BuildCityParams buildCity) throws IllegalCommandException, ModelException {
        return null;
    }


    @Override
    public IGame offerTrade(OfferTradeParams offerTrade) throws IllegalCommandException, ModelException {
        return null;
    }


    @Override
    public IGame acceptTrade(AcceptTradeParams acceptTrade) throws IllegalCommandException, ModelException {
        return null;
    }


    @Override
    public IGame maritimeTrade(MaritimeTradeParams maritimeTrade) throws IllegalCommandException, ModelException {
        return null;
    }


    @Override
    public IGame discardCards(DiscardCardParams discardCards) throws IllegalCommandException, ModelException {
        return null;
    }
}
