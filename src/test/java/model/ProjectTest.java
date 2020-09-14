package model;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

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
		skovdeMotorFlygklubb.addMember(new Pilot());
		assertTrue(skovdeMotorFlygklubb.getPilots().size()==1);

	}
}
