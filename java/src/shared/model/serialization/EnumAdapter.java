package shared.model.serialization;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;


public class EnumAdapter<T extends Enum> extends TypeAdapter<T> {
    Class<T> clazz;

    public EnumAdapter(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public void write(JsonWriter writer, T value) throws IOException {
        writer.value(value.toString().toLowerCase());
    }

    @Override
    @SuppressWarnings("unchecked")
    public T read(JsonReader reader) throws IOException {
        try {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return null;
            }
            else {
                return (T)T.valueOf(clazz, reader.nextString().toUpperCase());
            }
        }
        catch (IllegalArgumentException | JsonSyntaxException | MalformedJsonException e) {
            throw new MalformedJsonException("Unrecognized enumerated type.", e);
        }
    }
}
