package model;

import org.junit.Test;

import java.time.LocalDate;

import static junit.framework.TestCase.*;


public class LogbookTest {

    private Logbook logbook = new Logbook();
    private Pilot pilot = new Pilot("a", "a", "bosse", "test@gmail.com");


    @Test
    public void logbookGetFlightsTest(){
        logbook.addLogbookEntry(LocalDate.of(2020, 9, 29),2,34,2,"GBG","STHLM", "Regn", "SE-543", "test@gmail.com");
        assertEquals(1, logbook.getFlights().size());
    }

    @Test
    public void getDestinationTest() {
        logbook.addLogbookEntry(LocalDate.of(2020, 2, 2),2,34,2,"GBG","STHLM", "Regn", "SE-543", "test@gmail.com");
        logbook.addLogbookEntry(LocalDate.of(2020, 2, 2),2,34,2,"GBG","STHLM", "Regn", "SE-543", "test@gmail.com");
        assertEquals(2, logbook.getDestinationEntries("STHLM").size());
    }

    @Test
    public void getPilotsEntriesTest(){
        logbook.addLogbookEntry(LocalDate.of(2020, 3, 2),2,34,2,"GBG","STHLM", "Regn", "SE-543", "test@gmail.com");
        assertEquals(1, logbook.getPilotsEntries("test@gmail.com").size());
    }

    @Test
    public void getPilotsNStarts() {
        pilot.getLogbook().addLogbookEntry(LocalDate.of(2020, 2, 2),2,34,5,"GBG","STHLM", "Regn", "SE-543", "test@gmail.com");
        assertEquals(5, pilot.getTotalNStarts());
    }



}
