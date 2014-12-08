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
        assertEquals(DayInWeek.MON, time.getDayInWeek());
    }

    void testPlusAddition() {
        ITime time = new Time(2014, 9, 29, 15, 3);
        ITime timeAdded = new Time(2014, 9, 29, 16, 10);
        assertEquals(timeAdded, time.plus(67));
    }

    void testPlusSubstraction() {
        ITime time = new Time(2014, 9, 29, 0, 3);
        ITime timeAdded = new Time(2014, 9, 28, 23, 30);
        assertEquals(timeAdded, time.plus(-33));
    }

    void testDifferenceZero() {
        ITime time = new Time(2014, 9, 29, 0, 3);
        ITime timeDiff = new Time(2014, 9, 29, 0, 3);
        assertEquals(0, time.difference(timeDiff));
    }

    void testDifferenceNegative() {
        ITime time = new Time(2014, 9, 29, 0, 3);
        ITime timeDiff = new Time(2014, 9, 29, 12, 3);
        assertEquals(-720, time.difference(timeDiff));
    }

    void testDifferencePositive() {
        ITime time = new Time(2014, 9, 29, 12, 3);
        ITime timeDiff = new Time(2014, 9, 29, 0, 3);
        assertEquals(720, time.difference(timeDiff));
    }
}
