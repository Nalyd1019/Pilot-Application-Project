package model;

import java.time.LocalDate;
import java.util.GregorianCalendar;

public final class Flight {
    private GregorianCalendar gregorianCalendar;
    private LocalDate date;
    private final int nHours;
    private final int nMinutes;
    private final int nStarts;
    private final String departurePlace;
    private final String destination;
    private final String comment;
    private final String airplaneRegistration;
    private final String pilotEmail;

    public Flight(LocalDate date, int nHours, int nMinutes, int nStarts, String departurePlace, String destination, String comment, String airplaneRegistration, String pilotEmail) {
        this.date = date;
        this.nHours = nHours;
        this.nMinutes = nMinutes;
        this.nStarts = nStarts;
        this.departurePlace = departurePlace;
        this.destination = destination;
        this.comment = comment;
        this.airplaneRegistration = airplaneRegistration;
        this.pilotEmail = pilotEmail;  // TODO - Ska denna vara här - eller ska den sättas via currentUser?
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

    public int getnStarts() {
        return nStarts;
    }

    public String getPilotEmail() {
        return pilotEmail;
    }

    public GregorianCalendar getGregorianCalendar() {
        return gregorianCalendar;
    }

    public String getComment() {
        return comment;
    }

    public LocalDate getDate() {
        return date;
    }
}
