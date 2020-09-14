package model;



public class Airplane {

    private Logbook logbook;
    private String registration;

    public Airplane(String registration, Logbook logbook){
        this.registration = registration;
        this.logbook = logbook;

    }
}
