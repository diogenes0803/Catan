package client.poller;

import client.network.IServerProxy;
import shared.model.ModelException;
import client.network.NetworkException;


public interface IServerPoller {

    public void updateGame();


    public void setProxy(IServerProxy serverProxy);


    public void startPolling();


    public void stopPolling();


    public int getPollCount();

}
