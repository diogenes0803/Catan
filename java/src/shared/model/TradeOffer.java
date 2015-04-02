package shared.model;


public class TradeOffer implements ITradeOffer{

    private IPlayer m_sender;
    private IPlayer m_receiver;
    private IResourceBank m_offer;

    public TradeOffer(IPlayer sender, IPlayer receiver, IResourceBank offer) {
        assert sender != null && receiver != null && offer != null;
        setSender(sender);
        setReceiver(receiver);
        setOffer(offer);
    }

    @Override
    public IPlayer getSender() {
        return m_sender;
    }

    @Override
    public void setSender(IPlayer sender) {
        assert sender != null;
        m_sender = sender;
    }

    @Override
    public IPlayer getReceiver() {
        return m_receiver;
    }

    @Override
    public void setReceiver(IPlayer receiver) {
        assert receiver != null;
        m_receiver = receiver;
    }

    @Override
    public IResourceBank getOffer() {
        return m_offer;
    }

    @Override
    public void setOffer(IResourceBank offer) {
        assert offer != null;
        m_offer = offer;
    }
}
