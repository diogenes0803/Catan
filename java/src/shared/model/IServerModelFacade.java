package shared.model;

import client.network.IServerProxy;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;


public interface IServerModelFacade {


    public void setServerProxy(IServerProxy game);


    public void sendChat(String message) throws ModelException;


    public void placeRoad(EdgeLocation edge) throws ModelException;


    public void placeSettlement(VertexLocation vertex) throws ModelException;


    public void placeCity(VertexLocation vertex) throws ModelException;


    public void buyDevCard() throws ModelException;


    public void playSoldier(HexLocation hex, int victimIndex) throws ModelException;


    public void playYearOfPlenty(ResourceType r1, ResourceType r2) throws ModelException;


    public void playRoadBuilding(EdgeLocation e1, EdgeLocation e2) throws ModelException;


    public void playMonopoly(ResourceType rType) throws ModelException;


    public void playMonument() throws ModelException;


    public void robPlayer(HexLocation hex, int victimIndex) throws ModelException;


    // trading

    public void acceptTrade(boolean willAccept) throws ModelException;


    public void offerTrade(IResourceBank offer, int recipientPlayerIndex) throws ModelException;


    public void maritimeTrade(int ratio, ResourceType giving, ResourceType getting) throws ModelException;


    public void discardCards(IResourceBank discardedCards) throws ModelException;


    public int rollNumber() throws ModelException;


    public void finishTurn() throws ModelException;
}
