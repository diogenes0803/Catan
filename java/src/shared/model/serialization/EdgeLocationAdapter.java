package shared.model.serialization;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;

import java.io.IOException;


public class EdgeLocationAdapter extends TypeAdapter<EdgeLocation> {
    @Override
    public void write(JsonWriter writer, EdgeLocation location) throws IOException {
        writer.beginObject();
        writer.name("direction").value(location.getDir().toAbbreviation());
        writer.name("x").value(location.getHexLoc().getX());
        writer.name("y").value(location.getHexLoc().getY());
        writer.endObject();
    }

    @Override
    public EdgeLocation read(JsonReader reader) throws IOException {
        EdgeDirection direction = null;
        Integer x = null;
        Integer y = null;

        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();

            switch (name) {
                case "x":
                    x = reader.nextInt();
                    break;
                case "y":
                    y = reader.nextInt();
                    break;
                case "direction":
                    direction = EdgeDirection.fromAbbreviation(reader.nextString());
                    break;
                default:
                    throw new IOException("Unrecognized token \"" + name + "\"");
            }
        }

        reader.endObject();

        if (x == null || y == null || direction == null) {
            throw new IOException("Incomplete EdgeLocation JSON.");
        }

        return new EdgeLocation(x, y, direction);
    }
}
