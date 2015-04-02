package client.network;

import com.google.gson.JsonObject;
import shared.definitions.CatanColor;


public class GameAdminServerProxy implements IGameAdminServerProxy {

    private IHttpCommunicator m_httpCommunicator;

    public GameAdminServerProxy(IHttpCommunicator httpCommunicator) {
        m_httpCommunicator = httpCommunicator;
    }

    @Override
    public boolean login(String username, String password) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("username", username);
        json.addProperty("password", password);
        String response = m_httpCommunicator.post("/user/login", json.toString());

        return response != null;
    }

    @Override
    public boolean register(String username, String password) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("username", username);
        json.addProperty("password", password);

        String response = m_httpCommunicator.post("/user/register", json.toString());

        return response != null;
    }

    @Override
    public String listGames() throws NetworkException {
        return m_httpCommunicator.get("/games/list");
    }

    @Override
    public String createGame(boolean randTiles, boolean randNum, boolean randPorts, String name) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("randomTiles", randTiles);
        json.addProperty("randomNumbers", randNum);
        json.addProperty("randomPorts", randPorts);
        json.addProperty("name", name);

        return m_httpCommunicator.post("/games/create", json.toString());
    }

    @Override
    public boolean joinGame(int gameId, CatanColor playerColor) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("id", gameId);
        json.addProperty("color", playerColor.name().toLowerCase());
        String response = m_httpCommunicator.post("/games/join", json.toString());

        return response != null;
    }

    @Override
    public String listAI() throws NetworkException {
        return m_httpCommunicator.get("/game/listAI");
    }

    @Override
    public String addAI(String name) throws NetworkException {
        JsonObject json = new JsonObject();
        json.addProperty("AIType", name);

        String response = m_httpCommunicator.post("/game/addAI", json.toString());

        return response;
    }

    @Override
    public String getLocalPlayerName() {
        return m_httpCommunicator.getPlayerName();
    }

    @Override
    public int getLocalPlayerId() {
        return m_httpCommunicator.getPlayerId();
    }
}
