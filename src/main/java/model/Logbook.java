package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Logbook {

    private List<Flight> flights;

    public Logbook(List<Flight> flights) {
        this.flights = flights;
    }

    public Logbook(){
        flights = new ArrayList<>();
    }


    /**
     *
     * @param date the date of the flight
     * @param nHours how many hours the flight lasted
     * @param nMinutes how many minutes the flight lasted
     * @param nStarts the amounts of starts on the flight (??)
     * @param departurePlace the place of departure
     * @param destination the destination of the flight
     * @param comment any comments by the pilot
     * @param airplaneRegistration the registration of the aircraft
     */
    public void addLogbookEntry(GregorianCalendar date, int nHours, int nMinutes, int nStarts, String departurePlace, String destination, String comment, String airplaneRegistration, String pilotEmail) {
        Flight flight = new Flight(date, nHours, nMinutes, nStarts, departurePlace, destination, comment, airplaneRegistration, pilotEmail);
        flights.add(flight);
    }


    public List<Flight> getFlights() {
        return flights;
    }

    /**
     * Method that returns all entries with the specific airplane's registration.
     * @param registration the airplanes registration
     * @return list of entries with that airplane
     */
    public List<Flight> getAirplanesEntries(String registration) {
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
    public List<Flight> getPilotsEntries(String pilotEmail) {
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
    public List<Flight> getDestinationEntries(String destination) {
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
            hours = hours + flight.getnHours();
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
            minutes = minutes + flight.getnMinutes();
        }
        return minutes;
    }

    /**
     * Method that reutrns total amount of minutes an airplane has been flying - minutes + hours
     * @param registration
     * @return the total amount of time an airplane has been flying, in minutes
     */
    public int getAirplaneTotalMinutes(String registration){
        int hoursToMinutes = getAirplaneFlightHours(registration) * 60;
        return getAirplaneFlightMinutes(registration) + hoursToMinutes;
    }

    /**
     * Returns the amount of starts a pilot has made.
     * @param pilotEmail the email of the pilot in question
     * @return the number of starts this pilot
     */
    public int getPilotNumberOfStarts(String pilotEmail){
        int nStarts = 0;
        for (Flight flight : getPilotsEntries(pilotEmail)) {
            nStarts = nStarts + flight.getnStarts();
        }
        return nStarts;
    }

}
