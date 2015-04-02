package shared.model;

import java.io.Serializable;


public interface ITradeOffer extends Serializable {

    public IPlayer getSender();


    public void setSender(IPlayer sender);


    public IPlayer getReceiver();


    public void setReceiver(IPlayer receiver);


    public IResourceBank getOffer();


    public void setOffer(IResourceBank offer);
}
