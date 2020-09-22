package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Logbook {
    private List<Flight> flights;

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
    public void addLogbookEntry(Date date, int nHours, int nMinutes, int nStarts, String departurePlace, String destination, String comment, String airplaneRegistration) {
        Flight flight = new Flight(date, nHours, nMinutes, nStarts, departurePlace, destination, comment, airplaneRegistration);
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




}
