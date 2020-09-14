package model;

public class Booking {

    private iBookable airplane;
    private iBorrower pilot;

    private int startTime;
    private int weekday;

    public Booking(iBookable bookedPlane, iBorrower currentPilot, int start, int day) {
        startTime = start;
        weekday = day;
    }

}
