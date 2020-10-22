package model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Dylan Osolian &
 * This class represents a flying club
 */
public class FlyingClub{
    private List<Pilot> pilots = new ArrayList<>();
    private BookingSystem bookingHandler;
    private List<Airplane> airplanes = new ArrayList<>();
    private String password;
    private String clubName;
    private List<Event> events = new ArrayList<>();

    public FlyingClub (String clubName, BookingSystem bookingHandler){
        this.clubName = clubName;
        this.bookingHandler = bookingHandler;
    }

    /**
     * Constructor to create a FlyingClub
     * @param clubName the name of the FlyingClub
     * @param bookingHandler the bookinghandler the FLyingClub will use
     * @param password the password required in order to create an account with the FlyingClub
     */
    public FlyingClub(String clubName, BookingSystem bookingHandler, String password){
        this.clubName = clubName;
        this.bookingHandler = bookingHandler;
        this.password = password;
    }

    public static FlyingClub copy(FlyingClub flyingClub1){
        return new FlyingClub(flyingClub1.getClubName(),flyingClub1.getBookingHandler(),flyingClub1.getPassword());
    }

    /**
     * Adds a pilot to the flying club
     * @param pilot pilot to add
     */
    public void addMember(Pilot pilot){
        pilots.add(pilot);
    }

    /**
     * Adds an airplane to the flying clubs fleet.
     * @param airplane the airplane to add
     */
    public void addPlane(Airplane airplane){
        airplanes.add(airplane);
    }

    public List<Airplane> getAirplanes() {
        return airplanes;
    }

    public List<Pilot> getPilots() {
        return pilots;
    }

    public String getClubName() {
        return clubName;
    }

    public String getPassword() {
        return password;
    }

    public BookingSystem getBookingHandler() {
        return bookingHandler;
    }

    /**
     * Returns a list containing the registration of all of the clubs airplanes.
     * @return
     */
    public List<String> getAirplaneReg(){

        List<String> airplaneRegs = new ArrayList<>();

        for (Airplane airplane : airplanes){
            airplaneRegs.add(airplane.getRegistration());
        }
        return airplaneRegs;
    }

    /**
     * This method returns the airplane with a certain registration.
     * If no airplane with this registration exists, the method returns a "dummy" airplane with empty strings.
     * The method is used with caution, only when fixed values can be entered.
     * @param registration The registration of the desired airplane
     * @return The airplane with same registration as the one that the method is called with.
     */
     public Airplane getAirplaneFromRegistration(String registration) {
        Airplane airplane = new Airplane("", new Logbook());

        for(Airplane a : airplanes) {
            // this statement should always be true wherever the method is used
            if(registration.equals(a.getRegistration())) {
                airplane = a;
            }
        }
        return airplane;
    }

    /**
     * Creates a logbok entry for an airplane containing useful information
     * @param date date of flight
     * @param nHours amount of hours the flight was
     * @param nMinutes amount of minutes the flight was
     * @param nStarts amount of starts that were done during the flight
     * @param departurePlace where the flight started
     * @param destination where the flight ended
     * @param comment comment on the flight
     * @param airplaneRegistration registration of the airplane used for the flight
     * @param pilotEmail email of the pilot performing the flight
     */
    void addAirplaneLogBookEntry(LocalDate date, int nHours, int nMinutes, int nStarts, String departurePlace, String destination, String comment, String airplaneRegistration, String pilotEmail){
        Airplane airplane = getAirplaneFromRegistration(airplaneRegistration);
        airplane.addLogBookEntry(date,nHours,nMinutes,nStarts,departurePlace,destination,comment,airplaneRegistration,pilotEmail);
    }

    /**
     * Method to create a FlyingEvent, method creates the event and also books the airplanes that will be used during the event.
     * (Method currently not implemented to GUI)
     */
    public void createFlyingEventBookings(LocalDate date, int starTime, int endTime, iBorrower borrower, String description, String detailDesc, List<Airplane> airplanes){
        FlyingEvent event = new FlyingEvent(date, starTime, endTime, description,detailDesc, airplanes);
        for (Airplane airlane : event.getAirplanes()){
            for (int timeSlots : event.slotsDuringEvent()){
                bookingHandler.createBooking(timeSlots, date.getDayOfWeek().getValue(), borrower, airlane);

            }
        }

    }

    List<Event> getEvents() {
        return events;
    }

    /**
     * Returns a list of events sorted after date
     * @return the list of Events
     */
    public List<Event> getSortedEvents(){
        for (int i = 0; i < events.size()-1; i++){
                if (events.get(i).isLaterThan(events.get(i + 1))) {
                    Collections.swap(events, i, i + 1);
                }

        }
        return events;
    }
}
