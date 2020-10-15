package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlyingEvent extends Event {

    List<Airplane> airplanes = new ArrayList<>();

    public FlyingEvent(LocalDate date, int startTime, int endTime) {
        super(date, startTime, endTime);
    }

    
}
