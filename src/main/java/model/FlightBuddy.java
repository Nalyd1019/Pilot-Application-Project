package model;

import java.util.ArrayList;
import java.util.List;

public class FlightBuddy {


    private List<FlyingClub> flyingclubs = new ArrayList<>();
    private Pilot currentUser;
    private FlyingClub currentClub;

    public void initializeClubs(){
        FlyingClub skovdeFlygklubb = new FlyingClub("Skövde Flygklubb", new BookingHandler());
        skovdeFlygklubb.addPlane(new Airplane("SE-UKE", new Logbook()));
        skovdeFlygklubb.addPlane(new Airplane("SE-UDU", new Logbook()));

        FlyingClub borasFlygklubb = new FlyingClub("Borås Flygklubb", new BookingHandler());
        borasFlygklubb.addPlane(new Airplane("SE-UMY", new Logbook()));
        borasFlygklubb.addPlane(new Airplane("SE-UND", new Logbook()));
        borasFlygklubb.addPlane(new Airplane("SE-SKV", new Logbook()));

        FlyingClub falbygdensFlygklubb= new FlyingClub("Falbygdens Flygklubb", new BookingHandler());
        falbygdensFlygklubb.addPlane(new Airplane("SE-UYB", new Logbook()));
        falbygdensFlygklubb.addPlane(new Airplane("SE-UMN", new Logbook()));

        flyingclubs.add(skovdeFlygklubb);
        flyingclubs.add(borasFlygklubb);
        flyingclubs.add(falbygdensFlygklubb);

    }

    protected void createPilot(String password, String passwordconfirmation,String name, String email, FlyingClub flyingClub){
        currentClub = flyingClub;
        currentUser = new Pilot(password,passwordconfirmation,name,email);
        if (currentUser.nameSet())
        currentClub.addMember(currentUser);
    }

    private boolean logIn(String email, String password){
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

}
