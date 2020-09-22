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
     * @param place // TODO - vad är detta, flygplatsen?
     * @param destination the destination of the flight
     * @param comment any comments by the pilot
     * @param airplaneRegistration the registration of the aircraft
     */
    public void addLogbookEntry(Date date, int nHours, int nMinutes, int nStarts, String place, String destination, String comment, String airplaneRegistration) {
        Flight flight = new Flight(date, nHours, nMinutes, nStarts, place, destination, comment, airplaneRegistration);
        flights.add(flight);
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



}
