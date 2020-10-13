package dataService;

import model.iBookable;
import com.google.gson.*;

import java.lang.reflect.Type;

public class IBookableAdapter implements JsonSerializer<iBookable>, JsonDeserializer<iBookable>  {

    
        private static final String CLASSNAME = "CLASSNAME";
        private static final String DATA = "DATA";


        public iBookable deserialize(JsonElement jsonElement, Type type,
                                     JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
            String className = prim.getAsString();
            Class klass = getObjectClass(className);
            return jsonDeserializationContext.deserialize(jsonObject.get(DATA), klass);
        }


        public JsonElement serialize(iBookable iBookable, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(CLASSNAME, iBookable.getClass().getName());
            jsonObject.add(DATA, jsonSerializationContext.serialize(iBookable));
            return jsonObject;
        }




        private Class getObjectClass(String className) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                //e.printStackTrace();
                throw new JsonParseException(e.getMessage());
            }
        }
    
    
}
