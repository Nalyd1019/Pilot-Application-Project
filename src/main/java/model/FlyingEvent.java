package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class for an event that includes some kinf of flying activity.
 */
public class FlyingEvent extends Event {

    List<Airplane> airplanes = new ArrayList<>();

    private final List<Integer> bookingsSlots = List.of(7,9,11,13,15,17);

    /**
     * Constructor for a FlyingEvent.
     * @param date see super class
     * @param startTime see super class
     * @param endTime see super class
     * @param description see super class
     * @param detailDesc see super class
     * @param airplanes list of airplanes that will be available during the event.
     */
    public FlyingEvent(LocalDate date, int startTime, int endTime, String description, String detailDesc, List<Airplane> airplanes) {
        super(date, startTime, endTime, description, detailDesc);
        this.airplanes = airplanes;
    }

    /**
     * A method to check what booking slots are during this event. Can be used to automatically create bookings for airplanes when creating a FlyingEvent
     * @return list of start times of the timeslots that are during this event.
     */
    protected List<Integer> slotsDuringEvent(){
        List<Integer> slotsDuringEvent = new ArrayList<>();
        for (Integer slot : bookingsSlots){
            if (slot>=startTime && slot<endTime)
                slotsDuringEvent.add(slot);
        }
        return slotsDuringEvent;
    }

     List<Airplane> getAirplanes() {
        return airplanes;
    }
}
