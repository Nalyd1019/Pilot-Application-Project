package model;


import java.util.List;

public class Airplane {

    private Logbook logbook;
    private String registration;

    public Airplane(String registration, Logbook logbook){
        this.registration = registration;
        this.logbook = logbook;

    }

    /* TODO - airplane gets dependency to flight...
    public int getFlightHours() {
        int hours = 0;
        for (Flight flight : logbook.getAirplanesEntries(registration)) {
            hours = hours + flight.getnHours();
        }
        return hours;
    } */

}
