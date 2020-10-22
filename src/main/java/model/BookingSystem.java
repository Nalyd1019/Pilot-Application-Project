package model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Lisa Samuelsson & Malin Rosén
 * BookingSystem has a list with all bookings, and it is also responsible for creating new bookings.
 * When creating a booking, BookingSystem also makes sure that the time chosen is available.
 */

public class BookingSystem {

    /**
     * A list that contains all bookings.
     */
    private List<Booking> bookings;

    BookingSystem() {
        bookings = new ArrayList<>();
    }

    /**
     * Method that returns a booking based on its ID
     * @param id the id of the booking
     * @return a booking
     * @throws NoSuchElementException if there is no booking with the requested id
     */
    private Booking getBooking(int id) throws NoSuchElementException {
        for (Booking booking:bookings){
            if (booking.getBookingID() == id){
                return booking;
            }
        }

        throw new NoSuchElementException("Vi hittar tyvärr ingen bokning med detta ID");
    }


    /**
     * Method that creates a booking and adds it to the list with all bookings.
     * @param startTime the time the booking starts.
     * @param day the day of the booked time, 1 is monday and 7 is sunday.
     * @param borrower the one that makes a booking
     * @param bookable the item that is booked
     */
    public void createBooking(int startTime, int day, iBorrower borrower, iBookable bookable) {
        {
            if (timeIsAvailable(day, startTime)) {
                Booking booking = new Booking(startTime, day, borrower, bookable);
                bookings.add(booking);
            }
        }
    }


    /**
     * A method that takes the id of a booking and removes that booking from the list of bookings
     * @param id The id of the booking that is to be removed
     */
    void removeBooking(int id) {
            Booking booking = getBooking(id);
            bookings.remove(booking);
        }


    /**
     * Method that returns a list of all bookings a certain pilot has made
     * @param pilotEmail the pilot's email
     * @return ist of the bookings that pilot has made
     */
    public List<Booking> getUsersBookings(String pilotEmail){
        List<Booking> usersBookings = new ArrayList<>();
        for(Booking booking : this.bookings) {
            if(pilotEmail.equals(booking.getBorrower().getEmail())){
                usersBookings.add(booking);
            }
        }
        return usersBookings;
    }

    /**
     * Method that returns a list of all bookings made on an airplane
     * @param registration the airplane's registration
     * @return list of the bookings of that airplane
     */
    List<Booking> getAirplanesBookings(String registration) {
        List<Booking> airplanesBookings = new ArrayList<>();
        for (Booking booking : this.bookings) {
            if(registration.equals(booking.getBookable().getRegistration())) {
                airplanesBookings.add(booking);
            }
        }
        return bookings;
    }


    /**
     * Method that checks if a certain time is already booked
     * @param day the day of the week of the booking
     * @param startTime the time that the booking will start
     * @return true if the times is available, meaning it is not booked
     */
    private boolean timeIsAvailable(int day, int startTime) {

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

    /**
     * Method that returns all bookings made
     * @return list of all bookings made
     */
    public List<Booking> getBookings() {
        return bookings;
    }
}

