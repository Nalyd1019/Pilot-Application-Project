package dataService;

import com.google.gson.*;
import model.FlyingClub;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class JSONSerializer implements IdataService {

    private static String savedData = "src/main/java/resources/savedData.json";

    public void save(List<FlyingClub> flyingClubList) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
       String json= gson.toJson(flyingClubList);
       Writer writer = new FileWriter(savedData);
       writer.write(json);
       writer.flush();
       writer.close();
    }

    public List<FlyingClub> load() throws FileNotFoundException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonList = gson.fromJson(new FileReader(savedData), JsonArray.class);
        List<FlyingClub> flyingClubs = new ArrayList<>();
        for (Object o : jsonList){
            flyingClubs.add(gson.fromJson(o.toString(), FlyingClub.class));
        }
        return flyingClubs;
    }



}
