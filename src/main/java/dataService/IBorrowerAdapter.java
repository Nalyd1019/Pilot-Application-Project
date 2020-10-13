package dataService;

import com.google.gson.*;
import model.iBorrower;
import model.iBorrower;

import java.lang.reflect.Type;

public class IBorrowerAdapter implements JsonSerializer<iBorrower>, JsonDeserializer<iBorrower>{


        private static final String CLASSNAME = "CLASSNAME";
        private static final String DATA = "DATA";


        public iBorrower deserialize(JsonElement jsonElement, Type type,
                                     JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
            String className = prim.getAsString();
            Class klass = getObjectClass(className);
            return jsonDeserializationContext.deserialize(jsonObject.get(DATA), klass);
        }


        public JsonElement serialize(iBorrower iBorrower, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(CLASSNAME, iBorrower.getClass().getName());
            jsonObject.add(DATA, jsonSerializationContext.serialize(iBorrower));
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
