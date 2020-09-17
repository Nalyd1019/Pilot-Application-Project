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


    public void createBooking(int startTime, int day, String pilotEmail, String airplaneRegistration) {
        {
            if (timeIsAvailable(day, startTime)) {
                Booking booking = new Booking(startTime, day, pilotEmail, airplaneRegistration);
                bookings.add(booking);
            } // TODO - fix else-statement
        }
    }

        public void removeBooking () {
        }


        // TODO - fix this method
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

