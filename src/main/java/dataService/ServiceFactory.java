package dataService;

public class ServiceFactory {

    public static IdataService getService(){

        IdataService idataService = new JSONSerializer();
        return idataService;
    }
}
