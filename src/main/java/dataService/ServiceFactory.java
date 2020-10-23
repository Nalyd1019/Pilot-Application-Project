package dataService;

/**
 * @Author Dylan Osolian
 * Factory class for data services.
 */
public class ServiceFactory {
    /**
     * Creates a an IdataService which is dynamically a JSONSerializer.
     * @return DataService
     */
    public static IDataService getService(){
        IDataService idataService = new JSONSaver();
        return idataService;
    }
}
