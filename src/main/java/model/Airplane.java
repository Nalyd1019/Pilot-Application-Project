package model;

import java.time.LocalDate;

public class Airplane implements iBookable{

    private Logbook logbook;
    private String registration;

    private LocalDate yearlyCheckDate = LocalDate.of(2010, 10,4);

    private boolean isTimeForYearlyCheckNow;
    private boolean isTimeForYearlyCheckSoon;

    private int nChecks = 0;

    public Airplane(String registration, Logbook logbook){
        this.registration = registration;
        this.logbook = logbook;
    }


    /**
     * Method to see if check is needed soon
     * @return true if flight time is over 10000 minutes
     */
    public boolean isCheckNeededSoon(){
        return getTotalFlightTime() > 10000;
    }

    /**
     * Method to see if check is needed now
     * @return true if flight time is over 15000 minutes
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

        // Only for testing
        // yearlyCheckDate = LocalDate.of(2020,10,7);

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
     * Method that set yearlyCheckNow-boolean to false when yearly check is done.
     */
    public void yearlyCheckIsDone(){

        int nextYear = LocalDate.now().getYear()+1;
        yearlyCheckDate = LocalDate.of(nextYear, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());

        isTimeForYearlyCheckNow = false;
        isTimeForYearlyCheckSoon = false;
    }


    public void distanceCheckIsDone(){
        nChecks++;
    }


    // Getters

    public String getRegistration() {
        return registration;
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

    public Logbook getLogbook() {
        return logbook;
    }


    
    // TODO - Denna metoden bör ej kunna användas, ska den t om tas bort? Endast för test nu
    public void removeLogbookEntries() {
        logbook.clearLogbook();
    }

    // TODO - Denna metoden används för testsyften, tas bort sedan!
    public void setnChecks(int nChecks) {
        this.nChecks = nChecks;
    }


}
