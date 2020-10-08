package model;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static junit.framework.TestCase.*;


public class LogbookTest {

    @Test
    public void logbookTest(){
        Logbook logbook = new Logbook();
        logbook.addLogbookEntry(LocalDate.of(2020, 9, 29),2,34,2,"GBG","STHLM", "Regn", "SE-543", "test@gmail.com");
        assertEquals(1, logbook.getFlights().size());
    }

    @Test
    public void getDestinationTest() {
        Logbook logbook = new Logbook();
        logbook.addLogbookEntry(LocalDate.of(2020, 2, 2),2,34,2,"GBG","STHLM", "Regn", "SE-543", "test@gmail.com");
        logbook.addLogbookEntry(LocalDate.of(2020, 2, 2),2,34,2,"GBG","STHLM", "Regn", "SE-543", "test@gmail.com");
        assertEquals(2, logbook.getDestinationEntries("STHLM").size());
    }

    @Test
    public void airplaneFlightTimeTest(){
        Logbook logbook = new Logbook();
        logbook.addLogbookEntry(LocalDate.of(2020, 2,2),3,34,2,"GBG","STHLM", "Regn", "SE-543", "test@gmail.com");
        logbook.addLogbookEntry(LocalDate.of(2020, 2,3),1,2,2,"GBG","STHLM", "Regn", "SE-543", "test@gmail.com");
        Airplane airplane = new Airplane("SE-543", logbook);
        assertEquals(276, airplane.getTotalFlightTime());
    }

    @Test
    public void checkNeededSoonTest(){
        Logbook logbook = new Logbook();
        logbook.addLogbookEntry(LocalDate.of(2020, 2,2),0,10001,2,"GBG","STHLM", "Regn", "SE-543", "test@gmail.com");
        Airplane airplane = new Airplane("SE-543", logbook);
        assertTrue(airplane.isCheckNeededSoon());
    }

    @Test
    public void checkNeededNowTest(){
        Logbook logbook = new Logbook();
        logbook.addLogbookEntry(LocalDate.of(2020, 2 ,2),0,15001,2,"GBG","STHLM", "Regn", "SE-543", "test@gmail.com");
        Airplane airplane = new Airplane("SE-543", logbook);
        assertTrue(airplane.isCheckNeededNow());
    }

    @Test
    public void getPilotsEntriesTest(){
        Logbook logbook = new Logbook();
        Pilot pilot = new Pilot("a", "a", "bosse", "test@gmail.com");
        logbook.addLogbookEntry(LocalDate.of(2020, 3, 2),2,34,2,"GBG","STHLM", "Regn", "SE-543", "test@gmail.com");
        assertEquals(1, logbook.getPilotsEntries("test@gmail.com").size());
    }

    @Test
    public void getPilotsNStarts() {
        Pilot pilot = new Pilot("a", "a", "bosse", "test@gmail.com");
        pilot.getLogbook().addLogbookEntry(LocalDate.of(2020, 2, 2),2,34,5,"GBG","STHLM", "Regn", "SE-543", "test@gmail.com");
        assertEquals(5, pilot.getTotalNStarts());
    }



}
