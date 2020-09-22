package model;

import java.util.Date;

public class Flight {
    private Date date;
    private int nHours;
    private int nMinutes;
    private int nStarts;
    private String place;
    private String destination;
    private String comment;
    private String airplaneRegistration;

    public Flight(Date date, int nHours, int nMinutes, int nStarts, String place, String destination, String comment, String airplaneRegistration) {
        this.date = date;
        this.nHours = nHours;
        this.nMinutes = nMinutes;
        this.nStarts = nStarts;
        this.place = place;
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
}
