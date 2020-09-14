package model;

public class Booking {

    private iBookable airplane;
    private iBorrower pilot;

    private int startTime;
    private int day;

    public Booking(int startTime, int day) {
        this.startTime = startTime;
        this.day = day;
    }

}
