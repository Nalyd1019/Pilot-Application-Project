package model;

public class Booking {

    private iBookable airplane;
    private iBorrower pilot;
    private String pilotEmail;
    private String airplaneRegistration;

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
   }

    public int getDay() {
        return day;
    }

    public int getStartTime() {
        return startTime;
    }
}
