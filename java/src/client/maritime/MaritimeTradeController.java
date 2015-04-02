package client.maritime;

import client.base.Controller;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.model.GameModelFacade;
import shared.model.IResourceBank;
import shared.model.ModelException;
import shared.model.ServerModelFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;



public class MaritimeTradeController extends Controller implements IMaritimeTradeController {

    private final static Logger logger = Logger.getLogger("catan");

	private IMaritimeTradeOverlay tradeOverlay;
    private ResourceType m_gettingResource;
    private ResourceType m_givingResource;
    private final int TYPE_THRESHOLD = 2;
    private final int GENERIC_THRESHOLD = 3;
    private final int STANDARD_THRESHOLD = 4;

	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay) {
		
		super(tradeView);

		setTradeOverlay(tradeOverlay);

        GameModelFacade.instance().getGame().addObserver(this);
	}
	
	public IMaritimeTradeView getTradeView() {
		
		return (IMaritimeTradeView)super.getView();
	}
	
	public IMaritimeTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IMaritimeTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	@Override
	public void startTrade() {
		getTradeOverlay().showModal();
	}

	@Override
	public void makeTrade() {
        int ratio = 4;
        if (hasPort(PortType.THREE))
            ratio = 3;
        if(hasPort(convertRTypeToPType(m_givingResource)))
            ratio = 2;

        try {
            ServerModelFacade.getInstance().maritimeTrade(ratio, m_givingResource, m_gettingResource);
        } catch (ModelException e) {
            logger.log(Level.WARNING, "ERROR: MaritimeTrade failed in the makeTrade() method.  Server Error?", e);
        }

        getTradeOverlay().closeTopModal();
        resetModal();
	}

	@Override
	public void cancelTrade() {
        initFromModel();
		getTradeOverlay().closeTopModal();
        resetModal();
	}

	@Override
	public void setGetResource(ResourceType resource) {
        m_gettingResource = resource;
        getTradeOverlay().selectGetOption(resource, 1);
        getTradeOverlay().setTradeEnabled(GameModelFacade.instance().getGame().getResourceBank().canAfford(1, resource));
	}

	@Override
	public void setGiveResource(ResourceType resource) {
        m_givingResource = resource;
        getTradeOverlay().selectGiveOption(resource, determineAmount(resource));
        getTradeOverlay().showGetOptions(ResourceType.values());
	}

	@Override
	public void unsetGetValue() {
        m_gettingResource = null;
        getTradeOverlay().showGetOptions(ResourceType.values());
        getTradeOverlay().setTradeEnabled(false);
	}

	@Override
	public void unsetGiveValue() {
        m_givingResource = null;
        getTradeOverlay().hideGetOptions();
        getTradeOverlay().showGiveOptions(canGiveOptions());
	}

    @Override
    public void update(Observable o, Object arg) {
        initFromModel();
    }

    private void initFromModel() {
        // buttons and choices are disabled until Maritime Trade is proven a valid action
        getTradeView().enableMaritimeTrade(false);

        // if the local player is not playing, return
        if(!GameModelFacade.instance().localPlayerIsPlaying()) {
            return;
        }

        // set up the modal based on the player's resources and ports
        if(!getTradeOverlay().isModalShowing()) {
            resetModal();
        }

        // if the if statements above are true, then re-enable the button
        getTradeView().enableMaritimeTrade(true);
    }



    //
    // Private Helper Methods //
    //
    private void setUpModal() {
        getTradeOverlay().setTradeEnabled(false);
        getTradeOverlay().showGiveOptions(canGiveOptions());
    }

    private ResourceType[] canGiveOptions() {
        List<ResourceType> rTypes = new ArrayList<>();
        for(ResourceType r : ResourceType.values()) {
            if(canGive(r)) {
                rTypes.add(r);
            }
        }
        return rTypes.toArray(new ResourceType[5]);
    }

    private boolean canGive(ResourceType r) {
        IResourceBank rb = GameModelFacade.instance().getLocalPlayerResources();

        if(hasPort(convertRTypeToPType(r))) {
            return (rb.getCount(r) >= TYPE_THRESHOLD);
        }
        else if(hasPort(PortType.THREE)) {
            return (rb.getCount(r) >= GENERIC_THRESHOLD);
        }
        else {
            return (rb.getCount(r) >= STANDARD_THRESHOLD);
        }
    }

    private boolean hasPort(PortType p) {
        Set<PortType> ports = GameModelFacade.instance().getLocalPlayerPorts();
        for(PortType pt : ports) {
            if(pt == p)
                return true;
        }
        return false;
    }

    private PortType convertRTypeToPType(ResourceType r) {
        switch (r) {
            case WOOD: return PortType.WOOD;
            case BRICK: return PortType.BRICK;
            case SHEEP: return PortType.SHEEP;
            case WHEAT: return PortType.WHEAT;
            case ORE: return PortType.ORE;
            default: return null;
        }
    }

    private int determineAmount(ResourceType r) {
        if(hasPort(convertRTypeToPType(r))) {
            return TYPE_THRESHOLD;
        }
        else if(hasPort(PortType.THREE)) {
            return GENERIC_THRESHOLD;
        }
        else {
            return STANDARD_THRESHOLD;
        }
    }

    private void resetModal() {
        unsetGetValue();
        unsetGiveValue();
        getTradeOverlay().showGiveOptions(ResourceType.values());
        getTradeOverlay().hideGetOptions();
        setUpModal();
    }
}

