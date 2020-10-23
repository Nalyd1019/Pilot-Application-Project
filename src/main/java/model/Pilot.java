package model;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author
 * Pilot represents a pilot, which is someone able to book items. 
 */
public class Pilot implements IBorrower {

    /**
     * The pilot has a logbook with flights.
     */
    private Logbook logbook = new Logbook();

    /**
     * Licenses needed to be able to fly.
     */
    private List<License> licenses = new ArrayList<>();

    /**
     * A password needed to log in.
     */
    private String password;

    /**
     * The name of the pilot.
     */
    private String name;

    /**
     * The pilots email, used to log in.
     */
    private String email;

    /**
     * The amount of hours the pilot had flown.
     */
    private int startHours;

    /**
     * The amount of starts the pilot had made.
     */
    private int nStarts;



    Pilot(String password1, String name, String email) {
            password = password1;
            this.name = name;
            this.email = email;
    }

    /**
     * Tests if the instance variable "name" is set
     * @return true if it is set, else return false
     */
    protected boolean nameSet(){
        return name!=null;
    }

    int getTotalNStarts(){
        return logbook.getPilotNumberOfStarts(email) + nStarts;
    }


    /**
     * Method that creates a entry in the logbook
     * @param date date of the flight
     * @param nHours hours the flight lasted
     * @param nMinutes minutes the flight lasted
     * @param nStarts amounts of start in the flight
     * @param departurePlace the departure place of the flight
     * @param destination the flights destination
     * @param comment any comments made by the pilot
     * @param airplaneRegistration the registration of the airplane
     * @param pilotEmail the pilots email
     */
    void createLogbookEntry(LocalDate date, int nHours, int nMinutes, int nStarts, String departurePlace, String destination, String comment, String airplaneRegistration, String pilotEmail ){
        logbook.addLogbookEntry(date, nHours, nMinutes, nStarts, departurePlace, destination, comment, airplaneRegistration, pilotEmail);
    }

    /**
     * Method that returns the total amount of flight time the user has in minutes
     * @return the total flight time
     */
    int getPilotFlightTime(){
        int startHoursToMinutes = startHours * 60;
        return logbook.getPilotTotalMinutes(email) + startHoursToMinutes;
    }

    /**
     * Adds a license to the the users list of licenses
     * @param name
     * @param expirationDate
     */
    void addLicense(String name, LocalDate expirationDate){
        License license = new License(name, expirationDate);
        licenses.add(license);
    }

    public void checkLicenseExpiration(){
        List<License> licenses = new ArrayList<>();

        for (License license : licenses){
            if (license.isSoonExpired()){
                System.out.println("Den här licensen kommer snart gå ut");
            } else if (license.isExpired()){
                System.out.println("Den här licensen har gått ut");
            } else {
                System.out.println("Den här licensen går inte ur snart");
            }
        }
    }

    List<License> getLicenses() {
        return licenses;
    }


    // Setters and getters
    public String getEmail() {
        return email;
    }

    void setStartHours(int startHours) {
        this.startHours = startHours;
    }

    void setnStarts(int nStarts) {
        this.nStarts = nStarts;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    void setPassword(String password) {
        this.password = password;
    }

    void setName(String name) {
        this.name = name;
    }

    void setEmail(String email) {
        this.email = email;
    }

    List<model.Flight> getPilotsEntries(String pilotEmail){
        return logbook.getPilotsEntries(pilotEmail);
    }

    model.Flight getLastEntry(){
        return logbook.getLastEntry();
    }
    private License getWantedLicense(String type){
        for (License licens : licenses) {
            if (licens.getLicenseName().equals(type)) {
                return licens;
            }
        }
        return null;
    }
    String getWantedLicenseExpirationDate(String type){
        return Objects.requireNonNull(getWantedLicense(type)).getExpirationDate();
    }

    void setLicenseExpirationDate(String date, String type){
        Objects.requireNonNull(getWantedLicense(type)).setExpirationDate(date);
    }

}

