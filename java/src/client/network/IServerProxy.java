package client.network;

import shared.locations.VertexLocation;
import shared.definitions.*;
import shared.locations.*;
import shared.model.*;


public interface IServerProxy {

    public String getGameState() throws NetworkException;


    public String resetGame() throws NetworkException;


    public boolean changeLogLevel(String logLevel) throws NetworkException;


    public String sendChat(int playerIndex, String message) throws NetworkException;


    public String acceptTrade(int playerIndex, boolean willAccept) throws NetworkException;


    public String discardCards(int playerIndex, IResourceBank discardedCards) throws NetworkException;


    public String rollNumber(int playerIndex, int number) throws NetworkException;


    public String buildRoad(int playerIndex, EdgeLocation edgeLoc, boolean free) throws NetworkException;


    public String buildSettlement(int playerIndex, VertexLocation location, boolean free) throws NetworkException;


    public String buildCity(int playerIndex, VertexLocation location) throws NetworkException;


    public String offerTrade(int playerIndex, IResourceBank offer, int receiver) throws NetworkException;


    public String maritimeTrade(int playerIndex, int ratio, ResourceType input, ResourceType output) throws NetworkException;


    public String finishTurn(int playerIndex) throws NetworkException;


    public String buyDevCard(int playerIndex) throws NetworkException;


    public String playYearOfPlenty(int playerIndex, ResourceType resource1, ResourceType resource2) throws NetworkException;


    public String playRoadBuilding(int playerIndex, EdgeLocation location1, EdgeLocation location2) throws NetworkException;


    public String playSoldier(int playerIndex, HexLocation location, int victim) throws NetworkException;


    public String playMonopoly(int playerIndex, ResourceType resource) throws NetworkException;


    public String playMonument(int playerIndex) throws NetworkException;


    public String robPlayer(int robbingPlayerIndex, int victimIndex, HexLocation hex) throws NetworkException;


    public int getPlayerId();
}
