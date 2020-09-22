package dataService;

import model.FlyingClub;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IdataService {

    List<FlyingClub> load() throws FileNotFoundException;
    void save(List<FlyingClub> flyingClubs) throws IOException;

}
