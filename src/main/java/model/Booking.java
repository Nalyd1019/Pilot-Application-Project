package model;

public class Booking {

    private iBookable airplane;
    private iBorrower pilot;
    private String pilotEmail;
    private String airplaneRegistration;
    private int bookingID;
    private static int  nextID = 0;

    private int startTime;
    private int day;

    // pilot.getMail TODO - now getEmail returns void, change to String

    // TODO - what constructor will take
   /* public Booking(int startTime, int day, iBorrower pilot, iBookable airplane) {
        this.startTime = startTime;
        this.day = day;
        this.pilot = pilot;
        this.airplane = airplane;
    } */


   public Booking(int startTime, int day, String pilotEmail, String airplaneRegistration) {
       this.startTime = startTime;
       this.day = day;
       this.pilotEmail = pilotEmail;
       this.airplaneRegistration = airplaneRegistration;
       this.bookingID = nextID;
       nextID++;
   }

    public int getDay() {
        return day;
    }

    public int getStartTime() {
        return startTime;
    }

    public String getPilotEmail() { return pilotEmail;
    }

    public String getAirplaneRegistration() { return airplaneRegistration;
    }

    public int getBookingID() {
        return bookingID;
    }
}
