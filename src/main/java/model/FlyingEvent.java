package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlyingEvent extends Event {

    List<Airplane> airplanes = new ArrayList<>();

    private final List<Integer> bookingsSlots = List.of(7,9,11,13,15,17);

    public FlyingEvent(LocalDate date, int startTime, int endTime) {
        super(date, startTime, endTime);
    }

    List<Integer> timeSlotsNeeded(){
        List<Integer> slotsDuringEvent = new ArrayList<>();
        for (Integer slot : bookingsSlots){
            if (slot>startTime && slot<endTime)
                slotsDuringEvent.add(slot);
        }
        return slotsDuringEvent;

    }

}
