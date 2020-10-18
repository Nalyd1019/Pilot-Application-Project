package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlyingEvent extends Event {

    List<Airplane> airplanes = new ArrayList<>();

    private final List<Integer> bookingsSlots = List.of(7,9,11,13,15,17);

    public FlyingEvent(LocalDate date, int startTime, int endTime, String description, String detailDesc, List<Airplane> airplanes) {
        super(date, startTime, endTime, description, detailDesc);
        this.airplanes = airplanes;
    }

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
