package fontys.test

import fontys.time.DayInWeek
import fontys.time.ITime
import fontys.time.Time

/**
 * Created by Subhi on 29-9-2014.
 */
class ITimeTest extends groovy.util.GroovyTestCase {
    void testGetYear() {
        ITime time = new Time(2014, 11, 22, 15, 3);
        assertEquals(2014, time.getYear());
    }

    void testGetMonth() {
        ITime time = new Time(2014, 11, 22, 15, 3);
        assertEquals(11, time.getMonth());
    }

    void testGetDay() {
        ITime time = new Time(2014, 11, 22, 15, 3);
        assertEquals(22, time.getDay());
    }

    void testGetHours() {
        ITime time = new Time(2014, 11, 22, 15, 3);
        assertEquals(15, time.getHours());
    }

    void testGetMinutes() {
        ITime time = new Time(2014, 11, 22, 15, 3);
        assertEquals(3, time.getMinutes());
    }

    void testGetDayInWeek() {
        ITime time = new Time(2014, 9, 29, 15, 3);
        assertEquals(DayInWeek.SUN, time.getDayInWeek());
    }

    void testPlus() {

    }

    void testDifference() {

    }
}
