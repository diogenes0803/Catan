package shared.model.serialization;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

import java.io.IOException;


public class VertexLocationAdapter extends TypeAdapter<VertexLocation> {
    @Override
    public void write(JsonWriter writer, VertexLocation location) throws IOException {
        writer.beginObject();
        writer.name("direction").value(location.getDir().toAbbreviation());
        writer.name("x").value(location.getHexLoc().getX());
        writer.name("y").value(location.getHexLoc().getY());
        writer.endObject();
    }

    @Override
    public VertexLocation read(JsonReader reader) throws IOException {
        VertexDirection direction = null;
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
                    direction = VertexDirection.fromAbbreviation(reader.nextString());
                    break;
                default:
                    throw new IOException("Unrecognized token \"" + name + "\"");
            }
        }

        reader.endObject();

        if (x == null || y == null || direction == null) {
            throw new IOException("Incomplete VertexLocation JSON.");
        }

        return new VertexLocation(x, y, direction);
    }
}
