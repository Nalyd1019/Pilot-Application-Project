package dataService;

import com.google.gson.*;
import model.FlyingClub;
import model.iBookable;
import model.iBorrower;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Dylan Osolian
 * Class thats responsible for saving data used in application in a JSON file.
 */

class JSONSaver implements IdataService {

    private static String savedData = "src/main/java/resources/savedData.json";

    /**
     * Method to parse and save list of flyingclubs as a JSON file.
     * @param flyingClubList List of flyingclubs to save.
     * @throws IOException
     */
    public void save(List<FlyingClub> flyingClubList) throws IOException {

        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(iBookable.class, new IBookableAdapter());
        gsonBuilder.registerTypeAdapter(iBorrower.class, new IBorrowerAdapter());
        Gson gson = gsonBuilder.create();
        String json= gson.toJson(flyingClubList);
        Writer writer = new FileWriter(savedData);
        writer.write(json);
        writer.flush();
        writer.close();
    }

    /**
     * Method to parse JSON file to a list of FlyingClub java objects.
     * @return List of flyingClubs.
     * @throws FileNotFoundException
     */
    public List<FlyingClub> load() throws FileNotFoundException {

        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(iBookable.class, new IBookableAdapter());
        gsonBuilder.registerTypeAdapter(iBorrower.class, new IBorrowerAdapter());
        Gson gson = gsonBuilder.create();
        JsonArray jsonList = gson.fromJson(new FileReader(savedData), JsonArray.class);
        List<FlyingClub> flyingClubs = new ArrayList<>();
        for (Object o : jsonList){
            flyingClubs.add(gson.fromJson(o.toString(), FlyingClub.class));
        }
        return flyingClubs;
    }



}
