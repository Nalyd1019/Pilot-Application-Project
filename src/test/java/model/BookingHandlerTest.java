package model;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class BookingHandlerTest {

    @Test
    public void createBookingTest(){
        BookingHandler bookingHandler = new BookingHandler();

        bookingHandler.createBooking("13:00", 14, "hej@mail.com", "GHL-01");
        assertTrue(bookingHandler.getBookings().size() == 1);
    }

    @Test
    public void createTwoBookingsTest(){
        BookingHandler bookingHandler = new BookingHandler();

        bookingHandler.createBooking("13:00", 14, "hej@mail.com", "GHL-01");
        bookingHandler.createBooking("13:00", 14, "hej@mail.com", "GHL-01");
        assertFalse(bookingHandler.getBookings().size() == 2);
    }

    @Test
    public void createTwoBookingsTest2(){
        BookingHandler bookingHandler = new BookingHandler();

        bookingHandler.createBooking("13:00", 14, "hej@mail.com", "GHL-01");
        bookingHandler.createBooking("15:00", 14, "hej@mail.com", "GHL-01");
        assertTrue(bookingHandler.getBookings().size() == 2);
    }

    @Test
    public void getUsersBookingTest(){
        BookingHandler bookingHandler = new BookingHandler();

        bookingHandler.createBooking("13:00", 14, "malin@mail.com", "GHL-01");
        bookingHandler.createBooking("17:00", 16, "lisa@mail.com", "GHL-01");
        bookingHandler.createBooking("15:00", 14, "lisa@mail.com", "GHL-01");

        assertTrue(bookingHandler.getUsersBookings("lisa@mail.com").size() == 2);
    }

    @Test
    public void getAirplanesBookingTest(){
        BookingHandler bookingHandler = new BookingHandler();

        bookingHandler.createBooking("13:00", 14, "malin@mail.com", "GHL-01");
        bookingHandler.createBooking("17:00", 16, "lisa@mail.com", "GHL-01");
        bookingHandler.createBooking("15:00", 14, "lisa@mail.com", "GHL-01");

        assertTrue(bookingHandler.getAirplanesBookings("GHL-01").size() == 3);
    }

    @Test
    public void removeBookingTest(){
        BookingHandler bookingHandler = new BookingHandler();

        bookingHandler.createBooking("13:00", 14, "malin@mail.com", "GHL-01");
        bookingHandler.createBooking("17:00", 16, "lisa@mail.com", "GHL-01");
        bookingHandler.createBooking("15:00", 14, "lisa@mail.com", "GHL-01");

        bookingHandler.removeBooking(1);

        assertEquals(2, bookingHandler.getBookings().size());


    }


}
