package model;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class BookingHandlerTest {

    private BookingHandler bookingHandler = new BookingHandler();

    @Test
    public void createBookingTest(){
        bookingHandler.createBooking(1, 14, "hej@mail.com", "GHL-01");
        assertEquals(1, bookingHandler.getBookings().size());
    }

    @Test
    public void createTwoBookingsSame(){
        bookingHandler.createBooking(1, 14, "hej@mail.com", "GHL-01");
        bookingHandler.createBooking(1, 14, "hej@mail.com", "GHL-01");
        assertFalse(bookingHandler.getBookings().size() == 2);
    }

    @Test
    public void createTwoBookingsNotSame(){
        bookingHandler.createBooking(1, 14, "hej@mail.com", "GHL-01");
        bookingHandler.createBooking(2, 14, "hej@mail.com", "GHL-01");
        assertEquals(2, bookingHandler.getBookings().size());
    }

    @Test
    public void getUsersBookingTest(){
        bookingHandler.createBooking(1, 14, "malin@mail.com", "GHL-01");
        bookingHandler.createBooking(4, 16, "lisa@mail.com", "GHL-01");
        bookingHandler.createBooking(2, 14, "lisa@mail.com", "GHL-01");

        assertEquals(2, bookingHandler.getUsersBookings("lisa@mail.com").size());
    }

    @Test
    public void getAirplanesBookingTest(){
        bookingHandler.createBooking(1, 14, "malin@mail.com", "GHL-01");
        bookingHandler.createBooking(4, 16, "lisa@mail.com", "GHL-01");
        bookingHandler.createBooking(2, 14, "lisa@mail.com", "GHL-01");

        assertEquals(3, bookingHandler.getAirplanesBookings("GHL-01").size());
    }

    @Test
    public void removeBookingTest(){

        bookingHandler.createBooking(1, 14, "malin@mail.com", "GHL-01");
        bookingHandler.createBooking(4, 16, "lisa@mail.com", "GHL-01");
        bookingHandler.createBooking(2, 14, "lisa@mail.com", "GHL-01");

        bookingHandler.removeBooking(1);

        assertEquals(2, bookingHandler.getBookings().size());

    }



}
