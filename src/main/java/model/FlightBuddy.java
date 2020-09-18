package model;

import java.util.ArrayList;
import java.util.List;

public class FlightBuddy {


    private List<FlyingClub> flyingclubs = new ArrayList<>();
    private Pilot currentUser;
    private  FlyingClub currentClub;

    private FlightBuddy(){
        initializeClubs();
    }

    private static class FlightBuddyHolder{
        private static FlightBuddy instance = new FlightBuddy();
    }

    public static FlightBuddy getInstance(){
        return FlightBuddyHolder.instance;
    }

    private void initializeClubs(){
        FlyingClub skovdeFlygklubb = new FlyingClub("Skövde Flygklubb", new BookingHandler());
        skovdeFlygklubb.addPlane(new Airplane("SE-UKE", new Logbook()));
        skovdeFlygklubb.addPlane(new Airplane("SE-UDU", new Logbook()));

        FlyingClub falbygdensFlygklubb = new FlyingClub("Falbygdens Flygklubb", new BookingHandler());
        falbygdensFlygklubb.addPlane(new Airplane("SE-UMY", new Logbook()));
        falbygdensFlygklubb.addPlane(new Airplane("SE-UND", new Logbook()));
        falbygdensFlygklubb.addPlane(new Airplane("SE-SKV", new Logbook()));

        FlyingClub borasFlygklubb = new FlyingClub("Borås Flygklubb", new BookingHandler());
        borasFlygklubb.addPlane(new Airplane("SE-UYB", new Logbook()));
        borasFlygklubb.addPlane(new Airplane("SE-UMN", new Logbook()));

        flyingclubs.add(skovdeFlygklubb);
        flyingclubs.add(borasFlygklubb);
        flyingclubs.add(falbygdensFlygklubb);

        createPilot("pw123", "pw123", "Daniel Johansson", "dan.joh@gmail.com", borasFlygklubb);
        createPilot("jagjag123", "jagjag123", "Dylan Osolian", "apaapa@gmail.com", skovdeFlygklubb);

    }

    protected void createPilot(String password, String passwordconfirmation,String name, String email, FlyingClub flyingClub){
        currentClub = flyingClub;
        Pilot p = new Pilot(password,passwordconfirmation,name,email);
        currentUser = p;
       // if (currentUser.nameSet())
        currentClub.addMember(currentUser);
    }

    public boolean validateLogIn(String email, String password){
        for (FlyingClub flyingclub : flyingclubs) {
            int n = flyingclub.getPilots().size();
            for (int j = 0; j < n; j++) {
                if (flyingclub.getPilots().get(j).validateLogin(email, password)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<FlyingClub> getFlyingclubs() {
        return flyingclubs;
    }

    public void setFlyingclubs(List<FlyingClub> flyingclubs) {
        this.flyingclubs = flyingclubs;
    }
}
