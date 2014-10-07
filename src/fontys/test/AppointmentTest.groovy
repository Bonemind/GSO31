package fontys.test

import fontys.time.Appointment
import fontys.time.Contact
import fontys.time.Period
import fontys.time.Time

/**
 * @author Michon
 */
class AppointmentTest extends GroovyTestCase {
    private static void assertLength(Iterator iter, int length) {
        for (int i = 0; i < length; i++) {
            assertTrue(iter.hasNext())
            iter.next()
        }
        assertFalse(iter.hasNext())
    }

    void testInvitees() {
        // Create some objects.
        Appointment a1 = new Appointment("Test 1", new Period(new Time(2014, 8, 8, 12, 10), new Time(2014, 8, 8, 12, 40)));
        Appointment a2 = new Appointment("Test 2", new Period(new Time(2014, 8, 9, 10, 0), new Time(2014, 8, 9, 10, 45))));
        Appointment a3 = new Appointment("Test 3", new Period(new Time(2014, 8, 8, 12, 30), new Time(2014, 8, 8, 13, 15)));
        Contact c1 = new Contact("John");
        Contact c2 = new Contact("Jane");

        // Test addContact.
        assertTrue(a1.addContact(c1));
        assertLength(a1.invitees(), 1);
        assertTrue(a1.addContact(c2));
        assertLength(a1.invitees(), 2);
        assertTrue(a2.addContact(c1));
        assertLength(a2.invitees(), 1);

        // Test failing addContact.
        assertFalse(a3.addContact(c1));
        assertLength(a3.invitees(), 0);

        // Test removeContact.
        a1.removeContact(c1)
        assertLength(a1.invitees(), 1);

        // Re-test failing addContact, should succeed now.
        assertTrue(a3.addContact(c1));
        assertLength(a3.invitees(), 1);
    }
}
