package model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class FlightBuddyTest {

    @Test
    public void createNewUser(){
        FlightBuddy flightBuddy = FlightBuddy.getInstance();
        FlyingClub flyingClub = new FlyingClub("Borås Flygklubb", new BookingSystem());
        flightBuddy.createPilot("Kalle","Kalle","Karl","karl1337@email.com",flyingClub);
        assertEquals(1, flyingClub.getPilots().size());
        assertTrue(flyingClub.getPilots().get(0).validateLogin("karl1337@email.com","Kalle"));
    }
}
