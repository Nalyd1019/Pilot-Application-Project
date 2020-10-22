package model;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class FlyingClubTest {

    @Test
    public void addPlaneTest(){
        FlyingClub skovdeMotorFlygklubb = new FlyingClub("Skövde Flygklubb", new BookingSystem());
        skovdeMotorFlygklubb.addPlane(new Airplane("SE_THK", new Logbook()));
        assertTrue(skovdeMotorFlygklubb.getAirplanes().size()==1);
    }

    @Test
    public void addMemberTest(){
        FlyingClub skovdeMotorFlygklubb = new FlyingClub("Skövde Flygklubb", new BookingSystem());
        skovdeMotorFlygklubb.addMember(new Pilot("Kalle","Karl","karl1337@email.com"));
        assertTrue(skovdeMotorFlygklubb.getPilots().size()==1);

    }

    @Test
    public void getAirplaneFromRegistrationTest(){
        FlyingClub skovdeMotorFlygklubb = new FlyingClub("Skövde Flygklubb", new BookingSystem());
        skovdeMotorFlygklubb.addPlane(new Airplane("SE-THK", new Logbook()));
        assertSame(skovdeMotorFlygklubb.getAirplaneFromRegistration("SE-THK"), skovdeMotorFlygklubb.getAirplanes().get(0));
    }

    @Test
    public void getAirplaneFromRegistrationWrongTest(){
        FlyingClub skovdeMotorFlygklubb = new FlyingClub("Skövde Flygklubb", new BookingSystem());
        skovdeMotorFlygklubb.addPlane(new Airplane("SE-THK", new Logbook()));
        assertNotSame(skovdeMotorFlygklubb.getAirplaneFromRegistration("SE-thk"), skovdeMotorFlygklubb.getAirplanes().get(0));
    }

}
