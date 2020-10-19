package model;

import org.junit.Test;
import static junit.framework.TestCase.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventTest {

    private Event event = new Event(LocalDate.now(), 12, 1, "Test event", "wiho");
    private Pilot pilot = new Pilot("Kalle",  "Karl", "karl1337@email.com");
    private Logbook logbook = new Logbook();
    private List<Airplane> airplanes = List.of(new Airplane("ABC", logbook));
    private FlyingEvent flyingEvent = new FlyingEvent(LocalDate.now(), 12, 14, "Test event", "wiho", airplanes);
    private FlyingEvent flyingEvent2 = new FlyingEvent(LocalDate.now(), 9, 13, "Test event","wiho", airplanes);

    @Test
    public void addAttendingPilotTest(){
        event.addAttendingPilot(pilot);

        assertTrue(event.getPilotsAttending().size()==1);
    }

    @Test
    public void slotDuringEventTest(){
        assertTrue(flyingEvent.slotsDuringEvent().contains(13));
    }


    @Test
    public void slotsDuringEventTest(){
        boolean first = flyingEvent2.slotsDuringEvent().contains(9);
        boolean second = flyingEvent2.slotsDuringEvent().contains(11);
        boolean third = !flyingEvent2.slotsDuringEvent().contains(13);
        assertTrue(first && second && third);
    }



}
