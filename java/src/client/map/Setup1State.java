package client.map;

import client.communication.ServerProxy;
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
import shared.definitions.CatanColor;
import shared.definitions.PieceType;
import shared.models.CatanModel;
import shared.models.Game;

/**
 * @author campbeln
 *
 */
public class Setup1State implements IState {

	public static final Setup1State	singleton = new Setup1State();
	
	private Setup1State()
	{
		
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
		return;
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
		Game game = ServerProxy.getInstance().buildRoad(params).getGameManager().getGame();
		CatanModel.getInstance().getGameManager().setGame(game);
		int playerId = ServerProxy.getInstance().getlocalPlayer().getId();
		CatanColor thisColor = game.getPlayers()[game.getPlayerIndexByPlayerId(playerId)].getColor();
		
		controller.getView().startDrop(PieceType.SETTLEMENT, thisColor, false);
	}

	/* (non-Javadoc)
	 * @see client.state.IState#buildSettlement(client.state.MapController, shared.communicator.BuildSettlementParams)
	 */
	@Override
	public void buildSettlement(MapController controller,
			BuildSettlementParams params) {
		Game game = ServerProxy.getInstance().buildSettlement(params).getGameManager().getGame();
		CatanModel.getInstance().getGameManager().setGame(game);
		
		FinishTurnParams params2 = new FinishTurnParams(params.getPlayerIndex());
		finishTurn(controller, params2);
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
		MapController.setState(Setup2State.singleton);
		controller.setSetup1Finished(true);
		Game game = ServerProxy.getInstance().finishTurn(params).getGameManager().getGame();
		CatanModel.getInstance().getGameManager().setGame(game);
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

	@Override
	public String toString() {
		return "FirstRound";
	}
}
