package model;

import java.time.LocalDate;
import java.util.List;

public class Airplane implements iBookable{

    private Logbook logbook;
    private String registration;

    private boolean thisYearsCheckDone = false;
    private boolean isTimeForYearlyCheckNow = false;
    private boolean isTimeForYearlyCheckSoon = false;

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
        int currentYear = LocalDate.now().getYear();
        LocalDate todaysDate = LocalDate.now();
        LocalDate yearlyCheckDate = LocalDate.of(currentYear, 10,3);

        if(todaysDate.isAfter(yearlyCheckDate)) {
            isTimeForYearlyCheckSoon = false;
            isTimeForYearlyCheckNow = true;
        } else if(LocalDate.now().plusDays(8).isAfter(yearlyCheckDate)) {
            isTimeForYearlyCheckNow = false;
            isTimeForYearlyCheckSoon = true;
            thisYearsCheckDone = false;
        }
    }

    /**
     * Method that set yearlyCheckNow-boolean to false when yearly check is done.
     */
    public void yearlyCheckIsDone(){
        thisYearsCheckDone = true;
        isTimeForYearlyCheckNow = false;
        isTimeForYearlyCheckSoon = false;
    }


    public void distanceCheckIsDone(){
        nChecks++;
    }

    public String getRegistration() {
        return registration;
    }

    public boolean isTimeForYearlyCheckNow() {
        return isTimeForYearlyCheckNow;
    }

    public boolean isTimeForYearlyCheckSoon() {
        return isTimeForYearlyCheckSoon;
    }

    public boolean isThisYearsCheckDone() {
        return thisYearsCheckDone;
    }

    public void setnChecks(int nChecks) {
        this.nChecks = nChecks;
    }

    public int getnChecks() {
        return nChecks;
    }


}
