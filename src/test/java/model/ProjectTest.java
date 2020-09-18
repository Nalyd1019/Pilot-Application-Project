package model;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class ProjectTest {
	private static final int NUM_INCREMENTATIONS = 128;

	@Test
	public void addPlaneTest(){
		FlyingClub skovdeMotorFlygklubb = new FlyingClub("Skövde Flygklubb", new BookingHandler());
		skovdeMotorFlygklubb.addPlane(new Airplane("SE_THK", new Logbook()));
		assertTrue(skovdeMotorFlygklubb.getAirplanes().size()==1);
	}

	@Test
	public void addMemberTest(){
		FlyingClub skovdeMotorFlygklubb = new FlyingClub("Skövde Flygklubb", new BookingHandler());
		skovdeMotorFlygklubb.addMember(new Pilot("Kalle","Kalle","Karl","karl1337@email.com"));
		assertTrue(skovdeMotorFlygklubb.getPilots().size()==1);

	}
	@Test
	public void createNewUser(){
	FlightBuddy flightBuddy = FlightBuddy.getInstance();
	FlyingClub flyingClub = new FlyingClub("Borås Flygklubb", new BookingHandler());
	flightBuddy.createPilot("Kalle","Kalle","Karl","karl1337@email.com",flyingClub);
		assertEquals(1, flyingClub.getPilots().size());
	}

	@Test
	public void createBookingTest(){
		BookingHandler bookingHandler = new BookingHandler();

		bookingHandler.createBooking(1, 14, "hej@mail.com", "GHL-01");
		assertTrue(bookingHandler.getBookings().size() == 1);
	}

	@Test
	public void createTwoBookingsTest(){
		BookingHandler bookingHandler = new BookingHandler();

		bookingHandler.createBooking(1, 14, "hej@mail.com", "GHL-01");
		bookingHandler.createBooking(1, 14, "hej@mail.com", "GHL-01");
		assertFalse(bookingHandler.getBookings().size() == 2);
	}

	@Test
	public void createTwoBookingsTest2(){
		BookingHandler bookingHandler = new BookingHandler();

		bookingHandler.createBooking(1, 14, "hej@mail.com", "GHL-01");
		bookingHandler.createBooking(2, 14, "hej@mail.com", "GHL-01");
		assertTrue(bookingHandler.getBookings().size() == 2);
	}

	@Test
	public void getUsersBookingTest(){
		BookingHandler bookingHandler = new BookingHandler();

		bookingHandler.createBooking(1, 14, "malin@mail.com", "GHL-01");
		bookingHandler.createBooking(4, 16, "lisa@mail.com", "GHL-01");
		bookingHandler.createBooking(2, 14, "lisa@mail.com", "GHL-01");

		assertTrue(bookingHandler.getUsersBookings("lisa@mail.com").size() == 2);
	}

	@Test
	public void getAirplanesBookingTest(){
		BookingHandler bookingHandler = new BookingHandler();

		bookingHandler.createBooking(1, 14, "malin@mail.com", "GHL-01");
		bookingHandler.createBooking(4, 16, "lisa@mail.com", "GHL-01");
		bookingHandler.createBooking(2, 14, "lisa@mail.com", "GHL-01");

		assertTrue(bookingHandler.getAirplanesBookings("GHL-01").size() == 3);
	}

	@Test
	public void removeBookingTest(){
		BookingHandler bookingHandler = new BookingHandler();

		bookingHandler.createBooking(1, 14, "malin@mail.com", "GHL-01");
		bookingHandler.createBooking(4, 16, "lisa@mail.com", "GHL-01");
		bookingHandler.createBooking(2, 14, "lisa@mail.com", "GHL-01");

		bookingHandler.removeBooking(1);

		assertEquals(2, bookingHandler.getBookings().size());

	}

	@Test
	public void nameSetTest(){
		Pilot pilot = new Pilot("hej","hej","jo","sen");
		assertTrue(pilot.nameSet());
	}

}
