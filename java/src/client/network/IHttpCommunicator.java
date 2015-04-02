package client.network;


public interface IHttpCommunicator {


    public String get(String url) throws NetworkException;


    public String post(String url, String data) throws NetworkException;


    public String getPlayerName();


    public int getPlayerId();


    public int getGameIdCookie();

    public void clearGameCookie();


}

