package fontys.test

import fontys.time.IPeriod
import fontys.time.Period
import fontys.time.Time

/**
 * @author Michon
 */
class IPeriodTest extends groovy.util.GroovyTestCase {
    void testLength() {
        IPeriod pp = new Period(new Time(2014, 8, 8, 12, 10), new Time(2014, 8, 9, 10, 34));
        assertEquals(pp.length(), 1344);
    }

    void testMove() {
        IPeriod pp = new Period(new Time(2014, 8, 8, 12, 10), new Time(2014, 8, 9, 10, 34));
        IPeriod moved = pp.move(10);
        IPeriod pm = new Period(new Time(2014, 8, 8, 12, 20), new Time(2014, 8, 9, 10, 44));
        assertEquals(pm, moved);
    }

    void testChangeLengthWith() {
        IPeriod pp = new Period(new Time(2014, 8, 8, 12, 10), new Time(2014, 8, 9, 10, 34));
        IPeriod moved = pp.changeLengthWith(10);
        IPeriod pm = new Period(new Time(2014, 8, 8, 12, 10), new Time(2014, 8, 9, 10, 44));
        assertEquals(pm, moved);
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

        // Consecutive period at end.
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
        assertEquals(p1, p2);

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
        assertEquals(p5, pp);

        // Same, but reversed.
        Period p6 = pc.unionWith(pp);
        assertEquals(p6, pp);

        // No overlap.
        Period p7 = pp.unionWith(pd);
        assertEquals(p7, null);
    }

    void testIntersectionWith() {
        // Define time periods.
        IPeriod pp = new Period(new Time(2014, 8, 8, 12, 10), new Time(2014, 8, 9, 10, 34));
        IPeriod pb = new Period(new Time(2014, 8, 8, 10, 22), new Time(2014, 8, 8, 14, 45)); // Before and in pp.
        IPeriod pc = new Period(new Time(2014, 8, 8, 16, 34), new Time(2014, 8, 9, 6, 43));  // Completely inside pp.
        IPeriod pd = new Period(new Time(2014, 8, 9, 17, 56), new Time(2014, 8, 10, 5, 45)); // No overlap with pp.

        // Overlap at begin.
        IPeriod p1 = pp.intersectionWith(pb);
        assertEquals(p1.getBeginTime(), pp.getBeginTime());
        assertEquals(p1.getEndTime(), pb.getEndTime());

        // Overlap at end.
        IPeriod p2 = pb.intersectionWith(pp);
        assertEquals(p1, p2);

        // One period fully contained in the other.
        IPeriod p3 = pp.intersectionWith(pc);
        assertEquals(p3, pc);

        // Same, but reversed.
        IPeriod p4 = pc.intersectionWith(pp);
        assertEquals(p3, pc);

        // No overlap.
        IPeriod p5 = pp.intersectionWith(pd);
        assertEquals(p5, null);
    }
}
