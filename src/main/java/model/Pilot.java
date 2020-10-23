package model;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Albert Lund
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


    /**
     * Constructor for the class
     * @param password1 the desired password for the user
     * @param name name of the pilot
     * @param email email to the pilot
     */
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

    /**
     * Method that returns the total amount of starts the pilot have in their career
     * @return the total amount of starts/takeoffs
     */
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
     * @param type the type of the new license. Can either be FlightBuddy.MEDICALLICENSE or FlightBuddy.FLIGHTLICENSE
     * @param expirationDate the expiration date of the new license
     */
    void addLicense(String type, LocalDate expirationDate){
        License license = new License(type, expirationDate);
        licenses.add(license);
    }

    /**
     * Checks if any of the pilots licenses have or is about to expire
     */
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

    // Setters and getters
    List<License> getLicenses() {
        return licenses;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
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

    void setPassword(String password) {
        this.password = password;
    }

    void setName(String name) {
        this.name = name;
    }

    void setEmail(String email) {
        this.email = email;
    }

    List<Flight> getPilotsEntries(String pilotEmail){
        return logbook.getPilotsEntries(pilotEmail);
    }

    /**
     * Method that returns the last entry of the pilots logbook
     * @return the last entry in the pilots entry
     */
    Flight getLastEntry(){
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

    /**
     * Method that returns a licenses expiration date, either Flight or Medical depending on the parameter type
     * @param type the type of the license which expiration date needs to be retrieved. Can either be
     *             FlightBuddy.MEDICALLICENSE orFlightBuddy.FLIGHTLICENSE
     * @return the expiration date of the specified license
     */
    String getWantedLicenseExpirationDate(String type){
        return Objects.requireNonNull(getWantedLicense(type)).getExpirationDate();
    }

    /**
     * Sets the expiration date of one of the Pilots licenses, either Flight or Medical depending on the parameter type
     * @param date the new expiration date of the specified license
     * @param type the type of the license which expiration date needs to be set. Can either be
     *             FlightBuddy.MEDICALLICENSE or FlightBuddy.FLIGHTLICENSE
     */
    void setLicenseExpirationDate(String date, String type){
        Objects.requireNonNull(getWantedLicense(type)).setExpirationDate(date);
    }

}

