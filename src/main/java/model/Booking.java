package model;

public final class Booking {

    private iBookable airplane;
    private iBorrower pilot;
    private final String pilotEmail;
    private final String airplaneRegistration;
    private final int bookingID;
    private static int  nextID = 0; // TODO - blir detta ett problem för immutability?

    private final int startTime;
    private final int day;

    // TODO - what constructor will take
   /* public Booking(int startTime, int day, iBorrower pilot, iBookable airplane) {
        this.startTime = startTime;
        this.day = day;
        this.pilot = pilot;
        this.airplane = airplane;
    } */


   Booking(int startTime, int day, String pilotEmail, String airplaneRegistration) {
       this.startTime = startTime;
       this.day = day;
       this.pilotEmail = pilotEmail;
       this.airplaneRegistration = airplaneRegistration;
       this.bookingID = nextID;
       nextID++;
   }

    int getDay() {
        return day;
    }

    int getStartTime() {
        return startTime;
    }

    String getPilotEmail() { return pilotEmail;
    }

    String getAirplaneRegistration() { return airplaneRegistration;
    }

    int getBookingID() {
        return bookingID;
    }


}
