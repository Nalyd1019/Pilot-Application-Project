package model;

import org.junit.Test;

import java.time.LocalDate;

import static junit.framework.TestCase.*;


public class AirplaneTest {

    private Logbook logbook = new Logbook();
    private Airplane airplane = new Airplane("ABC", logbook);


    // Tests for distance check
    @Test
    public void distanceCheck15000min(){
        logbook.addLogbookEntry(LocalDate.of(2020, 3, 2),0,15000,2,"GBG","STHLM", "Regn", "ABC", "test@gmail.com");
        assertFalse(airplane.isCheckNeededNow());
    }

    @Test
    public void distanceCheck15001min(){
        logbook.addLogbookEntry(LocalDate.of(2020, 3, 2),0,15001,2,"GBG","STHLM", "Regn", "ABC", "test@gmail.com");
        assertTrue(airplane.isCheckNeededNow());
    }

    @Test
    public void distanceCheckIsDone(){
        logbook.addLogbookEntry(LocalDate.of(2020, 3, 2),0,15001,2,"GBG","STHLM", "Regn", "ABC", "test@gmail.com");
        airplane.distanceCheckIsDone();
        assertFalse(airplane.isCheckNeededNow());
    }

    @Test
    public void distanceCheck500h (){
        logbook.addLogbookEntry(LocalDate.of(2020, 3, 2),0,15001,2,"GBG","STHLM", "Regn", "ABC", "test@gmail.com");
        airplane.distanceCheckIsDone();
        logbook.addLogbookEntry(LocalDate.of(2020, 3, 2),0,15001,2,"GBG","STHLM", "Regn", "ABC", "test@gmail.com");
        assertTrue(airplane.isCheckNeededNow());
    }



}
