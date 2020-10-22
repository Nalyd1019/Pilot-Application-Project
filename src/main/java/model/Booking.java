package model;

/**
 * @author Lisa Samuelsson
 * Booking represents a booking made by a borrower, and stores information about this booking.
 */
public final class Booking {

    /**
     * The object that is booked.
     */
    private iBookable bookable;

    /**
     * The one that has booked the object.
     */
    private iBorrower borrower;

    /**
     * An ID that keeps track of the booking.
     */
    private final int bookingID;

    /**
     * The first booking has ID zero.
     */
    private static int nextID = 0; // TODO - blir detta ett problem för immutability?

    /**
     * The time of day the booking starts.
     */
    private final int startTime;

    /**
     * The day of the week the booking takes place.
     */
    private final int day;


    Booking(int startTime, int day, iBorrower borrower, iBookable bookable) {
        this.startTime = startTime;
        this.day = day;
        this.borrower = borrower;
        this.bookable = bookable;
        nextID++;
        this.bookingID = nextID;
    }

    // Getters
    public int getDay() {
        return day;
    }

    public int getStartTime() {
        return startTime;
    }

    public iBookable getBookable() {
        return bookable;
    }

    public iBorrower getBorrower() {
        return borrower;
    }

    public int getBookingID() {
       return bookingID;
    }








}
