package client.network;

import com.google.gson.JsonObject;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.IResourceBank;


public class ServerProxy implements IServerProxy {

    private IHttpCommunicator m_httpCommunicator;

    public ServerProxy(IHttpCommunicator httpCommunicator) {
        m_httpCommunicator = httpCommunicator;
    }


    @Override
    public String getGameState() throws NetworkException {
        String response = m_httpCommunicator.get("/game/model");

        return response;
    }


    @Override
    public String resetGame() throws NetworkException {
        String request =
                "{" +
                "}";

        String response = m_httpCommunicator.post("/game/reset", request);

        return response;
    }


    @Override
    public boolean changeLogLevel(String logLevel) throws NetworkException {
        String request =
                "{" +
                    "\"logLevel\":" + logLevel +
                "}";

        String response = m_httpCommunicator.post("/util/changeLogLevel", request);

        return (response != null ? true : false);
    }


    @Override
    public String sendChat(int playerIndex, String message) throws NetworkException {

        JsonObject json = new JsonObject();
        json.addProperty("type", "sendChat");
        json.addProperty("playerIndex", playerIndex);
        json.addProperty("content", message);

        String response = m_httpCommunicator.post("/moves/sendChat", json.toString());

        return response;
    }


    @Override
    public String acceptTrade(int playerIndex, boolean willAccept) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("type", "acceptTrade");
        json.addProperty("playerIndex", playerIndex);
        json.addProperty("willAccept", willAccept);

        String response = m_httpCommunicator.post("/moves/acceptTrade", json.toString());

        return response;
    }


    @Override
    public String discardCards(int playerIndex, IResourceBank discardedCards) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("type", "discardCards");
        json.addProperty("playerIndex", playerIndex);
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("brick", discardedCards.getBrick());
        innerObject.addProperty("ore", discardedCards.getOre());
        innerObject.addProperty("sheep", discardedCards.getSheep());
        innerObject.addProperty("wheat", discardedCards.getWheat());
        innerObject.addProperty("wood", discardedCards.getWood());
        json.add("discardedCards", innerObject);

        String response = m_httpCommunicator.post("/moves/discardCards", json.toString());

        return response;
    }


    @Override
    public String rollNumber(int playerIndex, int number) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("type", "rollNumber");
        json.addProperty("playerIndex", playerIndex);
        json.addProperty("number", number);

        String response = m_httpCommunicator.post("/moves/rollNumber", json.toString());

        return response;
    }


    @Override
    public String buildRoad(int playerIndex, EdgeLocation edgeLoc, boolean free) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("type", "buildRoad");
        json.addProperty("playerIndex", playerIndex);
        json.add("roadLocation", toJSON(edgeLoc));
        json.addProperty("free", free);

        String response = m_httpCommunicator.post("/moves/buildRoad", json.toString());

        return response;
    }


    @Override
    public String buildSettlement(int playerIndex, VertexLocation location, boolean free) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("type", "buildSettlement");
        json.addProperty("playerIndex", playerIndex);
        JsonObject vertexLocObject = new JsonObject();
        vertexLocObject.addProperty("x", location.getHexLoc().getX());
        vertexLocObject.addProperty("y", location.getHexLoc().getY());
        vertexLocObject.addProperty("direction", location.getDir().toAbbreviation());
        json.add("vertexLocation", vertexLocObject);
        json.addProperty("free", free);

        String response = m_httpCommunicator.post("/moves/buildSettlement", json.toString());

        return response;
    }


    @Override
    public String buildCity(int playerIndex, VertexLocation location) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("type", "buildCity");
        json.addProperty("playerIndex", playerIndex);
        JsonObject vertexLocObject = new JsonObject();
        vertexLocObject.addProperty("x", location.getHexLoc().getX());
        vertexLocObject.addProperty("y", location.getHexLoc().getY());
        vertexLocObject.addProperty("direction", location.getDir().toAbbreviation());
        json.add("vertexLocation", vertexLocObject);

        String response = m_httpCommunicator.post("/moves/buildCity", json.toString());

        return response;
    }


    @Override
    public String offerTrade(int playerIndex, IResourceBank offer, int receiver) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("type", "offerTrade");
        json.addProperty("playerIndex", playerIndex);
        JsonObject tradeObject = new JsonObject();
        tradeObject.addProperty("brick", offer.getBrick());
        tradeObject.addProperty("ore", offer.getOre());
        tradeObject.addProperty("sheep", offer.getSheep());
        tradeObject.addProperty("wheat", offer.getWheat());
        tradeObject.addProperty("wood", offer.getWood());
        json.add("offer", tradeObject);
        json.addProperty("receiver", receiver);

        String response = m_httpCommunicator.post("/moves/offerTrade", json.toString());

        return response;
    }


    @Override
    public String maritimeTrade(int playerIndex, int ratio, ResourceType input, ResourceType output) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("type", "maritimeTrade");
        json.addProperty("playerIndex", playerIndex);
        json.addProperty("ratio", ratio);
        json.addProperty("inputResource", input.name().toLowerCase());
        json.addProperty("outputResource", output.name().toLowerCase());

        String response = m_httpCommunicator.post("/moves/maritimeTrade", json.toString());

        return response;
    }


    @Override
    public String finishTurn(int playerIndex) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("type", "finishTurn");
        json.addProperty("playerIndex", playerIndex);

        String response = m_httpCommunicator.post("/moves/finishTurn", json.toString());

        return response;
    }


    @Override
    public String buyDevCard(int playerIndex) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("type", "buyDevCard");
        json.addProperty("playerIndex", playerIndex);

        String response = m_httpCommunicator.post("/moves/buyDevCard", json.toString());

        return response;
    }


    @Override
    public String playYearOfPlenty(int playerIndex, ResourceType resource1, ResourceType resource2) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("type", "Year_of_Plenty");
        json.addProperty("playerIndex", playerIndex);
        json.addProperty("resource1", resource1.name().toLowerCase());
        json.addProperty("resource2", resource2.name().toLowerCase());

        String response = m_httpCommunicator.post("/moves/Year_of_Plenty", json.toString());

        return response;
    }


    @Override
    public String playRoadBuilding(int playerIndex, EdgeLocation location1, EdgeLocation location2) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("type", "Road_Building");
        json.addProperty("playerIndex", playerIndex);
        json.add("spot1", toJSON(location1));
        json.add("spot2", toJSON(location2));

        String response = m_httpCommunicator.post("/moves/Road_Building", json.toString());

        return response;
    }


    @Override
    public String playSoldier(int playerIndex, HexLocation location, int victim) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("type", "Soldier");
        json.addProperty("playerIndex", playerIndex);
        json.addProperty("victimIndex", victim);
        JsonObject locObject = new JsonObject();
        locObject.addProperty("x", location.getX());
        locObject.addProperty("y", location.getY());
        json.add("location", locObject);

        String response = m_httpCommunicator.post("/moves/Soldier", json.toString());

        return response;
    }


    @Override
    public String playMonopoly(int playerIndex, ResourceType resource) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("type", "Monopoly");
        json.addProperty("resource", resource.name().toLowerCase());
        json.addProperty("playerIndex", playerIndex);

        String response = m_httpCommunicator.post("/moves/Monopoly", json.toString());

        return response;
    }


    @Override
    public String playMonument(int playerIndex) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("type", "Monument");
        json.addProperty("playerIndex", playerIndex);

        String response = m_httpCommunicator.post("/moves/Monument", json.toString());

        return response;
    }

    @Override
    public String robPlayer(int robbingPlayerIndex, int victimIndex, HexLocation hex) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("type", "robPlayer");
        json.addProperty("playerIndex", robbingPlayerIndex);
        json.addProperty("victimIndex", victimIndex);
        JsonObject locObject = new JsonObject();
        locObject.addProperty("x", hex.getX());
        locObject.addProperty("y", hex.getY());
        json.add("location", locObject);

        String response = m_httpCommunicator.post("/moves/robPlayer", json.toString());

        return response;

    }

    @Override
    public int getPlayerId() {
        return m_httpCommunicator.getPlayerId();
    }

    // Convert an EdgeLocation to JSON
    private JsonObject toJSON(EdgeLocation location) {
        assert location != null;

        JsonObject edge = new JsonObject();
        edge.addProperty("x", location.getHexLoc().getX());
        edge.addProperty("y", location.getHexLoc().getY());
        edge.addProperty("direction", location.getDir().toAbbreviation());

        return edge;
    }
}
