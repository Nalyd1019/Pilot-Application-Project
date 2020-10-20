package model;

import java.time.LocalDate;


/**
 * @author Lisa Samuelsson
 * The class is responsible for saving data about a flight and keeping it immutable.
 */
public final class Flight {

    /**
     * The date the flight took place.
     */
    private LocalDate date;

    /**
     * How many hours the flight lasted.
     */
    private final int nHours;

    /**
     * How many minutes the flight lasted.
     */
    private final int nMinutes;

    /**
     * How many starts the flight had.
     */
    private final int nStarts;

    /**
     * The place of departure.
     */
    private final String departurePlace;

    /**
     * The destination of the flight.
     */
    private final String destination;

    /**
     * Any comments made by the pilot about the flight.
     */
    private final String comment;

    /**
     * The registration of the airplane the flight was made in.
     */
    private final String airplaneRegistration;

    /**
     * The email of the pilot who made the flight.
     */
    private final String pilotEmail;



    public Flight(LocalDate date, int nHours, int nMinutes, int nStarts, String departurePlace, String destination,
                  String comment, String airplaneRegistration, String pilotEmail) {
        this.date = date;
        this.nHours = nHours;
        this.nMinutes = nMinutes;
        this.nStarts = nStarts;
        this.departurePlace = departurePlace;
        this.destination = destination;
        this.comment = comment;
        this.airplaneRegistration = airplaneRegistration;
        this.pilotEmail = pilotEmail;
    }


    // Getters
    public String getAirplaneRegistration() {
        return airplaneRegistration;
    }

    String getDestination() {
        return destination;
    }

    String getDeparturePlace() {
        return departurePlace;
    }

    int getNHours() {
        return nHours;
    }

    int getNMinutes() {
        return nMinutes;
    }

    int getNStarts() {
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
