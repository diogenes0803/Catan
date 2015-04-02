package client.network;

import client.data.GameInfo;
import shared.definitions.CatanColor;

import java.io.IOException;
import java.util.List;


public interface IGameAdministrator {
    public void setGameAdminServerProxy(IGameAdminServerProxy gameAdminServerProxy);


    public boolean login(String username, String password) throws NetworkException;


    public boolean register(String username, String password) throws NetworkException;


    public List<GameInfo> listGames() throws NetworkException, IOException;


    public boolean joinGame(int gameIndex, CatanColor playerColor) throws NetworkException;


    public GameInfo createGame(boolean randomTiles, boolean randomNumbers, boolean randomPorts, String gameName) throws NetworkException, IOException;


    public String[] listAI() throws NetworkException;


    public boolean addAI(String nameOfAI) throws NetworkException;


    public String getLocalPlayerName();


    public int getLocalPlayerId();

}
