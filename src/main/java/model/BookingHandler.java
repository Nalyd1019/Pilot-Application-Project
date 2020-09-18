package model;

import java.util.ArrayList;
import java.util.List;

public class BookingHandler {

    private List<Booking> bookings;

    public BookingHandler(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public BookingHandler() {
        bookings = new ArrayList<>();
    }


    public void createBooking(int startTime, int day, String pilotEmail, String airplaneRegistration, int bookingID) {
        {
            if (timeIsAvailable(day, startTime)) {
                Booking booking = new Booking(startTime, day, pilotEmail, airplaneRegistration, bookingID);
                bookings.add(booking);
            } // TODO - fix else-statement
        }
    }

        public void removeBooking () {
        }

        public List<Booking> getUsersBookings(String pilotEmail){
        List<Booking> usersBookings = new ArrayList<>();
        for(Booking booking : this.bookings) {
            if(pilotEmail.equals(booking.getPilotEmail())){
                usersBookings.add(booking);
            }
        }
        return usersBookings;
        }


        public boolean timeIsAvailable(int day, int startTime) {

            for (Booking booking:bookings){
                if (booking.getDay() != day || booking.getStartTime() != startTime){
                    return true;
                }
            }

            if (bookings.size() == 0){
                return true;
            }
            return false;
        }

    public List<Booking> getBookings() {
        return bookings;
    }
}

