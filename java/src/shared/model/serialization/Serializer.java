package shared.model.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import shared.model.CatanMap;
import shared.model.Game;
import shared.model.Log;
import shared.model.Player;

import java.io.Reader;
import java.io.Writer;
import java.util.logging.Level;


public class Serializer {
    private static Serializer serializer = new Serializer();
    private Gson gson;
    private Gson defaultGson;

    private Serializer() {
        initializeGson();
    }

    private void initializeGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Game.class, new GameAdapter());
        gsonBuilder.registerTypeAdapter(CatanColor.class, new EnumAdapter<>(CatanColor.class));
        gsonBuilder.registerTypeAdapter(ResourceType.class, new EnumAdapter<>(ResourceType.class));
        gsonBuilder.registerTypeAdapter(Log.class, new LogAdapter());
        gsonBuilder.registerTypeAdapter(CatanMap.class, new MapAdapter());
        gsonBuilder.registerTypeAdapter(Player.class, new PlayerAdapter());
        gsonBuilder.registerTypeAdapter(VertexLocation.class, new VertexLocationAdapter());
        gsonBuilder.registerTypeAdapter(EdgeLocation.class, new EdgeLocationAdapter());
        gsonBuilder.registerTypeAdapter(Level.class, new LoggerLevelAdapter());

        gson = gsonBuilder.create();

        defaultGson = new Gson();

    }

    public static Serializer instance() {
        assert serializer != null;
        return serializer;
    }

    public String toJson(Object object) {
        return gson.toJson(object);
    }

    public void toJson(Object object, Writer writer) {
        gson.toJson(object, writer);
    }

    public <T> T fromJson(Reader reader, Class<T> destinationClass) {
        return gson.fromJson(reader, destinationClass);
    }

    public <T> T defaultFromJson(JsonReader reader, Class<T> destinationClass) {
        return defaultGson.fromJson(reader, destinationClass);
    }
    public void toJson(Object src, JsonWriter jsonWriter) {
        gson.toJson(src, src.getClass(), jsonWriter);
    }

    public void defaultToJson(Object src, JsonWriter jsonWriter) {
        defaultGson.toJson(src, src.getClass(), jsonWriter);
    }
}
