
package model;

import org.junit.Test;
import java.time.LocalDate;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;

public class PilotTest {

    @Test
    public void addLicense() {
        Pilot pilot = new Pilot("Kalle",  "Karl", "karl1337@email.com");

        pilot.addLicense("Medical License", LocalDate.of(2020, 10, 1));
        assertTrue(pilot.getLicenses().size() == 1);

    }

    @Test
    public void addLicenses() {
        Pilot pilot = new Pilot("Kalle",  "Karl", "karl1337@email.com");

        pilot.addLicense("Medical License", LocalDate.of(2020, 10, 1));
        pilot.addLicense("Medical License", LocalDate.of(2020, 10, 1));
        pilot.addLicense("Medical License", LocalDate.of(2020, 10, 1));
        assertTrue(pilot.getLicenses().size() == 3);

    }

    @Test
    public void checkLicenseExpirationTest() {
        Pilot pilot = new Pilot("Kalle",  "Karl", "karl1337@email.com");

        pilot.addLicense("Medical License", LocalDate.now().plusDays(10));
        pilot.addLicense("Medical License", LocalDate.now());
        pilot.addLicense("Medical License", LocalDate.now().minusDays(10));

        assertFalse(pilot.getLicenses().get(0).isExpired());
        assertFalse(pilot.getLicenses().get(1).isExpired());
        assertTrue(pilot.getLicenses().get(2).isExpired());

    }

    @Test
    public void checkLicenseSoonExpirationTest() {
        Pilot pilot = new Pilot("Kalle",  "Karl", "karl1337@email.com");

        pilot.addLicense("Medical License", LocalDate.now().plusDays(10));
        pilot.addLicense("Medical License", LocalDate.now());
        pilot.addLicense("Medical License", LocalDate.now().minusDays(10));

        assertFalse(pilot.getLicenses().get(0).isSoonExpired());
        assertTrue(pilot.getLicenses().get(1).isSoonExpired());
        assertFalse(pilot.getLicenses().get(2).isSoonExpired());
    }
}











