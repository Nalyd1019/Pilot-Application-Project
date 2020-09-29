package model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Airplane implements iBookable{

    private Logbook logbook;
    private String registration;

    public Airplane(String registration, Logbook logbook){
        this.registration = registration;
        this.logbook = logbook;
    }


    //FLYGTIMMAR 250 H = 15000 MINUTER
    // ÅRSTILLSYN - HALVÅRSTILLSYN: två olika rutiner
    // Hur checkar man av att man har gjort tillsynen
    // Tillsyn var 500de timma


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
        return getTotalFlightTime() > 15000;
    }

    /**
     * Method that gives total flight time for a specific airplane
     * @return total flight time in minutes
     */
    public int getTotalFlightTime(){
        return logbook.getAirplaneTotalMinutes(registration);
    }



    public boolean isYearlyCheckNeeded() {
        int currentYear = LocalDate.now().getYear();
        LocalDate todaysDate = LocalDate.now();
        LocalDate yearlyCheckDate = LocalDate.of(currentYear, 10, 1);
        return (todaysDate.isEqual(yearlyCheckDate));
    }


    // TODO - java doc + testing
    public boolean isYearlyCheckSoon(){
        int currentYear = LocalDate.now().getYear();
        LocalDate yearlyCheckDate = LocalDate.of(currentYear, 10, 1);
        return(LocalDate.now().plusDays(8).isAfter(yearlyCheckDate));
    }

    /*
    public boolean isSixMonthCheckNeeded() {
    }*/

}
