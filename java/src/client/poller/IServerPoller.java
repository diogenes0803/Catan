package client.poller;

import client.network.IServerProxy;


public interface IServerPoller {

    public void updateGame();


    public void setProxy(IServerProxy serverProxy);


    public void startPolling();


    public void stopPolling();


    public int getPollCount();

}
