package dataService;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Class in order for GSON to be able to serialize and deserialize interfaces. Taken from GSON recomendations.
 * @param <T> What type to deserialize to.
 */

public class InterfaceAdapter<T> implements JsonSerializer, JsonDeserializer {

    private static final String CLASSNAME = "CLASSNAME";
    private static final String DATA = "DATA";



    public T deserialize(JsonElement jsonElement, Type type,
                         JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = prim.getAsString();
        Class klass = getObjectClass(className);
        return jsonDeserializationContext.deserialize(jsonObject.get(DATA), klass);
    }
    public JsonElement serialize(Object jsonElement, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(CLASSNAME, jsonElement.getClass().getName());
        jsonObject.add(DATA, jsonSerializationContext.serialize(jsonElement));
        return jsonObject;
    }
    /****** Helper method to get the className of the object to be deserialized *****/
    public Class getObjectClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            throw new JsonParseException(e.getMessage());
        }
    }
}

