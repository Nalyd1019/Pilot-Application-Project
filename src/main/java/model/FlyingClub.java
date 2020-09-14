package model;

import java.util.ArrayList;
import java.util.List;

public class FlyingClub {
    private List<Pilot> pilots = new ArrayList<>();
    private BookingHandler bookingHandler;
    private List<Airplane> airplanes = new ArrayList<>();

    private String clubName;

    public FlyingClub (String clubName, BookingHandler bookingHandler){
        this.clubName = clubName;
        this.bookingHandler = bookingHandler;
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
}
