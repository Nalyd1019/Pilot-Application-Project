package model;

import java.time.LocalDate;

public class Airplane implements iBookable{

    private Logbook logbook;
    private String registration;

    // Obs, ändrat yearlycheckdate i json-filen för att testa
    private LocalDate yearlyCheckDate;

    private boolean isTimeForYearlyCheckNow;
    private boolean isTimeForYearlyCheckSoon;

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
