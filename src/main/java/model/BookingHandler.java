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
            if (timeIsAvailable()) {
                Booking booking = new Booking(startTime, day, pilotEmail, airplaneRegistration);
                bookings.add(booking);
            } // TODO - fix else-statement
        }
    }

        public void removeBooking () {
        }


        // TODO - fix this method
        public boolean timeIsAvailable() {
            // if another booking on the same day (in list bookings) starts at the same time, the slot is booked, return false
            return true;
        }
}

