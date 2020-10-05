package model;

public final class Booking {

    private iBookable airplane;
    private iBorrower pilot;
    private final String pilotEmail;
    private final String airplaneRegistration;
    private final int bookingID;
    private static int  nextID = 0; // TODO - blir detta ett problem för immutability?

    private final String startTime;
    private final int day;

    // TODO - what constructor will take
   /* public Booking(int startTime, int day, iBorrower pilot, iBookable airplane) {
        this.startTime = startTime;
        this.day = day;
        this.pilot = pilot;
        this.airplane = airplane;
    } */


   Booking(String startTime, int day, String pilotEmail, String airplaneRegistration) {
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

    public String getStartTime() {
        return this.startTime;
    }

    String getPilotEmail() { return this.pilotEmail;
    }

    String getAirplaneRegistration() { return this.airplaneRegistration;
    }

    int getBookingID() {
        return this.bookingID;
    }


}
