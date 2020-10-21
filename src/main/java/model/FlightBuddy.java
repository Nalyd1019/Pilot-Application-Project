package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The facade of the model
 * @Author Albert Lund &
 */
public class FlightBuddy {


    private List<FlyingClub> flyingclubs = new ArrayList<>();
    private Pilot currentUser;
    private  FlyingClub currentClub;
    public static final String MEDICALLICENSE = "Medical License";
    public static final String FLIGHTLICENSE = "Flight License";

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

    /**
     * checks if a user with the provided email exists in the system
     * @param email the email which is to be checked if it exists already
     * @return true if a user with the provided email already exists, otherwise false
     */
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

    /**
     * @param index the position the club have in the list of flyingclubs
     * @return the name of a flyingclub
     */
    public String getFlyingClubName(int index){
        return flyingclubs.get(index).getClubName();
    }

    /**
     * @return the amount of flyingclubs
     */
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

    /**
     * checks if the provided flyingClub and password match
     * @param flyingClubName the name of the flyingClub
     * @param password the provided password
     * @return true if the password match, otherwise false
     */
    public boolean flyingClubMatchingPassword(String flyingClubName, String password){
        for (FlyingClub flyingclub : flyingclubs) {
            if (flyingclub.getClubName().equals(flyingClubName)) {
                currentClub = flyingclub;
                return flyingclub.getPassword().equals(password);
            }
        }
        return false;
    }

    /**
     * adds a member to a flyingclub
     * @param pilot the pilot which is to be added
     */
    public void addMemberToCurrentClub(Pilot pilot){
        currentClub.addMember(pilot);
    }
    public List<FlyingClub> getFlyingclubs() {
        return flyingclubs;
    }
    public List<String> getAirplaneReg(){
        return currentClub.getAirplaneReg();
    }

    /**
     * creates a logbook entry with the provided information for an airplane
     * @param date date of the flight
     * @param nHours how many hours the flight took
     * @param nMinutes amount of minutes the flight took, excluding the full hours
     * @param nStarts the total amount of starts the pilot took during the flight
     * @param departurePlace where the pilot departed from
     * @param destination where the pilot eventually landed
     * @param comment any comment the pilot desires to give about the flight
     * @param airplaneRegistration the registration of the airplane which was used during the flight
     * @param pilotEmail the email of the pilot that flew
     */
    public void addAirplaneLogBookEntry(LocalDate date, int nHours, int nMinutes, int nStarts, String departurePlace, String destination, String comment, String airplaneRegistration, String pilotEmail){
        currentClub.addAirplaneLogBookEntry(date,nHours,nMinutes,nStarts,departurePlace,destination,comment,airplaneRegistration,pilotEmail);
    }
    public List<Flight> getPilotsEntries(){
        return currentUser.getPilotsEntries(currentUser.getEmail());
    }

    /**
     *
     * @return the total amount of starts the pilot has
     */
    public int getPilotNStarts(){
        return currentUser.getTotalNStarts();
    }

    /**
     * @return the total airborne time the pilot has
     */
    public int getPilotFlightTime(){
        return currentUser.getPilotFlightTime();
    }

    public String getPilotName(){
        return currentUser.getName();
    }

    public String getPilotPassword(){
        return currentUser.getPassword();
    }

    /**
     * creates a logbook entry with the provided information for a pilot
     * @param date date of the flight
     * @param nHours how many hours the flight took
     * @param nMinutes amount of minutes the flight took, excluding the full hours
     * @param nStarts the total amount of starts the pilot took during the flight
     * @param departurePlace where the pilot departed from
     * @param destination where the pilot eventually landed
     * @param comment any comment the pilot desires to give about the flight
     * @param airplaneRegistration the registration of the airplane which was used during the flight
     * @param pilotEmail the email of the pilot that flew
     */
    public void createPilotLogbookEntry(LocalDate date, int nHours, int nMinutes, int nStarts, String departurePlace, String destination, String comment, String airplaneRegistration, String pilotEmail ){
        currentUser.createLogbookEntry(date,nHours,nMinutes,nStarts,departurePlace,destination,comment,airplaneRegistration,pilotEmail);
    }

    /**
     * getter for the current pilotemail
     * @return String with the pilots email
     */
    public String getPilotEmail(){
        return currentUser.getEmail();
    }

    /**
     * @return the last entry of the current users LogBook entry
     */
    public Flight getPilotLastEntry(){
        return currentUser.getLastEntry();
    }

    /**
     * @param type either a MEDICALLICENSE or FLIGHTLICENSE
     * @return the expiration date of a desired License
     */
    public String getWantedeLicenseExpirationDate(String type){
        return currentUser.getWantedLicenseExpirationDate(type);
    }

    /**
     * sets the name of the currentuser
     * @param name the desired name
     */
    public void setPilotName(String name){
        currentUser.setName(name);
    }

    /**
     * sets the email of the currentuser
     * @param email the desired email
     */
    public void setPilotEmail(String email){
        currentUser.setEmail(email);
    }

    /**
     * sets the password of the currentuser
     * @param password the desired password
     */
    public void setPilotPassword(String password){
        currentUser.setPassword(password);
    }

    /**
     * sets expiration date of one of the users licenses
     * @param date the date the license expires
     * @param type the type of the license, either MEDICALLICENSE or FLIGHTLICENSE
     */
    public void setPilotLicenseExpirationDate(String date,String type){
        currentUser.setLicenseExpirationDate(date,type);
    }

    /**
     * @return a list of the users licenses
     */
    public List<License> getPilotLicenses(){
        return currentUser.getLicenses();
    }
}
