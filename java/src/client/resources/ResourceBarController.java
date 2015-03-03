package client.resources;

import client.base.Controller;
import client.base.IAction;
import client.communication.ServerProxy;
import shared.definitions.PieceType;
import shared.definitions.ResourceType;
import shared.models.Game;
import shared.models.Piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;


/**
 * Implementation for the resource bar controller
 */
public class ResourceBarController extends Controller implements IResourceBarController {

    private Map<ResourceBarElement, IAction> elementActions;

    public ResourceBarController(IResourceBarView view) {

        super(view);

        elementActions = new HashMap<ResourceBarElement, IAction>();
    }

    @Override
    public IResourceBarView getView() {
        return (IResourceBarView) super.getView();
    }

    /**
     * Sets the action to be executed when the specified resource bar element is clicked by the user
     *
     * @param element The resource bar element with which the action is associated
     * @param action  The action to be executed
     */
    public void setElementAction(ResourceBarElement element, IAction action) {

        elementActions.put(element, action);
    }

    @Override
    public void buildRoad() {
        executeElementAction(ResourceBarElement.ROAD);
    }

    @Override
    public void buildSettlement() {
        executeElementAction(ResourceBarElement.SETTLEMENT);
    }

    @Override
    public void buildCity() {
        executeElementAction(ResourceBarElement.CITY);
    }

    @Override
    public void buyCard() {
        executeElementAction(ResourceBarElement.BUY_CARD);
    }

    @Override
    public void playCard() {
        executeElementAction(ResourceBarElement.PLAY_CARD);
    }

    private void executeElementAction(ResourceBarElement element) {

        if (elementActions.containsKey(element)) {

            IAction action = elementActions.get(element);
            action.execute();
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        Game game = (Game) arg;
//Resource
        getView().setElementAmount(ResourceBarElement.WOOD, game.getPlayers()[ServerProxy.getInstance().getlocalPlayer().getId()].getResCount(ResourceType.WOOD));
        getView().setElementAmount(ResourceBarElement.BRICK, game.getPlayers()[ServerProxy.getInstance().getlocalPlayer().getId()].getResCount(ResourceType.BRICK));
        getView().setElementAmount(ResourceBarElement.SHEEP, game.getPlayers()[ServerProxy.getInstance().getlocalPlayer().getId()].getResCount(ResourceType.SHEEP));
        getView().setElementAmount(ResourceBarElement.WHEAT, game.getPlayers()[ServerProxy.getInstance().getlocalPlayer().getId()].getResCount(ResourceType.WHEAT));
        getView().setElementAmount(ResourceBarElement.ORE, game.getPlayers()[ServerProxy.getInstance().getlocalPlayer().getId()].getResCount(ResourceType.ORE));
//Contructions
        int cityNum = 0;
        int settleNum = 0;
        int roadNum = 0;
        for (Piece piece : game.getPlayers()[ServerProxy.getInstance().getlocalPlayer().getId()].getAvailablePieces()) {
            if (piece.getType() == PieceType.CITY) {
                cityNum++;
            } else if (piece.getType() == PieceType.SETTLEMENT) {
                settleNum++;
            } else if (piece.getType() == PieceType.ROAD) {
                roadNum++;
            } else if (piece.getType() == PieceType.ROBBER) {
                roadNum++;
            }
        }

        getView().setElementAmount(ResourceBarElement.ROAD, roadNum);
        getView().setElementAmount(ResourceBarElement.SETTLEMENT, settleNum);
        getView().setElementAmount(ResourceBarElement.CITY, cityNum);
//Cards

        getView().setElementEnabled(ResourceBarElement.BUY_CARD, game.getPlayers()[ServerProxy.getInstance().getlocalPlayer().getId()].canBuyDevCard());
        getView().setElementAmount(ResourceBarElement.SOLDIERS, game.getPlayers()[ServerProxy.getInstance().getlocalPlayer().getId()].getNumMonumentPlayed());

        /**
         * For PLAY_CARD I don't know what to do, it seem
         */

        //WOOD, BRICK, SHEEP, WHEAT, ORE, ROAD, SETTLEMENT, CITY, BUY_CARD, PLAY_CARD, SOLDIERS
    }

}

