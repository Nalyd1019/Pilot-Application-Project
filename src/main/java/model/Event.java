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
    private String detailedDesc;

    public Event(LocalDate date, int startTime, int endTime, String description, String detailedDesc) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.detailedDesc = detailedDesc;
    }

    public void addAttendingPilot(Pilot p){
        pilotsAttending.add(p);
    }

    public List<Pilot> getPilotsAttending() {
        return pilotsAttending;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public String getDescription() {
        return description;
    }

    public String getDetailedDesc() {
        return detailedDesc;
    }
}
