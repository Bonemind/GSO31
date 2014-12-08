package fontys.test

import fontys.time.IPeriod
import fontys.time.ITime
import fontys.time.Period
import fontys.time.Time

/**
 * @author Michon
 */
class IPeriodTest extends GroovyTestCase {
    private static void assertEquals(ITime c1, ITime c2) {
        assertEquals(c1.compareTo(c2), 0)
    }

    void testLength() {
        IPeriod pp = new Period(new Time(2014, 8, 8, 12, 10), new Time(2014, 8, 9, 10, 34));
        assertEquals(pp.length(), 1344);
    }

    void testMove() {
        IPeriod pp = new Period(new Time(2014, 8, 8, 12, 10), new Time(2014, 8, 9, 10, 34));
        pp.move(10);
        IPeriod pm = new Period(new Time(2014, 8, 8, 12, 20), new Time(2014, 8, 9, 10, 44));
        assertEquals(pm.getBeginTime(), pp.getBeginTime());
        assertEquals(pm.getEndTime(), pp.getEndTime());
    }

    void testChangeLengthWith() {
        IPeriod pp = new Period(new Time(2014, 8, 8, 12, 10), new Time(2014, 8, 9, 10, 34));
        pp.changeLengthWith(10);
        IPeriod pm = new Period(new Time(2014, 8, 8, 12, 10), new Time(2014, 8, 9, 10, 44));
        assertEquals(pm.getBeginTime(), pp.getBeginTime());
        assertEquals(pm.getEndTime(), pp.getEndTime());
    }

    void testIsPartOf() {
        // Define time periods.
        IPeriod pp = new Period(new Time(2014, 8, 8, 12, 10), new Time(2014, 8, 9, 10, 34));
        IPeriod pa = new Period(new Time(2014, 8, 8, 10, 4), pp.getBeginTime());             // Before and until start of pp.
        IPeriod pb = new Period(new Time(2014, 8, 8, 10, 22), new Time(2014, 8, 8, 14, 45)); // Before and in pp.
        IPeriod pc = new Period(new Time(2014, 8, 8, 16, 34), new Time(2014, 8, 9, 6, 43));  // Completely inside pp.
        IPeriod pd = new Period(new Time(2014, 8, 9, 17, 56), new Time(2014, 8, 10, 5, 45)); // No overlap with pp.

        // Partial overlap at begin.
        assertFalse(pp.isPartOf(pb));

        // Partial overlap at end.
        assertFalse(pb.isPartOf(pp));

        // Consecutive period at begin.
        assertFalse(pp.isPartOf(pa));
        assertFalse(pa.isPartOf(pp));

        // One period fully contained in the other.
        assertFalse(pp.isPartOf(pc));

        // Same, but reversed.
        assertTrue(pc.isPartOf(pp));

        // No overlap.
        assertFalse(pp.isPartOf(pd));
    }

    void testUnionWith() {
        // Define time periods.
        IPeriod pp = new Period(new Time(2014, 8, 8, 12, 10), new Time(2014, 8, 9, 10, 34));
        IPeriod pa = new Period(new Time(2014, 8, 8, 10, 4), pp.getBeginTime());             // Before and until start of pp.
        IPeriod pb = new Period(new Time(2014, 8, 8, 10, 22), new Time(2014, 8, 8, 14, 45)); // Before and in pp.
        IPeriod pc = new Period(new Time(2014, 8, 8, 16, 34), new Time(2014, 8, 9, 6, 43));  // Completely inside pp.
        IPeriod pd = new Period(new Time(2014, 8, 9, 17, 56), new Time(2014, 8, 10, 5, 45)); // No overlap with pp.

        // Partial overlap at begin.
        IPeriod p1 = pp.unionWith(pb);
        assertEquals(p1.getBeginTime(), pb.getBeginTime());
        assertEquals(p1.getEndTime(), pp.getEndTime());

        // Partial overlap at end.
        IPeriod p2 = pb.unionWith(pp);
        assertEquals(p1.getBeginTime(), p2.getBeginTime());
        assertEquals(p1.getEndTime(), p2.getEndTime());

        // Consecutive period at begin.
        IPeriod p3 = pp.unionWith(pa);
        assertEquals(p3.getBeginTime(), pa.getBeginTime());
        assertEquals(p3.getEndTime(), pp.getEndTime());

        // Consecutive period at end.
        IPeriod p4 = pa.unionWith(pp);
        assertEquals(p4.getBeginTime(), pa.getBeginTime());
        assertEquals(p4.getEndTime(), pp.getEndTime());

        // One period fully contained in the other.
        IPeriod p5 = pp.unionWith(pc);
        assertEquals(p5.getBeginTime(), pp.getBeginTime());
        assertEquals(p5.getEndTime(), pp.getEndTime());

        // Same, but reversed.
        Period p6 = pc.unionWith(pp);
        assertEquals(p6.getBeginTime(), pp.getBeginTime());
        assertEquals(p6.getEndTime(), pp.getEndTime());

        // No overlap.
        Period p7 = pp.unionWith(pd);
        assertNull(p7);
    }

    void testIntersectionWith() {
        // Define time periods.
        IPeriod pp = new Period(new Time(2014, 8, 8, 12, 10), new Time(2014, 8, 9, 10, 34));
        IPeriod pa = new Period(new Time(2014, 8, 8, 10, 4), pp.getBeginTime());             // Before and until start of pp.
        IPeriod pb = new Period(new Time(2014, 8, 8, 10, 22), new Time(2014, 8, 8, 14, 45)); // Before and in pp.
        IPeriod pc = new Period(new Time(2014, 8, 8, 16, 34), new Time(2014, 8, 9, 6, 43));  // Completely inside pp.
        IPeriod pd = new Period(new Time(2014, 8, 9, 17, 56), new Time(2014, 8, 10, 5, 45)); // No overlap with pp.

        // Overlap at begin.
        IPeriod p1 = pp.intersectionWith(pb);
        assertEquals(p1.getBeginTime(), pp.getBeginTime());
        assertEquals(p1.getEndTime(), pb.getEndTime());

        // Overlap at end.
        IPeriod p2 = pb.intersectionWith(pp);
        assertEquals(p1.getBeginTime(), p2.getBeginTime());
        assertEquals(p1.getEndTime(), p2.getEndTime());

        // Consecutive period at begin.
        IPeriod p3 = pp.intersectionWith(pa);
        assertNull(p3);

        // Consecutive period at end.
        IPeriod p4 = pa.intersectionWith(pp);
        assertNull(p4);

        // One period fully contained in the other.
        IPeriod p5 = pp.intersectionWith(pc);
        assertEquals(p5.getBeginTime(), pc.getBeginTime());
        assertEquals(p5.getEndTime(), pc.getEndTime());

        // Same, but reversed.
        IPeriod p6 = pc.intersectionWith(pp);
        assertEquals(p6.getBeginTime(), pc.getBeginTime());
        assertEquals(p6.getEndTime(), pc.getEndTime());

        // No overlap.
        IPeriod p7 = pp.intersectionWith(pd);
        assertNull(p7);
    }
}
