package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent an Event that can be held in a club.
 */

public class Event {

    private List<Pilot> pilotsAttending = new ArrayList<>();
    private LocalDate date;
    protected int startTime;
    protected int endTime;
    private String description;
    private String detailedDesc;

    /**
     *Constructor for an Event.
     * @param date date of the event.
     * @param startTime time that the event starts.
     * @param endTime time that the event ends.
     * @param description a general description of the event.
     * @param detailedDesc a detailed description of the event.
     */
    public Event(LocalDate date, int startTime, int endTime, String description, String detailedDesc) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.detailedDesc = detailedDesc;
    }

    /**
     * Adds a pilot to the event if the pilot is not already attending.
     * @param p The pilot to add.
     */
    public void addAttendingPilot(Pilot p){
        if (!pilotsAttending.contains(p))
            pilotsAttending.add(p);
    }

    /**
     * Helper method to check if the event is later than another event.
     * @param event the event to compare to.
     * @return true if this event is later than the parameter.
     */
    boolean isLaterThan(Event event){
        if (this.getDate().isAfter(event.getDate()))
            return true;
        return false;
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
