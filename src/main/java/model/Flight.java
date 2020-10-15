package model;

import java.time.LocalDate;

public final class Flight {
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

    public int getNHours() {
        return nHours;
    }

    public int getNMinutes() {
        return nMinutes;
    }

    public int getNStarts() {
        return nStarts;
    }

    public String getPilotEmail() {
        return pilotEmail;
    }

    public String getComment() {
        return comment;
    }

    public LocalDate getDate() {
        return date;
    }
}
