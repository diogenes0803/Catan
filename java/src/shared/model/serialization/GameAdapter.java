package shared.model.serialization;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import shared.model.CatanConstants;
import shared.model.Game;
import shared.model.IPlayer;
import shared.model.Player;

import java.io.IOException;


public class GameAdapter extends TypeAdapter<Game> {

    @Override
    public void write(JsonWriter jsonWriter, Game game) throws IOException {
        jsonWriter.beginObject();

        jsonWriter.name("deck");
        Serializer.instance().toJson(game.getDevCards(), jsonWriter);

        jsonWriter.name("map");
        Serializer.instance().toJson(game.getMap(), jsonWriter);

        writePlayers(jsonWriter, game);

        jsonWriter.name("log");
        Serializer.instance().toJson(game.getGameplayLog(), jsonWriter);

        jsonWriter.name("chat");
        Serializer.instance().toJson(game.getChatHistory(), jsonWriter);

        jsonWriter.name("bank");
        Serializer.instance().toJson(game.getResourceBank(), jsonWriter);

        writeTradeOffer(jsonWriter, game);

        writeTurnTracker(jsonWriter, game);

        jsonWriter.name("winner");
        jsonWriter.value(game.getWinner() != null ? game.getWinner().getId() : Player.NO_PLAYER); // TODO: INDEX OR ID?

        jsonWriter.name("version");
        jsonWriter.value(game.getVersion());

        jsonWriter.endObject();
    }

    private void writeTradeOffer(JsonWriter jsonWriter, Game game) throws IOException {
        if (game.getTradeOffer() != null) {
            jsonWriter.name("tradeOffer");

            jsonWriter.beginObject();
            jsonWriter.name("sender").value(game.getTradeOffer().getSender().getIndex());

            jsonWriter.name("offer");
            Serializer.instance().toJson(game.getTradeOffer().getOffer(), jsonWriter);

            jsonWriter.name("receiver").value(game.getTradeOffer().getReceiver().getIndex());
            jsonWriter.endObject();
        }
    }

    private void writeTurnTracker(JsonWriter jsonWriter, Game game) throws IOException {
        jsonWriter.name("turnTracker");
        jsonWriter.beginObject();

        jsonWriter.name("status");
        Serializer.instance().toJson(game.getGameState(), jsonWriter);

        jsonWriter.name("currentTurn");
        jsonWriter.value(game.getCurrentPlayer() != null ? game.getCurrentPlayer().getIndex() : Player.NO_PLAYER);

        jsonWriter.name("longestRoad");
        jsonWriter.value(game.getLongestRoad() != null ? game.getLongestRoad().getIndex() : Player.NO_PLAYER);

        jsonWriter.name("largestArmy");
        jsonWriter.value(game.getLargestArmy() != null ? game.getLargestArmy().getIndex() : Player.NO_PLAYER);

        jsonWriter.endObject();
    }

    private void writePlayers(JsonWriter jsonWriter, Game game) throws IOException {
        jsonWriter.name("players");
        jsonWriter.beginArray();

        int playersToOutput = CatanConstants.NUM_PLAYERS;
        for (IPlayer player : game.getPlayers()) {
            Serializer.instance().toJson(player, jsonWriter);
            playersToOutput--;
        }
        // fill in nulls for the rest of the players
        for (int i = 0; i < playersToOutput; ++i) {
            jsonWriter.nullValue();
        }

        jsonWriter.endArray();
    }


    @Override
    public Game read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
