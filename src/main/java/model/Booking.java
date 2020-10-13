package model;

public final class Booking {

    private iBookable bookable;
    private iBorrower borrower;
    // private final String pilotEmail;
    // private final String airplaneRegistration;
    private final int bookingID;
    private static int  nextID = 0; // TODO - blir detta ett problem för immutability?

    private final int startTime;
    private final int day;

    public Booking(int startTime, int day, iBorrower borrower, iBookable bookable) {
        this.startTime = startTime;
        this.day = day;
        this.borrower = borrower;
        this.bookable = bookable;
        this.bookingID = nextID;
        nextID++;
    }
/*
   Booking(int startTime, int day, String pilotEmail, String airplaneRegistration) {
       this.startTime = startTime;
       this.day = day;
       this.pilotEmail = pilotEmail;
       this.airplaneRegistration = airplaneRegistration;
       this.bookingID = nextID;
       nextID++;
   }


 */
    int getDay() {
        return day;
    }

    int getStartTime() {
        return startTime;
    }

    /*
    String getPilotEmail() { return pilotEmail;
    }


    public String getAirplaneRegistration() { return airplaneRegistration;
    }
     */

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
