package model;

public class Booking {

    private iBookable airplane;
    private iBorrower pilot;
    private String pilotEmail;
    private String airplaneRegistration;


    private int startTime;
    private int day;

    // TODO - vad konstruktorn ska ta som input för pilot/flygplan
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


}
