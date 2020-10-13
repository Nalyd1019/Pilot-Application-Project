package model;

import java.util.ArrayList;
import java.util.List;

public class FlyingClub {
    private List<Pilot> pilots = new ArrayList<>();
    private BookingHandler bookingHandler;
    private List<Airplane> airplanes = new ArrayList<>();
    private String password;
    private String clubName;

    public FlyingClub (String clubName, BookingHandler bookingHandler){
        this.clubName = clubName;
        this.bookingHandler = bookingHandler;
    }

    /**
     * Constructor to create a FlyingClub
     * @param clubName the name of the FlyingClub
     * @param bookingHandler the bookinghandler the FLyingClub will use
     * @param password the password required in order to create an account with the FlyingClub
     */
    public FlyingClub(String clubName, BookingHandler bookingHandler, String password){
        this.clubName = clubName;
        this.bookingHandler = bookingHandler;
        this.password = password;
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

    public BookingHandler getBookingHandler() {
        return bookingHandler;
    }

    public List<String> getAirplaneReg(){

        List<String> airplaneRegs = new ArrayList<>();

        for (Airplane airplane : airplanes){
            airplaneRegs.add(airplane.getRegistration());
        }
        return airplaneRegs;
    }
}
