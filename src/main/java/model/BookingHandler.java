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


    public void createBooking(int startTime, int day, iBorrower pilot, iBookable airplane) {
        Booking booking = new Booking(startTime, day, pilot, airplane);
        bookings.add(booking);
    }

}

