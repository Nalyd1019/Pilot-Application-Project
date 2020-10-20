package model;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class BookingHandlerTest {

    private BookingSystem bookingHandler = new BookingSystem();
    private Airplane airplane1 = new Airplane("airplane1", new Logbook());
    Pilot pilot1 = new Pilot("123",  "pilot1", "pilot1@email.com");
    Pilot pilot2 = new Pilot("123",  "pilot2", "pilot2@email.com");


    @Test
    public void createBookingTest(){
        bookingHandler.createBooking(1, 14, pilot1, airplane1);
        assertEquals(1, bookingHandler.getBookings().size());
    }

    @Test
    public void createTwoBookingsSame(){
        bookingHandler.createBooking(1, 14, pilot1, airplane1);
        bookingHandler.createBooking(1, 14, pilot1, airplane1);
        assertFalse(bookingHandler.getBookings().size() == 2);
    }

    @Test
    public void createTwoBookingsNotSame(){
        bookingHandler.createBooking(1, 14, pilot1, airplane1);
        bookingHandler.createBooking(2, 14,  pilot1, airplane1);
        assertEquals(2, bookingHandler.getBookings().size());
    }

    @Test
    public void getUsersBookingTest(){
        bookingHandler.createBooking(1, 14,  pilot1, airplane1);
        bookingHandler.createBooking(4, 16, pilot2, airplane1);
        bookingHandler.createBooking(2, 14, pilot2, airplane1);

        assertEquals(2, bookingHandler.getUsersBookings("pilot2@email.com").size());
    }

    @Test
    public void getAirplanesBookingTest(){
        bookingHandler.createBooking(1, 14, pilot1, airplane1);
        bookingHandler.createBooking(4, 16, pilot2, airplane1);
        bookingHandler.createBooking(2, 14, pilot2, airplane1);

        assertEquals(3, bookingHandler.getAirplanesBookings("GHL-01").size());
    }

    @Test
    public void removeBookingTest(){

        bookingHandler.createBooking(1, 14, pilot1, airplane1);
        bookingHandler.createBooking(4, 16, pilot2, airplane1);
        bookingHandler.createBooking(2, 14, pilot2, airplane1);

        bookingHandler.removeBooking(1);

        assertEquals(2, bookingHandler.getBookings().size());

    }



}
