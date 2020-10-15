package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Event {

    private List<Pilot> pilotsAttending = new ArrayList<>();
    private LocalDate date;
    protected int startTime;
    protected int endTime;
    private String description;

    public Event(LocalDate date, int startTime, int endTime, String description) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    void addAttendingPilot(Pilot p){
        pilotsAttending.add(p);
    }

    public List<Pilot> getPilotsAttending() {
        return pilotsAttending;
    }
}
