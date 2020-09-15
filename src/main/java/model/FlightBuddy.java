package model;

import java.util.ArrayList;
import java.util.List;

public class FlightBuddy {


    private static List<FlyingClub> flyingclubs = new ArrayList<>();
    private static Pilot currentUser;
    private static FlyingClub currentClub;

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

}
