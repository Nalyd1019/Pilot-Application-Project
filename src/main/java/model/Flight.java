package model;

import java.util.Date;
import java.util.GregorianCalendar;

public class Flight {
    private GregorianCalendar date;
    private int nHours;
    private int nMinutes;
    private int nStarts;
    private String departurePlace;
    private String destination;
    private String comment;
    private String airplaneRegistration;

    public Flight(GregorianCalendar date, int nHours, int nMinutes, int nStarts, String departurePlace, String destination, String comment, String airplaneRegistration) {
        this.date = date;
        this.nHours = nHours;
        this.nMinutes = nMinutes;
        this.nStarts = nStarts;
        this.departurePlace = departurePlace;
        this.destination = destination;
        this.comment = comment;
        this.airplaneRegistration = airplaneRegistration;
    }

    public String getAirplaneRegistration() {
        return airplaneRegistration;
    }

    public String getDestination() {
        return destination;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public int getnHours() {
        return nHours;
    }

    public int getnMinutes() {
        return nMinutes;
    }
}
