package shared.model.serialization;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import shared.model.IPieceBank;
import shared.model.Player;

import java.io.IOException;


public class PlayerAdapter extends TypeAdapter<Player> {

    @Override
    public void write(JsonWriter writer, Player player) throws IOException {
        writer.beginObject();

        writer.name("resources");
        Serializer.instance().toJson(player.getResources(), writer);

        writer.name("oldDevCards");
        Serializer.instance().toJson(player.getPlayableDevCards(), writer);

        writer.name("newDevCards");
        Serializer.instance().toJson(player.getNewDevCards(), writer);

        writePieceBank(writer, player.getPieceBank());

        writer.name("soldiers").value(player.getSoldiers());

        writer.name("victoryPoints").value(player.getVictoryPoints());

        writer.name("monuments").value(player.getMonuments());

        writer.name("playedDevCard").value(player.hasPlayedDevCard());

        writer.name("discarded").value(player.hasDiscarded());

        writer.name("playerID").value(player.getId());

        writer.name("playerIndex").value(player.getIndex());

        writer.name("name").value(player.getName());

        writer.name("color");
        Serializer.instance().toJson(player.getColor(), writer);

        writer.endObject();
    }

    private void writePieceBank(JsonWriter writer, IPieceBank pieces) throws IOException {
        writer.name("roads").value(pieces.availableRoads());
        writer.name("cities").value(pieces.availableCities());
        writer.name("settlements").value(pieces.availableSettlements());
    }

    @Override
    public Player read(JsonReader reader) throws IOException {
        return null;
    }
}
