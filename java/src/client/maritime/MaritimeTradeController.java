package client.maritime;

import client.base.Controller;
import shared.definitions.ResourceType;

import java.util.Observable;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController {

    private IMaritimeTradeOverlay tradeOverlay;

    public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay) {

        super(tradeView);

        setTradeOverlay(tradeOverlay);
    }

    public IMaritimeTradeView getTradeView() {

        return (IMaritimeTradeView) super.getView();
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

        getTradeOverlay().closeModal();
    }

    @Override
    public void cancelTrade() {

        getTradeOverlay().closeModal();
    }

    @Override
    public void setGetResource(ResourceType resource) {

    }

    @Override
    public void setGiveResource(ResourceType resource) {

    }

    @Override
    public void unsetGetValue() {

    }

    @Override
    public void unsetGiveValue() {

    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub

    }

}

