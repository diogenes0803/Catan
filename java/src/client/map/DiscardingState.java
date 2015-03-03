package client.map;

import client.communication.ServerProxy;
import shared.communicator.*;
import shared.models.CatanModel;

/**
 * @author campbeln
 */
public class DiscardingState implements IState {

    public static final DiscardingState singleton = new DiscardingState();

    private DiscardingState() {

    }

    /* (non-Javadoc)
     * @see client.state.IState#acceptTrade(client.state.MapController, shared.communicator.AcceptTradeParams)
     */
    @Override
    public void acceptTrade(MapController controller, AcceptTradeParams params) {
        return;
    }

    /* (non-Javadoc)
     * @see client.state.IState#discardCards(client.state.MapController, shared.communicator.DiscardCardsParams)
     */
    @Override
    public void discardCards(MapController controller, DiscardCardsParams params) {
        // TODO Auto-generated method stub

        CatanModel.setInstance(ServerProxy.getInstance().discardCards(params));
        controller.setState(RobbingState.singleton);
    }

    /* (non-Javadoc)
     * @see client.state.IState#rollNumber(client.state.MapController, shared.communicator.RollNumberParams)
     */
    @Override
    public void rollNumber(MapController controller, RollNumberParams params) {
        return;
    }

    /* (non-Javadoc)
     * @see client.state.IState#buildRoad(client.state.MapController, shared.communicator.BuildRoadParams)
     */
    @Override
    public void buildRoad(MapController controller, BuildRoadParams params) {
        return;
    }

    /* (non-Javadoc)
     * @see client.state.IState#buildSettlement(client.state.MapController, shared.communicator.BuildSettlementParams)
     */
    @Override
    public void buildSettlement(MapController controller,
                                BuildSettlementParams params) {
        return;
    }

    /* (non-Javadoc)
     * @see client.state.IState#buildCity(client.state.MapController, shared.communicator.BuildCityParams)
     */
    @Override
    public void buildCity(MapController controller, BuildCityParams params) {
        return;
    }

    /* (non-Javadoc)
     * @see client.state.IState#offerTrade(client.state.MapController, shared.communicator.OfferTradeParams)
     */
    @Override
    public void offerTrade(MapController controller, OfferTradeParams params) {
        return;
    }

    /* (non-Javadoc)
     * @see client.state.IState#maritimeTrade(client.state.MapController, shared.communicator.MaritimeTradeParams)
     */
    @Override
    public void maritimeTrade(MapController controller, MaritimeTradeParams params) {
        return;
    }

    /* (non-Javadoc)
     * @see client.state.IState#robPlayer(client.state.MapController, shared.communicator.RobPlayerParams)
     */
    @Override
    public void robPlayer(MapController controller, RobPlayerParams params) {
        return;
    }

    /* (non-Javadoc)
     * @see client.state.IState#finishTurn(client.state.MapController, shared.communicator.FinishTurnParams)
     */
    @Override
    public void finishTurn(MapController controller, FinishTurnParams params) {
        return;
    }

    /* (non-Javadoc)
     * @see client.state.IState#buyDevCard(client.state.MapController, shared.communicator.BuyDevCardParams)
     */
    @Override
    public void buyDevCard(MapController controller, BuyDevCardParams params) {
        return;
    }

    /* (non-Javadoc)
     * @see client.state.IState#playSoldier(client.state.MapController, shared.communicator.PlaySoldierParams)
     */
    @Override
    public void playSoldier(MapController controller, PlaySoldierParams params) {
        return;
    }

    /* (non-Javadoc)
     * @see client.state.IState#yearOfPlenty(client.state.MapController, shared.communicator.YearOfPlentyParams)
     */
    @Override
    public void yearOfPlenty(MapController controller, YearOfPlentyParams params) {
        return;
    }

    /* (non-Javadoc)
     * @see client.state.IState#roadBuilding(client.state.MapController, shared.communicator.RoadBuildingParams)
     */
    @Override
    public void roadBuilding(MapController controller, RoadBuildingParams params) {
        return;
    }

    /* (non-Javadoc)
     * @see client.state.IState#monopoly(client.state.MapController, shared.communicator.MonopolyParams)
     */
    @Override
    public void monopoly(MapController controller, MonopolyParams params) {
        return;
    }

    /* (non-Javadoc)
     * @see client.state.IState#monument(client.state.MapController, shared.communicator.MonumentParams)
     */
    @Override
    public void monument(MapController controller, MonumentParams params) {
        return;
    }

}
