package client.devcards;

import client.base.Controller;
import client.base.IAction;
import client.communication.ServerProxy;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.models.DevCard;
import shared.models.Game;
import shared.models.Player;

import java.util.Observable;


/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller implements IDevCardController {

    private IBuyDevCardView buyCardView;
    private IAction soldierAction;
    private IAction roadAction;

    /**
     * DevCardController constructor
     *
     * @param view          "Play dev card" view
     * @param buyCardView   "Buy dev card" view
     * @param soldierAction Action to be executed when the user plays a soldier card.  It calls
     *                      "mapController.playSoldierCard()".
     * @param roadAction    Action to be executed when the user plays a road building card.  It calls
     *                      "mapController.playRoadBuildingCard()".
     */
    public DevCardController(IPlayDevCardView view, IBuyDevCardView buyCardView,
                             IAction soldierAction, IAction roadAction) {

        super(view);

        this.buyCardView = buyCardView;
        this.soldierAction = soldierAction;
        this.roadAction = roadAction;
    }

    public IPlayDevCardView getPlayCardView() {
        return (IPlayDevCardView) super.getView();
    }

    public IBuyDevCardView getBuyCardView() {
        return buyCardView;
    }

    @Override
    public void startBuyCard() {

        getBuyCardView().showModal();
    }

    @Override
    public void cancelBuyCard() {

        getBuyCardView().closeModal();
    }

    @Override
    public void buyCard() {

        getBuyCardView().closeModal();
    }

    @Override
    public void startPlayCard() {

        getPlayCardView().showModal();
    }

    @Override
    public void cancelPlayCard() {

        getPlayCardView().closeModal();
    }

    @Override
    public void playMonopolyCard(ResourceType resource) {

    }

    @Override
    public void playMonumentCard() {

    }

    @Override
    public void playRoadBuildCard() {

        roadAction.execute();
    }

    @Override
    public void playSoldierCard() {

        soldierAction.execute();
    }

    @Override
    public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {

    }

    @Override
    public void update(Observable o, Object arg) {
    	Game thisGame = (Game)arg;
    	getPlayCardView().setCardEnabled(DevCardType.MONOPOLY, thisGame.getPlayers()[ServerProxy.getInstance()
    	                                 .getlocalPlayer().getPlayerIndex()].canPlayerDevCardType(DevCardType.MONOPOLY));
    	getPlayCardView().setCardEnabled(DevCardType.SOLDIER, thisGame.getPlayers()[ServerProxy.getInstance()
    	                                 .getlocalPlayer().getPlayerIndex()].canPlayerDevCardType(DevCardType.SOLDIER));
    	getPlayCardView().setCardEnabled(DevCardType.YEAR_OF_PLENTY, thisGame.getPlayers()[ServerProxy.getInstance()
                                         .getlocalPlayer().getPlayerIndex()].canPlayerDevCardType(DevCardType.YEAR_OF_PLENTY));
    	getPlayCardView().setCardEnabled(DevCardType.ROAD_BUILD, thisGame.getPlayers()[ServerProxy.getInstance()
    	                                 .getlocalPlayer().getPlayerIndex()].canPlayerDevCardType(DevCardType.ROAD_BUILD));
    	getPlayCardView().setCardEnabled(DevCardType.MONUMENT, thisGame.getPlayers()[ServerProxy.getInstance()
    	                                 .getlocalPlayer().getPlayerIndex()].canPlayerDevCardType(DevCardType.MONUMENT));
    	int monoNum  = 0;
    	int soldNum  = 0;
    	int yearNum  = 0;
    	int roadNum  = 0;
    	int monuNum  = 0;
    	
    	for(DevCard  devCard: thisGame.getPlayers()[ServerProxy.getInstance().getlocalPlayer().getPlayerIndex()].getDevCards())
    	{
    		switch (devCard.getType()) {
			case MONOPOLY:
				monoNum++;
				break;

			case SOLDIER:
				soldNum++;
				break;
			case YEAR_OF_PLENTY:
				yearNum++;
				break;
			case ROAD_BUILD:
				yearNum++;
				break;
			case MONUMENT:
				roadNum++;
				break;

			default:
				break;
			}
    	}

    	getPlayCardView().setCardAmount(DevCardType.MONOPOLY, monoNum);
    	getPlayCardView().setCardAmount(DevCardType.SOLDIER, soldNum);
    	getPlayCardView().setCardAmount(DevCardType.YEAR_OF_PLENTY, yearNum);
    	getPlayCardView().setCardAmount(DevCardType.ROAD_BUILD, roadNum);
    	getPlayCardView().setCardAmount(DevCardType.MONUMENT, monuNum);
    	
    	/**
    	 * I don't know what to do with the reset function
    	 */

    }

}

