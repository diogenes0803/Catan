package client.network;

import shared.definitions.CatanColor;

import java.util.List;


public interface IGameAdminServerProxy {

    public boolean login(String username, String password) throws NetworkException;


    public boolean register(String username, String password) throws NetworkException;


    public String listGames() throws NetworkException;


    public String createGame(boolean randTiles, boolean randNum, boolean randPorts, String name) throws NetworkException;


    public boolean joinGame(int gameId, CatanColor playerColor) throws NetworkException;


    public String listAI() throws NetworkException;


    public String addAI(String name) throws NetworkException;


    public String getLocalPlayerName();


    public int getLocalPlayerId();
}
