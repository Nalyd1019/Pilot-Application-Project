package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Event {

    private List<Pilot> pilotsAttending = new ArrayList<>();
    private LocalDate date;
    private int startTime;
    private int endTime;
    private String description;

    public Event(LocalDate date, int startTime, int endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    void addAttendingPilot(Pilot p){
        pilotsAttending.add(p);
    }
}
