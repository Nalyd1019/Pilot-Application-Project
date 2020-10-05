package model;

import java.util.ArrayList;
import java.util.List;


public class FlightBuddy {


    private List<FlyingClub> flyingclubs = new ArrayList<>();
    private Pilot currentUser;
    private  FlyingClub currentClub;

    /**
     * private constructor in order to make a singleton pattern
     */
    private FlightBuddy(){
        initializeClubs();
    }

    /**
     * class inside of FlightBuddy that holds the single instance of the FlightBuddy class in line with the singleton pattern
     */
    private static class FlightBuddyHolder{
        private static FlightBuddy instance = new FlightBuddy();
    }

    /**
     * Method to get the instance of FlightBuddy
     * @return the single instance of the FlightBuddy class
     */
    public static FlightBuddy getInstance(){
        return FlightBuddyHolder.instance;
    }

    /**
     * method (should) no longer used
     */
    public void initializeClubs(){
        FlyingClub skovdeFlygklubb = new FlyingClub("Skövde Flygklubb", new BookingHandler());
        skovdeFlygklubb.addPlane(new Airplane("SE-UKE", new Logbook()));
        skovdeFlygklubb.addPlane(new Airplane("SE-UDU", new Logbook()));

        FlyingClub falbygdensFlygklubb = new FlyingClub("Falbygdens Flygklubb", new BookingHandler());
        falbygdensFlygklubb.addPlane(new Airplane("SE-UMY", new Logbook()));
        falbygdensFlygklubb.addPlane(new Airplane("SE-UND", new Logbook()));
        falbygdensFlygklubb.addPlane(new Airplane("SE-SKV", new Logbook()));

        FlyingClub borasFlygklubb = new FlyingClub("Borås Flygklubb", new BookingHandler(),"hej");
        borasFlygklubb.addPlane(new Airplane("SE-UYB", new Logbook()));
        borasFlygklubb.addPlane(new Airplane("SE-UMN", new Logbook()));
        createPilot("123", "123", "Test", "test@gmail.com", borasFlygklubb);


        flyingclubs.add(skovdeFlygklubb);
        flyingclubs.add(borasFlygklubb);
        flyingclubs.add(falbygdensFlygklubb);

    }

    /**
     * Method to create a new user/pilot, sets the values given in the parameters to a new pilot instance
     * @param password the desired password the pilot will use when login in to the application
     * @param passwordconfirmation confirming the password, so the user does not mistype it when setting
     * @param name the pilots name
     * @param email the pilots email, will be used when login in
     * @param flyingClub the flyingClub the pilot belongs to
     */
    protected void createPilot(String password, String passwordconfirmation,String name, String email, FlyingClub flyingClub){
        currentClub = flyingClub;
        currentUser = new Pilot(password,passwordconfirmation,name,email);
       // if (currentUser.nameSet())
        currentClub.addMember(currentUser);
    }

    /**
     * Method that checks if there exists a combination of the given username and password when a user tries to login
     * @param email the email the user tries to login with
     * @param password the password the user tries to login with
     * @return true if the given combination of email and password exists, else returns false
     */
    public boolean validateLogIn(String email, String password){
        for (FlyingClub flyingclub : flyingclubs) {
            int n = flyingclub.getPilots().size();
            for (int j = 0; j < n; j++) {
                if (flyingclub.getPilots().get(j).validateLogin(email, password)) {
                    currentClub = flyingclub;
                    currentUser = flyingclub.getPilots().get(j);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * getter for the list of flyingClubs
     * @return the list of all the flyingClubs
     */
    public List<FlyingClub> getFlyingclubs() {
        return flyingclubs;
    }

    /**
     * setter for the list of flyingClubs
     * @param flyingclubs the desired list to set as flyingclubs
     */
    public void setFlyingclubs(List<FlyingClub> flyingclubs) {
        this.flyingclubs = flyingclubs;
    }

    public void setCurrentUser(Pilot currentUser) {
        this.currentUser = currentUser;
    }

    public void setCurrentClub(FlyingClub currentClub) {
        this.currentClub = currentClub;
    }
}
