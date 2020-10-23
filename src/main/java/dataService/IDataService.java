package dataService;

import model.FlyingClub;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @Author Dylan Osolian
 * Interface that represents a data service which can both load and save data.
 */

public interface IDataService {

    List<FlyingClub> load() throws FileNotFoundException;
    void save(List<FlyingClub> flyingClubs) throws IOException;

}
