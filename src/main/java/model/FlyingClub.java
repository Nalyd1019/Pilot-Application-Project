package model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public void addMember(Pilot pilot){
        pilots.add(pilot);
    }

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

    public List<String> getAirplaneReg(){

        List<String> airplaneRegs = new ArrayList<>();

        for (Airplane airplane : airplanes){
            airplaneRegs.add(airplane.getRegistration());
        }
        return airplaneRegs;
    }

    // TODO obs måste refaktoriseras?
    public Airplane getAirplaneFromRegistration(String registration) {
        Airplane airplane = null;

        for(Airplane a : airplanes) {
            if(registration.equals(a.getRegistration())) {
                airplane = a;
            }
        }
        return airplane;
    }

    void addAirplaneLogBookEntry(LocalDate date, int nHours, int nMinutes, int nStarts, String departurePlace, String destination, String comment, String airplaneRegistration, String pilotEmail){
        Airplane airplane = getAirplaneFromRegistration(airplaneRegistration);
        airplane.addLogBookEntry(date,nHours,nMinutes,nStarts,departurePlace,destination,comment,airplaneRegistration,pilotEmail);
    }


    public void createFlyingEventBookings(LocalDate date, int starTime, int endTime, iBorrower borrower, String description, String detailDesc, List<Airplane> airplanes){
        FlyingEvent event = new FlyingEvent(date, starTime, endTime, description,detailDesc, airplanes);
        for (Airplane airlane : event.getAirplanes()){
            for (int timeSlots : event.slotsDuringEvent()){
                bookingHandler.createBooking(timeSlots, date.getDayOfWeek().getValue(), borrower, airlane);

            }
        }

    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Event> getSortedEvents(){
        for (int i = 0; i < events.size()-1; i++){
                if (events.get(i).isLaterThan(events.get(i + 1))) {
                    Collections.swap(events, i, i + 1);
                }

        }
        return events;
    }
}
