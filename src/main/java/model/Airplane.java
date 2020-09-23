package model;


import java.util.List;

public class Airplane {

    private Logbook logbook;
    private String registration;

    public Airplane(String registration, Logbook logbook){
        this.registration = registration;
        this.logbook = logbook;

    }

    public int getTotalHours(){
        return logbook.getFlightHours(registration);
    }

    public int getTotalMinutes(){
        return logbook.getFlightMinutes(registration);
    }


}
