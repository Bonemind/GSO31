package fontys.test

import fontys.time.Appointment
import fontys.time.Contact
import fontys.time.IPeriod
import fontys.time.ITime
import fontys.time.Period
import fontys.time.Time

/**
 * Created by Subhi on 7-10-2014.
 */
class ContactTest extends GroovyTestCase {
    void testContact() {
        Contact c = new Contact("John Doe");
        //Make sure the initial size = 0
        assertEquals(c.appointments().size(), 0);

        ITime t1 = new Time(2014, 8, 8, 12, 00);
        ITime t2 = new Time(2014, 8, 8, 12, 30);
        ITime t3 = new Time(2014, 8, 8, 12, 29);
        ITime t4 = new Time(2014, 8, 8, 12, 45);

        //Add a new appointment
        IPeriod p1 = new Period(t1, t2);
        Appointment appointment = new Appointment("Lorem Ipsum", p1);
        appointment.addContact(c);
        assertEquals(c.appointments().size(), 1);

        //Test adding an overlapping period
        IPeriod p2 = new Period(t3, t4);
        Appointment appointment2 = new Appointment("Lorem Ipsum", p2);
        appointment2.addContact(c);
        //Appointment overlaps so it should not be added
        assertEquals(c.appointments().size(), 1);

        //Remove a contact from an appointment
        appointment.removeContact(c);
        assertEquals(c.appointments().size(), 0);

    }
}
