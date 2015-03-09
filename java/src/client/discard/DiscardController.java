package client.discard;

import client.base.Controller;
import client.communication.ServerProxy;
import client.map.DiscardingState;
import client.map.MapController;
import client.map.RobbingState;
import client.misc.IWaitView;
import shared.definitions.ResourceType;
import shared.models.CatanModel;
import shared.models.Game;
import shared.models.Player;
import shared.models.ResourceList;

import java.util.Observable;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController {

    private IWaitView waitView;
    private int currentDiscardCount;
    private int woodDiscardCount;
    private int wheatDiscardCount;
    private int sheepDiscardCount;
    private int brickDiscardCount;
    private int oreDiscardCount;
    private int woodCount;
    private int wheatCount;
    private int sheepCount;
    private int brickCount;
    private int oreCount;
    private int cardCount;
    private int discardAmount;

    /**
     * DiscardController constructor
     *
     * @param view     View displayed to let the user select cards to discard
     * @param waitView View displayed to notify the user that they are waiting for other players to discard
     */
    public DiscardController(IDiscardView view, IWaitView waitView) {

        super(view);

        this.waitView = waitView;
        currentDiscardCount = 0;
        woodDiscardCount = 0;
        wheatDiscardCount = 0;
        sheepDiscardCount = 0;
        brickDiscardCount = 0;
        oreDiscardCount = 0;
    }

    public IDiscardView getDiscardView() {
        return (IDiscardView) super.getView();
    }

    public IWaitView getWaitView() {
        return waitView;
    }

    @Override
    public void increaseAmount(ResourceType resource) {

    	//Don't worry about logic where discard count = 0, increase function means min size is 1
    	switch(resource) {
		case BRICK: brickDiscardCount++;
		getDiscardView().setResourceDiscardAmount(ResourceType.BRICK, brickDiscardCount);
		if (brickDiscardCount == brickCount) {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, false, true);
		}
		else {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, true, true);
		}
			break;
		case ORE: oreDiscardCount++;
		getDiscardView().setResourceDiscardAmount(ResourceType.ORE, oreDiscardCount);
		if (oreDiscardCount == oreCount) {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, false, true);
		}
		else {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, true, true);
		}
			break;
		case SHEEP: sheepDiscardCount++;
		getDiscardView().setResourceDiscardAmount(ResourceType.SHEEP, sheepDiscardCount);
		if (sheepDiscardCount == sheepCount) {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, false, true);
		}
		else {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, true, true);
		}
			break;
		case WHEAT: wheatDiscardCount++;
		getDiscardView().setResourceDiscardAmount(ResourceType.WHEAT, wheatDiscardCount);
		if (wheatDiscardCount == wheatCount) {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, false, true);
		}
		else {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, true, true);
		}
			break;
		case WOOD: woodDiscardCount++;
		getDiscardView().setResourceDiscardAmount(ResourceType.WOOD, woodDiscardCount);
		if (woodDiscardCount == woodCount) {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, false, true);
		}
		else {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, true, true);
		}
			break;
		default:
			break;
    		
    	}
    	
    	currentDiscardCount++;
    	getDiscardView().setStateMessage(currentDiscardCount + "/" + discardAmount);
    	
    	if (currentDiscardCount == discardAmount) {
    		disableIncreaseAll();
    		getDiscardView().setDiscardButtonEnabled(true);
    	}
    }

    @Override
    public void decreaseAmount(ResourceType resource) {

    	switch(resource) {
		case BRICK: brickDiscardCount--;
		getDiscardView().setResourceDiscardAmount(ResourceType.BRICK, brickDiscardCount);
		if (brickDiscardCount == 0) {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, true, false);
		}
		else {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, true, true);
		}
			break;
		case ORE: oreDiscardCount--;
		getDiscardView().setResourceDiscardAmount(ResourceType.ORE, oreDiscardCount);
		if (oreDiscardCount == 0) {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, true, false);
		}
		else {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, true, true);
		}
			break;
		case SHEEP: sheepDiscardCount--;
		getDiscardView().setResourceDiscardAmount(ResourceType.SHEEP, sheepDiscardCount);
		if (sheepDiscardCount == 0) {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, true, false);
		}
		else {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, true, true);
		}
			break;
		case WHEAT: wheatDiscardCount--;
		getDiscardView().setResourceDiscardAmount(ResourceType.WHEAT, wheatDiscardCount);
		if (wheatDiscardCount == 0) {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, true, false);
		}
		else {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, true, true);
		}
			break;
		case WOOD: woodDiscardCount--;
		getDiscardView().setResourceDiscardAmount(ResourceType.WOOD, woodDiscardCount);
		if (woodDiscardCount == 0) {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, true, false);
		}
		else {
			getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, true, true);
		}
			break;
		default:
			break;
    		
    	}
    	
    	if (currentDiscardCount == discardAmount) {
    		enableIncreaseAll();
    	}
    	currentDiscardCount--;
    	getDiscardView().setStateMessage(currentDiscardCount + "/" + discardAmount);
    	getDiscardView().setDiscardButtonEnabled(false);
    }

    @Override
    public void discard() {
    	int playerIndex = ServerProxy.getInstance().getlocalPlayer().getPlayerIndex();
    	ResourceList resourceList = new ResourceList(brickDiscardCount, oreDiscardCount, sheepDiscardCount, wheatDiscardCount, woodDiscardCount);
    	MapController.discardCards(playerIndex, resourceList);
    	
        getDiscardView().closeModal();
    }

    @Override
    public void update(Observable o, Object arg) {
    	if(arg instanceof Game) {
	        int playerIndex = ServerProxy.getInstance().getlocalPlayer().getPlayerIndex();
	        Player[] players = CatanModel.getInstance().getGameManager().getGame().getPlayers();
	        cardCount = players[playerIndex].getResCards().size();
	        
	        if (MapController.state == DiscardingState.singleton && cardCount > 7) {
	        	
	        	oreCount = players[playerIndex].getResCount(ResourceType.ORE);
	        	woodCount = players[playerIndex].getResCount(ResourceType.WOOD);
	        	brickCount = players[playerIndex].getResCount(ResourceType.BRICK);
	        	sheepCount = players[playerIndex].getResCount(ResourceType.SHEEP);
	        	wheatCount = players[playerIndex].getResCount(ResourceType.WHEAT);
	        	
	        	discardAmount = cardCount / 2;
	        	getDiscardView().setStateMessage(currentDiscardCount + "/" + discardAmount);
	        	getDiscardView().setResourceMaxAmount(ResourceType.ORE, oreCount);
	        	getDiscardView().setResourceMaxAmount(ResourceType.WOOD, woodCount);
	        	getDiscardView().setResourceMaxAmount(ResourceType.BRICK, brickCount);
	        	getDiscardView().setResourceMaxAmount(ResourceType.SHEEP, sheepCount);
	        	getDiscardView().setResourceMaxAmount(ResourceType.WHEAT, wheatCount);
	        	
	        	if (oreCount > 0) {
	        		getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, true, false);
	        	}
	        	else {
	        		getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, false, false);
	        	}
	        	if (woodCount > 0) {
	        		getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, true, false);
	        	}
	        	else {
	        		getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, false, false);
	        	}
	        	if (brickCount > 0) {
	        		getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, true, false);
	        	}
	        	else {
	        		getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, false, false);
	        	}
	        	if (sheepCount > 0) {
	        		getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, true, false);
	        	}
	        	else {
	        		getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, false, false);
	        	}
	        	if (wheatCount > 0) {
	        		getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, true, false);
	        	}
	        	else {
	        		getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, false, false);
	        	}
	        	
	        	getDiscardView().setDiscardButtonEnabled(false);
	        	getDiscardView().showModal();
	        }
	        else {
	        	MapController.state = RobbingState.singleton;
	        }
    	}

    }
    
    private void disableIncreaseAll() {
    	// Reason for if statements: If the resource count was 0, increase is already disabled, don't want to enable decrease
    	if (oreDiscardCount > 0) {
    		getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, false, true);
    	}
    	else {
    		getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, false, false);
    	}
    	if (woodDiscardCount > 0) {
    		getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, false, true);
    	}
    	else {
    		getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, false, false);
    	}
    	if (brickDiscardCount > 0) {
    		getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, false, true);
    	}
    	else {
    		getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, false, false);
    	}
    	if (sheepDiscardCount > 0) {
    		getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, false, true);
    	}
    	else {
    		getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, false, false);
    	}
    	if (wheatDiscardCount > 0) {
    		getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, false, true);
    	}
    	else {
    		getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, false, false);
    	}
    }
    
    private void enableIncreaseAll() {
    	if (oreCount > 0 && oreDiscardCount < oreCount) {
    		if (oreDiscardCount > 0) {
    			getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, true, true);
    		}
    		else {
    			getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, true, false);
    		}
    	}
    	if (woodCount > 0 && woodDiscardCount < woodCount) {
    		if (woodDiscardCount > 0) {
    			getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, true, true);
    		}
    		else {
    			getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, true, false);
    		}
    	}
    	if (brickCount > 0 && brickDiscardCount < brickCount) {
    		if (brickDiscardCount > 0) {
    			getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, true, true);
    		}
    		else {
    			getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, true, false);
    		}
    	}
    	if (sheepCount > 0 && sheepDiscardCount < sheepCount) {
    		if (sheepDiscardCount > 0) {
    			getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, true, true);
    		}
    		else {
    			getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, true, false);
    		}
    	}
    	if (wheatCount > 0 && wheatDiscardCount < wheatCount) {
    		if (wheatDiscardCount > 0) {
    			getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, true, true);
    		}
    		else {
    			getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, true, false);
    		}
    	}
    }

}

