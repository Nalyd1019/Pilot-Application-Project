package data;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import model.Flight;
import model.FlyingClub;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JSONSerializer {

    private static String savedData = "src/main/resources/savedData.json";

    public void serializeToJson(List<FlyingClub> flyingClubList) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
       String json= gson.toJson(flyingClubList);
       Writer writer = new FileWriter(savedData);
       writer.write(json);
       writer.flush();
       writer.close();
    }

    public List<FlyingClub> getFlyingClubs() throws FileNotFoundException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonList = gson.fromJson(new FileReader(savedData), JsonArray.class);
        List<FlyingClub> flyingClubs = new ArrayList<>();
        for (Object o : jsonList){
            flyingClubs.add(gson.fromJson(o.toString(), FlyingClub.class));
        }
        return flyingClubs;
    }



}
