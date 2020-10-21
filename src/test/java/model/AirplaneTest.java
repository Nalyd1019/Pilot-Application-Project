package model;

import org.junit.Test;

import java.time.LocalDate;

import static junit.framework.TestCase.*;


public class AirplaneTest {

    private Logbook logbook = new Logbook();
    private Airplane airplane = new Airplane("ABC", logbook);

    @Test
    public void airplaneFlightTimeTest(){
        logbook.addLogbookEntry(LocalDate.of(2020, 2,2),3,34,2,"GBG","STHLM", "Regn", "SE-543", "test@gmail.com");
        logbook.addLogbookEntry(LocalDate.of(2020, 2,3),1,2,2,"GBG","STHLM", "Regn", "SE-543", "test@gmail.com");
        Airplane airplane = new Airplane("SE-543", logbook);
        assertEquals(276, airplane.getTotalFlightTime());
    }

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

    @Test
    public void isCheckNeededTest(){
        assertTrue(!airplane.isCheckNeededSoon());
    }

    @Test
    public void yearlyCheckTest1(){
        assertTrue(!airplane.isTimeForYearlyCheckNow());
    }

    @Test
    public void yearlyCheckTest2(){
        airplane.inspectYearlyCheck();
        assertTrue(airplane.isTimeForYearlyCheckNow());
    }



}
