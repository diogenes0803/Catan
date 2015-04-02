package client.network;

import client.data.GameInfo;
import client.data.PlayerInfo;
import com.google.gson.stream.JsonReader;
import shared.definitions.CatanColor;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


public class GameAdministrator implements IGameAdministrator {
    private static GameAdministrator m_admin = null;
    private IGameAdminServerProxy m_gameAdminServerProxy;

    private GameAdministrator(IGameAdminServerProxy gameAdminServerProxy) {
        setGameAdminServerProxy(gameAdminServerProxy);
    }

    public static GameAdministrator getInstance() {
        if (m_admin == null) {
            m_admin = new GameAdministrator(new GameAdminServerProxy(new HttpCommunicator()));
        }
        return m_admin;
    }

    @Override
    public void setGameAdminServerProxy(IGameAdminServerProxy gameAdminServerProxy) {
        assert gameAdminServerProxy != null;
        m_gameAdminServerProxy = gameAdminServerProxy;
    }

    @Override
    public boolean login(String username, String password) throws NetworkException {
        return m_gameAdminServerProxy.login(username, password);
    }

    @Override
    public boolean register(String username, String password) throws NetworkException {
        return m_gameAdminServerProxy.register(username, password);
    }

    @Override
    public List<GameInfo> listGames() throws NetworkException, IOException {
        String gamesList = m_gameAdminServerProxy.listGames();

        if (gamesList == null || gamesList.isEmpty()) {
            throw new NetworkException("Received %s response for list games.".format(gamesList == null ? "null" : "empty"));
        }

        JsonReader reader = new JsonReader(new StringReader(gamesList));
        return readGameList(reader);
    }

    @Override
    public boolean joinGame(int gameIndex, CatanColor playerColor) throws NetworkException {
        return m_gameAdminServerProxy.joinGame(gameIndex, playerColor);
    }

    @Override
    public GameInfo createGame(boolean randomTiles, boolean randomNumbers, boolean randomPorts, String gameName) throws NetworkException, IOException {
        JsonReader reader = new JsonReader(new StringReader(m_gameAdminServerProxy.createGame(randomTiles, randomNumbers, randomPorts, gameName)));
        return readGame(reader);
    }

    @Override
    public String[] listAI() throws NetworkException {
        JsonReader reader = new JsonReader(new StringReader(m_gameAdminServerProxy.listAI()));

        try {
            return readAIPlayers(reader);
        }
        catch (IOException e) {
            throw new NetworkException(e);
        }
    }

    @Override
    public boolean addAI(String nameOfAI) throws NetworkException {
        String response = m_gameAdminServerProxy.addAI(nameOfAI);

        return response != null;
    }

    @Override
    public String getLocalPlayerName() {
        return m_gameAdminServerProxy.getLocalPlayerName();
    }

    @Override
    public int getLocalPlayerId() {
        return m_gameAdminServerProxy.getLocalPlayerId();
    }

    private List<GameInfo> readGameList(JsonReader reader) throws IOException {
        List<GameInfo> gameInfoList = new ArrayList<GameInfo>();

        reader.beginArray();

        while (reader.hasNext()) {
            gameInfoList.add(readGame(reader));
        }

        reader.endArray();

        return gameInfoList;
    }

    private GameInfo readGame(JsonReader reader) throws IOException {
        GameInfo gameInfo = new GameInfo();

        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();

            if (name.equals("title")) {
                gameInfo.setTitle(reader.nextString());
            } else if (name.equals("id")) {
                gameInfo.setId(reader.nextInt());
            } else if (name.equals("players")) {
                readPlayers(reader, gameInfo);
            } else {
                reader.skipValue();
            }
        }

        reader.endObject();

        return gameInfo;
    }

    private void readPlayers(JsonReader reader, GameInfo gameInfo) throws IOException {

        reader.beginArray();

        while (reader.hasNext()) {
            PlayerInfo playerInfo = readPlayerInfo(reader);
            if (playerInfo != null) {
                gameInfo.addPlayer(playerInfo);
            }
        }
        reader.endArray();
    }

    private PlayerInfo readPlayerInfo(JsonReader reader) throws IOException {
        reader.beginObject();

        PlayerInfo playerInfo = new PlayerInfo();

        while (reader.hasNext()) {
            String name = reader.nextName();

            if (name.equals("color")) {
                playerInfo.setColor(CatanColor.valueOf(reader.nextString().toUpperCase()));
            } else if (name.equals("name")) {
                playerInfo.setName(reader.nextString());
            } else if (name.equals("id")) {
                playerInfo.setId(reader.nextInt());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();

        return playerInfo;
    }

    private String[] readAIPlayers(JsonReader reader) throws IOException {
        reader.beginArray();

        List<String> players = new ArrayList<String>();

        while (reader.hasNext()) {
            players.add(reader.nextString());
        }

        String[] returnValue = new String[players.size()];
        for (int i = 0; i < returnValue.length; ++i) {
            returnValue[i] = players.get(i);
        }

        return returnValue;
    }
}