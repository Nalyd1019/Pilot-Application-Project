package dataService;

import model.FlyingClub;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IdataService {

    /**
     * @Author Dylan Osolian
     * Interface that represents a data service which can both load and save data.
     */
    List<FlyingClub> load() throws FileNotFoundException;
    void save(List<FlyingClub> flyingClubs) throws IOException;

}
