package model;

import java.util.Random;

/**
 * @author Lisa Samuelsson
 * Booking represents a booking made by a borrower, and stores information about this booking.
 */
public final class Booking {

    /**
     * The object that is booked.
     */
    private IBookable bookable;

    /**
     * The one that has booked the object.
     */
    private IBorrower borrower;

    /**
     * An ID that keeps track of the booking.
     */
    private final int bookingID;


    /**
     * The time of day the booking starts.
     */
    private final int startTime;

    /**
     * The day of the week the booking takes place.
     */
    private final int day;


    Booking(int startTime, int day, IBorrower borrower, IBookable bookable) {
        this.startTime = startTime;
        this.day = day;
        this.borrower = borrower;
        this.bookable = bookable;
        this.bookingID = new Random().nextInt();
    }

    // Getters
    public int getDay() {
        return day;
    }

    public int getStartTime() {
        return startTime;
    }

    public IBookable getBookable() {
        return bookable;
    }

    public IBorrower getBorrower() {
        return borrower;
    }

    public int getBookingID() {
       return bookingID;
    }








}
