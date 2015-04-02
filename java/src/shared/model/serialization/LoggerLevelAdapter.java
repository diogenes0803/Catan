package shared.model.serialization;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.util.logging.Level;


public class LoggerLevelAdapter extends TypeAdapter<Level> {
    @Override
    public void write(JsonWriter writer, Level value) throws IOException {
        writer.value(value.toString());
    }

    @Override
    public Level read(JsonReader reader) throws IOException {
        Level level = null;
        reader.beginObject();

        try {
            if (reader.nextName().equalsIgnoreCase("logLevel")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                }
                else {

                    level = Level.parse(reader.nextString().toUpperCase());
                }
            }
            else {
                throw new MalformedJsonException("Improperly formatted logger level command.");
            }
        }
        catch (IllegalArgumentException e) {
            throw new MalformedJsonException("Unrecognized logger level.");
        }

        reader.endObject();

        return level;
    }
}
