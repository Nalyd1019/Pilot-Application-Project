package model;

import java.time.LocalDate;

public class Airplane implements iBookable{

    /**
     * The airplane has a logbook in which all flights are logged.
     */
    private Logbook logbook;


    /**
     * The airplane has a registration, like a name.
     */
    private String registration;


    // Obs, ändrat yearlycheckdate i json-filen för att testa
    /**
     * The date of the yearly check, changes yearly.
     */
    private LocalDate yearlyCheckDate;


    /**
     * Is set to true if a year has gone since the last yearly check.
     */
    private boolean isTimeForYearlyCheckNow;

    /**
     *  Is set to true if it is less than one week to yearly check.
     */
    private boolean isTimeForYearlyCheckSoon;


    /**
     * This int keeps track of how many time the airplane has had a Distance check.
     */
    private int nChecks = 0;

    public Airplane(String registration, Logbook logbook){
        this.registration = registration;
        this.logbook = logbook;
        this.yearlyCheckDate = LocalDate.of(2020,10,4);
    }

    /**
     * Method to see if check is needed soon
     * @return true if flight time is over 10000 minutes
     */
    public boolean isCheckNeededSoon(){
        return getTotalFlightTime() - nChecks * 13000 > 13000;
    }


    /**
     * Controls if distance check is needed, which is 250 hours after the last check.
     * @return true if flight time is 250 since last check.
     */
    public boolean isCheckNeededNow(){
        return getTotalFlightTime() - nChecks * 15000 > 15000;
    }


    /**
     * Method that gives total flight time for a specific airplane
     * @return total flight time in minutes
     */
    public int getTotalFlightTime(){
        return logbook.getAirplaneTotalMinutes(registration);
    }


    /**
     * Method that checks if it is time for a yearly check soon or now. Date 1 week before yearlyCheckDate => check soon, after => check now.
     */
    public void inspectYearlyCheck(){
        LocalDate todaysDate = LocalDate.now();

        if(todaysDate.plusDays(8).isAfter(yearlyCheckDate)) {
            isTimeForYearlyCheckNow = false;
            isTimeForYearlyCheckSoon = true;
        }
        if(todaysDate.isAfter(yearlyCheckDate)) {
            isTimeForYearlyCheckSoon = false;
            isTimeForYearlyCheckNow = true;
        }
    }


    /**
     * Method that sets yearly check date to one year from date method is called
     */
    public void yearlyCheckIsDone(){
        int nextYear = LocalDate.now().getYear()+1;
        yearlyCheckDate = LocalDate.of(nextYear, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
    }


    /**
     * Keeps track of amount of checks in order to calculate when 250 has gone
     */
    public void distanceCheckIsDone(){
        nChecks++;
    }

    /**
     * Adds an entry to the logbook.
     * @param date The date the flight took place.
     * @param nHours Amount of hours the flight lasted.
     * @param nMinutes Amount of minutes the flight lasted.
     * @param nStarts How many starts the flight had.
     * @param departurePlace The place of departure.
     * @param destination The destination of the flight.
     * @param comment Any comment made by the pilot.
     * @param airplaneRegistration The airplanes registration.
     * @param pilotEmail The email of the pilot.
     */
    void addLogBookEntry(LocalDate date, int nHours, int nMinutes, int nStarts, String departurePlace, String destination, String comment, String airplaneRegistration, String pilotEmail){
        logbook.addLogbookEntry(date,nHours,nMinutes,nStarts,departurePlace,destination,comment,airplaneRegistration,pilotEmail);
    }


    // Getters
    public String getRegistration() {
        return registration;
    }

    public String setRegistration(String registration){
        return this.registration = registration;
    }

    public boolean isTimeForYearlyCheckNow() {
        return isTimeForYearlyCheckNow;
    }

    public boolean isTimeForYearlyCheckSoon() {
        return isTimeForYearlyCheckSoon;
    }

    public LocalDate getYearlyCheckDate() {
        return yearlyCheckDate;
    }

    public int getnChecks() {
        return nChecks;
    }

    public Logbook getLogbook() { return logbook; }


    // TODO - Denna metoden bör ej användas, ska den t om tas bort? Endast för test nu
    public void removeLogbookEntries() {
        logbook.clearLogbook();
    }

    // TODO - Denna metoden används för testsyften, tas bort sedan!
    public void setnChecks(int nChecks) {
        this.nChecks = nChecks;
    }


}
