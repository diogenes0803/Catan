package client.discard;

import client.base.Controller;
import client.misc.IWaitView;
import shared.definitions.ResourceType;
import shared.model.*;

import java.util.List;
import java.util.Observable;
import java.util.logging.Logger;



public class DiscardController extends Controller implements IDiscardController {

	private IWaitView waitView;
    private final static Logger logger = Logger.getLogger("catan");
    private IResourceBank discardRb;
    private IResourceBank playerBank;
    private int half;


	public DiscardController(IDiscardView view, IWaitView waitView) {

		super(view);

        GameModelFacade.instance().getGame().addObserver(this);

		this.waitView = waitView;
	}

	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}
	
	public IWaitView getWaitView() {
		return waitView;
	}

	@Override
	public void increaseAmount(ResourceType resource) {
        // positive values increase discard numbers
		updateCount(1, resource);
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
        // negative values decrease discard numbers
        updateCount(-1, resource);
	}

	@Override
	public void discard() {
        // discard the resources from the player's bank
        try {
            ServerModelFacade.getInstance().discardCards(discardRb);
        } catch (ModelException e) {
            logger.fine(e.getMessage() +
                    " : Error in DiscardController.discard()" +
                    " method. Server Network Error?");
        }

        // when the local player finishes discarding, bring up the appropriate modal
        if(everyoneHasDiscarded()) {
            getDiscardView().closeThisModal();
            getWaitView().closeThisModal();
            resetModal();
        }
        else{
            getDiscardView().closeThisModal();
            resetModal();
        }
	}

    @Override
    public void update(Observable o, Object arg) {
        initFromModel();
    }

    private void initFromModel() {
        // check that the state is set to discarding
        if(GameModelFacade.instance().getGame().getGameState() != GameState.Discarding) {
            // close all discarding modals
            if(getWaitView().isModalShowing()) getWaitView().closeThisModal();
            if(getDiscardView().isModalShowing()) getDiscardView().closeThisModal();
        }
        else {
            if(GameModelFacade.instance().localPlayerIsDiscarding()) {
                if (!getDiscardView().isModalShowing()) {
                    resetModal();
                    getDiscardView().showModal();
                }

                // reset the modal to its default setup
            }
            else {
                // check if everyone has finished discarding
                if(everyoneHasDiscarded()) {
                    // close all discarding modals
                    if(getWaitView().isModalShowing()) getWaitView().closeThisModal();
                    if(getDiscardView().isModalShowing()) getDiscardView().closeThisModal();
                    return;
                }

                // if there are players still discarding
                if(!getWaitView().isModalShowing()){
                    getWaitView().showModal();
                }
            }
        }
    }



    //
    // Private Helper Methods //
    //

    // positive values increase discard amount
    // negative values decrease discard amount
    private void updateCount(int incDec, ResourceType r) {
        if(incDec < 0) {
            discardRb.decrement(r);
        }
        else if (incDec > 0) {
            discardRb.increment(r);
        }

        getDiscardView().setResourceDiscardAmount(r, discardRb.getCount(r));
        enableButtons();
    }

    private void enableIncDecButtons(ResourceType r) {
        if(discardRb.getCount(r) == 0 && playerBank.getCount(r) == 0)
            getDiscardView().setResourceAmountChangeEnabled(r, false, false);
        else if(discardRb.getCount(r) <= 0)
            getDiscardView().setResourceAmountChangeEnabled(r, true, false);
        else if (discardRb.getCount(r) >= playerBank.getCount(r))
            getDiscardView().setResourceAmountChangeEnabled(r, false, true);
        else
            getDiscardView().setResourceAmountChangeEnabled(r, true, true);
    }

    private void enableButtons() {
        // if the number of resources being discarded equal half of the player resources
        if(discardRb.getCount() == half) {
            getDiscardView().setDiscardButtonEnabled(true);

            // make sure that the inc buttons are disabled when the discard count equals half
            for(ResourceType r : ResourceType.values()) {
                getDiscardView().setResourceAmountChangeEnabled(r, false, true);
                if(discardRb.getCount(r) <= 0)
                    getDiscardView().setResourceAmountChangeEnabled(r, false, false);
            }
        }
        // discard count is less than half
        else {
            getDiscardView().setDiscardButtonEnabled(false);
            // make sure all the inc and dec buttons are properly enabled
            for(ResourceType r : ResourceType.values()) {
                enableIncDecButtons(r);
            }
        }

        getDiscardView().setStateMessage(generateStateMessage());
    }

    private boolean everyoneHasDiscarded() {
        List<IPlayer> players = GameModelFacade.instance().getGame().getPlayers();
        int localIndex = GameModelFacade.instance().getLocalPlayer().getIndex();
        for(IPlayer p : players) {
            if(!p.hasDiscarded() && p.getIndex() != localIndex) {
                return false;
            }
        }
        return true;
    }

    private void resetModal() {
        discardRb = new ResourceBank();
        playerBank = GameModelFacade.instance().getLocalPlayerResources();
        half = (int)((float)(playerBank.getCount() / 2.0));
        getDiscardView().setStateMessage(generateStateMessage());

        getDiscardView().setDiscardButtonEnabled(false);
        for(ResourceType r : ResourceType.values()) {
            getDiscardView().setResourceMaxAmount(r, playerBank.getCount(r));
            getDiscardView().setResourceDiscardAmount(r, 0);
            getDiscardView().setResourceAmountChangeEnabled(r, playerBank.getCount(r) > 0, false);
        }
    }

    private String generateStateMessage() {
        return "Discard: " + discardRb.getCount() + "/" + half;
    }
}

