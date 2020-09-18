package model;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BookingHandler {

    private List<Booking> bookings;

    public BookingHandler(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public BookingHandler() {
        bookings = new ArrayList<>();
    }


    public Booking getBooking(int id) throws NoSuchElementException {
        for (Booking booking:bookings){
            if (booking.getBookingID() == id){
                return booking;
            }
        }

        throw new NoSuchElementException("Vi hittar tyvärr ingen bokning med detta ID");
    }


    public void createBooking(int startTime, int day, String pilotEmail, String airplaneRegistration) {
        {
            if (timeIsAvailable(day, startTime)) {
                Booking booking = new Booking(startTime, day, pilotEmail, airplaneRegistration);
                bookings.add(booking);
            } // TODO - fix else-statement
        }
    }

        public void removeBooking(int id) {
            Booking booking = getBooking(id);
            bookings.remove(booking);
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


        public List<Booking> getAirplanesBookings(String registration){
        List<Booking> airplanesBookings = new ArrayList<>();
        for(Booking booking : this.bookings) {
            if(registration.equals(booking.getAirplaneRegistration())){
                airplanesBookings.add(booking);
            }
        }
        return airplanesBookings;
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

