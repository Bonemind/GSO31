package fontys.time;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Michon
 */
public class Contact {
    /**
     * The name of the contact
     */
    private String name;

    /**
     * The list of appointments
     */
    ArrayList<Appointment> appointments = new ArrayList<Appointment>();

    /**
     * Constructor
     * @param name The name of the contact
     */
    public Contact(String name) {
        this.name = name;
    }

    /**
     * Adds an appointment to the appointment list
     * If the appointment overlaps with another appointment it's not added and false is returned
     * @param a The appointment to add
     * @return True if it could be added, false otherwise
     */
    protected boolean addAppointment(Appointment a) {
        IPeriod otherPeriod = a.getPeriod();
        for (Appointment currAppointment : appointments) {
            IPeriod currPeriod = currAppointment.getPeriod();
            if (currPeriod.intersectionWith(otherPeriod) != null) {
                return false;
            }
        }
        appointments.add(a);
        return true;
    }

    /**
     * Removes an appointment from the list
     * @param a The appointment to remove
     */
    protected void removeAppointment(Appointment a) {
        a.removeContact(this);
        appointments.remove(a);
    }

    public Iterator<Appointment> appointments() {
        return appointments.iterator();
    }

}
