package model;

import org.junit.Assert;
import org.junit.Test;


import java.util.Calendar;
import java.util.GregorianCalendar;

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
		assertTrue(flyingClub.getPilots().get(0).validateLogin("karl1337@email.com","Kalle"));
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

	@Test
	public void logbookTest(){
		Logbook logbook = new Logbook();
		logbook.addLogbookEntry(new GregorianCalendar(2020, Calendar.FEBRUARY,2),2,34,2,"GBG","STHLM", "Regn", "SE-543");
	assertEquals(1, logbook.getFlights().size());
	}

	@Test
	public void airplaneFlightTimeTest(){
		Logbook logbook = new Logbook();
		logbook.addLogbookEntry(new GregorianCalendar(2020, Calendar.FEBRUARY,2),3,34,2,"GBG","STHLM", "Regn", "SE-543");
		logbook.addLogbookEntry(new GregorianCalendar(2020, Calendar.SEPTEMBER,3),1,2,2,"GBG","STHLM", "Regn", "SE-543");
		Airplane airplane = new Airplane("SE-543", logbook);
		assertEquals(276, airplane.getTotalFlightTime());
	}

	@Test
	public void checkNeededSoonTest(){
		Logbook logbook = new Logbook();
		logbook.addLogbookEntry(new GregorianCalendar(2020, Calendar.FEBRUARY,2),0,10001,2,"GBG","STHLM", "Regn", "SE-543");
		Airplane airplane = new Airplane("SE-543", logbook);
		assertTrue(airplane.isCheckNeededSoon());
	}

	@Test
	public void checkNeededNowTest(){
		Logbook logbook = new Logbook();
		logbook.addLogbookEntry(new GregorianCalendar(2020, Calendar.FEBRUARY,2),0,15001,2,"GBG","STHLM", "Regn", "SE-543");
		Airplane airplane = new Airplane("SE-543", logbook);
		assertTrue(airplane.isCheckNeededNow());
	}


}
