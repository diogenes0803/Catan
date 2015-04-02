package shared.model.serialization;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.locations.EdgeLocation;
import shared.model.CatanMap;
import shared.model.IRoad;
import shared.model.IHex;
import shared.model.ITown;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;


public class MapAdapter extends TypeAdapter<CatanMap> {

    @Override
    public void write(JsonWriter jsonWriter, CatanMap map) throws IOException {
        jsonWriter.beginObject();

        jsonWriter.name("hexes");
        jsonWriter.beginArray();
        for (IHex tile : map.getTiles()) {
            writeTile(jsonWriter, tile);
        }
        jsonWriter.endArray();

        jsonWriter.name("roads");
        jsonWriter.beginArray();
        for (IRoad road : map.getRoads()) {
            writeRoad(jsonWriter, road);
        }
        jsonWriter.endArray();

        jsonWriter.name("cities");
        writeTowns(jsonWriter, map.getCities());

        jsonWriter.name("settlements");
        writeTowns(jsonWriter, map.getSettlements());

        jsonWriter.name("radius").value(3); // TODO: this is the map radius

        jsonWriter.name("ports");
        jsonWriter.beginArray();
        for (Map.Entry<EdgeLocation, PortType> port : map.getPorts().entrySet()) {
            writePort(jsonWriter, port.getKey(), port.getValue());
        }
        jsonWriter.endArray();

        jsonWriter.name("robber");
        Serializer.instance().toJson(map.getRobber(), jsonWriter);

        jsonWriter.endObject();
    }

    private void writeTowns(JsonWriter jsonWriter, Collection<ITown> towns) throws IOException {
        jsonWriter.beginArray();

        for (ITown town : towns) {
            jsonWriter.beginObject();

            jsonWriter.name("owner").value(town.getOwner().getIndex());

            jsonWriter.name("location");
            Serializer.instance().toJson(town.getLocation(), jsonWriter);

            jsonWriter.endObject();
        }

        jsonWriter.endArray();
    }

    private void writeRoad(JsonWriter writer, IRoad road) throws IOException {
        writer.beginObject();

        writer.name("owner").value(road.getOwner().getIndex());

        writer.name("location");
        Serializer.instance().toJson(road.getLocation(), writer);

        writer.endObject();
    }

    private void writePort(JsonWriter jsonWriter, EdgeLocation location, PortType type) throws IOException {
        jsonWriter.beginObject();

        if (type == PortType.THREE) {
            jsonWriter.name("ratio").value(3);
        }
        else {
            jsonWriter.name("ratio").value(2);
            jsonWriter.name("resource").value(type.toString().toLowerCase());
        }

        jsonWriter.name("direction").value(location.getDir().toAbbreviation());

        jsonWriter.name("location");
        Serializer.instance().toJson(location.getHexLoc(), jsonWriter);

        jsonWriter.endObject();
    }

    private void writeTile(JsonWriter jsonWriter, IHex tile) throws IOException {
        jsonWriter.beginObject();

        HexType type = tile.type();
        if (type != HexType.DESERT) {
            jsonWriter.name("resource").value(tile.type().toString().toLowerCase());
            jsonWriter.name("number").value(tile.numberToken());
        }

        jsonWriter.name("location");
        Serializer.instance().toJson(tile.location(), jsonWriter);

        jsonWriter.endObject();
    }

    @Override
    public CatanMap read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
