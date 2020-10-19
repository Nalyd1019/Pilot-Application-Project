package model;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class FlyingClubTest {

    @Test
    public void addPlaneTest(){
        FlyingClub skovdeMotorFlygklubb = new FlyingClub("Skövde Flygklubb", new BookingHandler());
        skovdeMotorFlygklubb.addPlane(new Airplane("SE_THK", new Logbook()));
        assertTrue(skovdeMotorFlygklubb.getAirplanes().size()==1);
    }

    @Test
    public void addMemberTest(){
        FlyingClub skovdeMotorFlygklubb = new FlyingClub("Skövde Flygklubb", new BookingHandler());
        skovdeMotorFlygklubb.addMember(new Pilot("Kalle","Karl","karl1337@email.com"));
        assertTrue(skovdeMotorFlygklubb.getPilots().size()==1);

    }
}
