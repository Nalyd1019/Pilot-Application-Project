package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lisa Samuelsson
 * The logbook holds flights, and can be filtered based on elements in the flight.
 */
public class Logbook {

    /**
     * A list of all all flights in the logbook.
     */
    private List<Flight> flights;

    public Logbook(List<Flight> flights) {
        this.flights = flights;
    }

    Logbook(){
        flights = new ArrayList<>();
    }

    /**
     *
     * @param date the date of the flight
     * @param nHours how many hours the flight lasted
     * @param nMinutes how many minutes the flight lasted
     * @param nStarts the amounts of starts on the flight
     * @param departurePlace the place of departure
     * @param destination the destination of the flight
     * @param comment any comments by the pilot
     * @param airplaneRegistration the registration of the aircraft
     */
    void addLogbookEntry(LocalDate date, int nHours, int nMinutes, int nStarts, String departurePlace, String destination, String comment, String airplaneRegistration, String pilotEmail) {
        Flight flight = new Flight(date, nHours, nMinutes, nStarts, departurePlace, destination, comment, airplaneRegistration, pilotEmail);
        flights.add(flight);
    }


    /**
     * Method that returns all entries with the specific airplane's registration.
     * @param registration the airplanes registration
     * @return list of entries with that airplane
     */
    private List<Flight> getAirplanesEntries(String registration) {
        List<Flight> airplanesEntries = new ArrayList<>();
        for(Flight fli : flights){
            if(registration.equals(fli.getAirplaneRegistration())) {
                airplanesEntries.add(fli);
            }
        }
        return airplanesEntries;
    }


    /**
     * Method that gets all entries made by a specific pilot
     * @param pilotEmail the pilots email, the way to identify the pilot who made the entry
     * @return all entries made by that pilot
     */
    List<Flight> getPilotsEntries(String pilotEmail) {
        List<Flight> pilotsEntries = new ArrayList<>();
        for(Flight fli : flights){
            if(pilotEmail.equals(fli.getPilotEmail())) {
                pilotsEntries.add(fli);
            }
        }
        return pilotsEntries;
    }


    /**
     * Method that returns all entries too a specific destination
     * @param destination the destination of the entry
     * @return list of entries to that destination
     */
    List<Flight> getDestinationEntries(String destination) {
        List<Flight> destinationEntries = new ArrayList<>();
        for(Flight fli : flights){
            if(destination.equals(fli.getDestination())) {
                destinationEntries.add(fli);
            }
        }
        return destinationEntries;
    }


    /**
     * Method that returns all entries from a specific place
     * @param departurePlace the place of departure in the entry
     * @return list of entries with departures from that place
     */
    public List<Flight> getPlaceEntries(String departurePlace) {
        List<Flight> placeEntries = new ArrayList<>();
        for(Flight fli : flights){
            if(departurePlace.equals(fli.getDeparturePlace())) {
                placeEntries.add(fli);
            }
        }
        return placeEntries;
    }


    /**
     * Get total amount of hours an airplane has been flying
     * @param registration the registration of the airplane
     * @return total number of hours
     */
    private int getAirplaneFlightHours(String registration) {
        int hours = 0;
        for (Flight flight : getAirplanesEntries(registration)) {
            hours = hours + flight.getNHours();
        }
        return hours;
    }


    /**
     * Get amount of minutes an airplane has been flying - no hours included!
     * @param registration registration of the airplane
     * @return amount of minutes an airplane has been flying
     */
    private int getAirplaneFlightMinutes(String registration) {
        int minutes = 0;
        for (Flight flight : getAirplanesEntries(registration)) {
            minutes = minutes + flight.getNMinutes();
        }
        return minutes;
    }


    /**
     * Method that returns total amount of minutes an airplane has been flying - minutes + hours
     * @param registration the airplanes registration
     * @return the total amount of time an airplane has been flying, in minutes
     */
    int getAirplaneTotalMinutes(String registration){
        int hoursToMinutes = getAirplaneFlightHours(registration) * 60;
        return getAirplaneFlightMinutes(registration) + hoursToMinutes;
    }


    /**
     * Method that returns amount of hours a pilot has been flying - no minutes
     * @param email the pilots email
     * @return the amount of hours
     */
    private int getPilotFlightHours(String email){
        int hours = 0;
        for (Flight flight : getPilotsEntries(email)) {
            hours = hours + flight.getNHours();
        }
        return hours;
    }


    /**
     * Method that returns the amount of minutes a pilot has been flying - no hours included
     * @param email the pilots email
     * @return the amount of minutes
     */
    private int getPilotFlightMinutes(String email){
        int minutes = 0;
        for (Flight flight : getPilotsEntries(email)) {
            minutes = minutes + flight.getNHours();
        }
        return minutes;
    }


    /**
     * Method that returns the amount of time a pilot has been flying - hours and minutes
      * @param email the pilots email
     * @return the amount of time a pilot has been flying, in minutes
     */
    int getPilotTotalMinutes(String email){
        int hoursToMinutes = getPilotFlightHours(email) * 60;
        return hoursToMinutes + getPilotFlightMinutes(email);
    }


    /**
     * Returns the amount of starts a pilot has made.
     * @param pilotEmail the email of the pilot in question
     * @return the number of starts this pilot
     */
    int getPilotNumberOfStarts(String pilotEmail){
        int starts = 0;
        for (Flight flight : getPilotsEntries(pilotEmail)) {
            starts = starts + flight.getNStarts();
        }
        return starts;
    }

    /**
     * Method that is used to get latest entry in logbook.
     * @return the last Flight that was added to list flights
     */
    Flight getLastEntry(){
        return flights.get(flights.size()-1);
    }


    // getters
    List<Flight> getFlights() {
        return flights;
    }

}
