package fontys.time;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * @author Michon
 */
public class Appointment {
    /**
     * The subject of the appointment.
     */
    private String subject;

    /**
     * The period of the appointment.
     */
    private IPeriod period;

    /**
     * The invited contacts.
     */
    private Collection<Contact> invitees = new ArrayList<Contact>();

    /**
     * Create a new appointment.
     *
     * @param subject The subject
     * @param period The period
     */
    public Appointment(String subject, IPeriod period) {
        this.subject = subject;
        this.period = period;
    }

    /**
     * Get the subject of the appointment.
     *
     * @return The subject of the appointment
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Get the period of the appointment.
     *
     * @return The period of the appointment
     */
    public IPeriod getPeriod() {
        return period;
    }

    /**
     * Get the invited contacts.
     *
     * @return The invited contacts
     */
    public Iterator<Contact> invitees() {
        return invitees.iterator();
    }

    /**
     * Add a contact.
     *
     * This will fail if this creates a clash in the schedule of the contact.
     *
     * @param c The contact to add
     * @return Whether it succeeded
     */
    public boolean addContact(Contact c) {
        if (!c.addAppointment(this)) {
            return false;
        }

        invitees.add(c);
        return true;
    }

    /**
     * Remove a contact.
     *
     * @param c The contact to remove
     */
    public void removeContact(Contact c) {
        invitees.remove(c);
        c.removeAppointment(this);
    }
}
