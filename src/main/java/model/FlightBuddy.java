package model;

import java.time.LocalDate;
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
        FlyingClub skovdeFlygklubb = new FlyingClub("Skövde Flygklubb", new BookingSystem());
        Airplane seUKE = new Airplane("SE-UKE", new Logbook());
        skovdeFlygklubb.addPlane(seUKE);

        skovdeFlygklubb.addPlane(new Airplane("SE-UDU", new Logbook()));

        FlyingClub falbygdensFlygklubb = new FlyingClub("Falbygdens Flygklubb", new BookingSystem());
        falbygdensFlygklubb.addPlane(new Airplane("SE-UMY", new Logbook()));
        falbygdensFlygklubb.addPlane(new Airplane("SE-UND", new Logbook()));
        falbygdensFlygklubb.addPlane(new Airplane("SE-SKV", new Logbook()));

        FlyingClub borasFlygklubb = new FlyingClub("Borås Flygklubb", new BookingSystem(),"hej");
        borasFlygklubb.addPlane(new Airplane("SE-UYB", new Logbook()));
        borasFlygklubb.addPlane(new Airplane("SE-UMN", new Logbook()));

        skovdeFlygklubb.getEvents().add(new Event(LocalDate.now().plusDays(1), 10,18,"Segelflygets dag", "En rolig dag"));
        skovdeFlygklubb.getEvents().add(new Event(LocalDate.now(), 10,19,"Prova pa dag", "En rolig dag"));

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
        currentUser = new Pilot(password,name,email);
       // if (currentUser.nameSet())
        currentClub.addMember(currentUser);
    }


    public boolean userExists(String email){
        for (FlyingClub flyingclub : flyingclubs) {
            int n = flyingclub.getPilots().size();
            for (int j = 0; j < n; j++) {
                if (flyingclub.getPilots().get(j).getEmail().equals(email)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Pilot getUser(String userEmail){
        for (FlyingClub flyingclub : flyingclubs) {
            int n = flyingclub.getPilots().size();
            for (int j = 0; j < n; j++) {
                if (flyingclub.getPilots().get(j).getEmail().equals(userEmail)) {
                    return flyingclub.getPilots().get(j);
                }
            }
        }
        return null;
    }

    public FlyingClub getUserClub(String userEmail){
        for (FlyingClub flyingclub : flyingclubs) {
            int n = flyingclub.getPilots().size();
            for (int j = 0; j < n; j++) {
                if (flyingclub.getPilots().get(j).getEmail().equals(userEmail)) {
                    return flyingclub;
                }
            }
        }
        return null;
    }

    public String getFlyingClubName(int index){
        return flyingclubs.get(index).getClubName();
    }

    public int getNFlyingClubs() {
        return flyingclubs.size();
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

    public Pilot getCurrentUser() {
        return currentUser;
    }

    public void setCurrentClub(FlyingClub currentClub){
        this.currentClub = currentClub;
    }

    public FlyingClub getCurrentClub() {
        return currentClub;
    }
    //inte bra metod
    public boolean flyingClubMatchingPassword(String flyingClubName, String password){
        for (FlyingClub flyingclub : flyingclubs) {
            if (flyingclub.getClubName().equals(flyingClubName)) {
                currentClub = flyingclub;
                return flyingclub.getPassword().equals(password);
            }
        }
        return false;
    }
    public void addMemberToCurrentClub(Pilot pilot){
        currentClub.addMember(pilot);
    }
    //försök till defensive copying
    /*
    public List<FlyingClub> getFlyingclubs() {
        List<FlyingClub> copy = new ArrayList<>();
        for (FlyingClub flyingclub : flyingclubs) {
            copy.add(FlyingClub.copy(flyingclub));
        }
        return copy;
    }

     */
    public List<FlyingClub> getFlyingclubs() {
        return flyingclubs;
    }
    public List<String> getAirplaneReg(){
        return currentClub.getAirplaneReg();
    }
    public void addAirplaneLogBookEntry(LocalDate date, int nHours, int nMinutes, int nStarts, String departurePlace, String destination, String comment, String airplaneRegistration, String pilotEmail){
        currentClub.addAirplaneLogBookEntry(date,nHours,nMinutes,nStarts,departurePlace,destination,comment,airplaneRegistration,pilotEmail);
    }
    public List<Flight> getPilotsEntries(){
        return currentUser.getPilotsEntries(currentUser.getEmail());
    }
    public int getPilotNStarts(){
        return currentUser.getTotalNStarts();
    }
    public int getPilotFlightTime(){
        return currentUser.getPilotFlightTime();
    }
    public void createPilotLogbookEntry(LocalDate date, int nHours, int nMinutes, int nStarts, String departurePlace, String destination, String comment, String airplaneRegistration, String pilotEmail ){
        currentUser.createLogbookEntry(date,nHours,nMinutes,nStarts,departurePlace,destination,comment,airplaneRegistration,pilotEmail);
    }
    public String getPilotEmail(){
        return currentUser.getEmail();
    }
    public Flight getPilotLastEntry(){
        return currentUser.getLastEntry();
    }
}
