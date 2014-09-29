package fontys.time;

/**
 * Created by Subhi on 29-9-2014.
 */
public class Time implements ITime {
    /**
     * creation of a Time-object with year y, month m, day d, hours h and
     * minutes m; if the combination of y-m-d refers to a non-existing date
     * a correct value of this Time-object will be not guaranteed
     * @param y
     * @param m 1≤m≤12
     * @param d 1≤d≤31
     * @param h 0≤h≤23
     * @param m 0≤m≤59
     */
    public Time(int y, int m, int d, int h, int min) {
        //TODO: Implement
    }
    /**
     * @return the concerning year of this time
     */
    @Override
    public int getYear() {
        return 0;
    }

    /**
     * @return the number of the month of this time (1..12)
     */
    @Override
    public int getMonth() {
        return 0;
    }

    /**
     * @return the number of the day in the month of this time (1..31)
     */
    @Override
    public int getDay() {
        return 0;
    }

    /**
     * @return the number of hours in a day of this time (0..23)
     */
    @Override
    public int getHours() {
        return 0;
    }

    /**
     * @return the number of minutes in a hour of this time (0..59)
     */
    @Override
    public int getMinutes() {
        return 0;
    }

    /**
     * @return the concerning day in the week of this time
     */
    @Override
    public DayInWeek getDayInWeek() {
        return null;
    }

    /**
     * @param minutes (a negative value is allowed)
     * @return this time plus minutes
     */
    @Override
    public ITime plus(int minutes) {
        return null;
    }

    /**
     * @param time
     * @return the difference between this time and [time] expressed in minutes
     */
    @Override
    public int difference(ITime time) {
        return 0;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p/>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p/>
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p/>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p/>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p/>
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(ITime o) {
        return 0;
    }
}
