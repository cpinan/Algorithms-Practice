package common;

/**
 * @author Carlos Piñan
 */
public class Geometry {

    /**
     * Given a point and a segment.
     * <p>
     * Returns true if the point is in the segment
     *
     * @param p
     * @param s
     * @return
     */
    public static boolean onSegment(
            Point p,
            Segment s
    ) {
        return onSegment(p, s.p, s.q);
    }

    public static boolean onSegment(
            Point p,
            Point p1,
            Point q1
    ) {
        return p.x <= Math.max(p1.x, q1.x) &&
                p.x >= Math.min(p1.x, q1.x) &&
                p.y <= Math.max(p1.y, q1.y) &&
                p.y >= Math.min(p1.y, q1.y);
    }

    /**
     * To find orientation of ordered triplet (p, q, r).
     * The function returns following values
     * 0 --> p, q and r are colinear
     * 1 --> Clockwise
     * 2 --> Counterclockwise
     * <p>
     * <p>
     * Slope of line segment (p1, p2): σ = (y2 - y1)/(x2 - x1)
     * Slope of line segment (p2, p3): τ = (y3 - y2)/(x3 - x2)
     * <p>
     * If  σ > τ, the orientation is clockwise (right turn)
     * <p>
     * Using above values of σ and τ, we can conclude that,
     * the orientation depends on sign of  below expression:
     * <p>
     * (y2 - y1)*(x3 - x2) - (y3 - y2)*(x2 - x1)
     * <p>
     * Above expression is negative when σ < τ, i.e.,  counterclockwise
     *
     * @param p
     * @param q
     * @param r
     */


    public static Orientation orientation(
            Point p,
            Point q,
            Point r
    ) {
        float value = (q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y);
        if (value == 0)
            return Orientation.COLLINEAR;
        return (value > 0F) ? Orientation.CLOCKWISE : Orientation.COUNTER_CLOCKWISE;
    }

    /**
     * The main function that returns true if line segment 'p1q1'
     * and 'p2q2' intersect.
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean doIntersect(Segment s1, Segment s2) {
        return doIntersect(
                s1.p,
                s1.q,
                s2.p,
                s2.q
        );
    }

    public static boolean doIntersect(
            Point p1,
            Point q1,
            Point p2,
            Point q2
    ) {


        // Find the four orientations needed for general and
        // special cases
        Orientation o1 = orientation(p1, q1, p2);
        Orientation o2 = orientation(p1, q1, q2);
        Orientation o3 = orientation(p2, q2, p1);
        Orientation o4 = orientation(p2, q1, q1);

        // General case
        if (o1 != o2 && o3 != o4) {
            return true;
        }

        // Special Cases

        // p1, q1 and p2 are collinear and p2 lies on segment p1q1
        if (o1 == Orientation.COLLINEAR && onSegment(p2, p1, q1))
            return true;

        // p1, q1 and q2 are collinear and q2 lies on segment p1q1
        if (o2 == Orientation.COLLINEAR && onSegment(q2, p1, q1))
            return true;

        // p2, q2 and p1 are collinear and p1 lies on segment p2q2
        if (o3 == Orientation.COLLINEAR && onSegment(p1, p2, q2))
            return true;

        // p2, q2 and q1 are collinear and q1 lies on segment p2q2
        if (o4 == Orientation.COLLINEAR && onSegment(q1, p2, q2))
            return true;

        return false;
    }

}
