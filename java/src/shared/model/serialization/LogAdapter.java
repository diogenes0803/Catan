package shared.model.serialization;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import shared.model.Log;

import java.io.IOException;


public class LogAdapter extends TypeAdapter<Log> {

    @Override
    public void write(JsonWriter jsonWriter, Log log) throws IOException {
        jsonWriter.beginObject();

        jsonWriter.name("lines");
        jsonWriter.beginArray();

        for (Log.LogMessage message : log) {
            jsonWriter.beginObject();
            jsonWriter.name("source").value(message.getPlayer().getName());
            jsonWriter.name("message").value(message.getMessage());
            jsonWriter.endObject();
        }

        jsonWriter.endArray();

        jsonWriter.endObject();
    }

    @Override
    public Log read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
