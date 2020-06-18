package geometry;

import common.Point;
import common.Segment;

/**
 * @author Carlos Piñan
 * @url https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/
 * <p>
 * How to check if two given line segments intersect?
 * <p>
 * Given two line segments (p1, q1) and (p2, q2),
 * find if the given line segments intersect with each other.
 * <p>
 * Orientation of an ordered triplet of points in the plane can be:
 * <p>
 * –counterclockwise
 * –clockwise
 * –collinear
 * <p>
 * Two segments (p1,q1) and (p2,q2) intersect if and only if
 * one of the following two conditions is verified
 * <p>
 * General Case
 * <p>
 * – (p1, q1, p2) and (p1, q1, q2) have different orientations and
 * – (p2, q2, p1) and (p2, q2, q1) have different orientations.
 * <p>
 * Special Case
 * <p>
 * – (p1, q1, p2), (p1, q1, q2), (p2, q2, p1), and (p2, q2, q1) are all collinear and
 * – the x-projections of (p1, q1) and (p2, q2) intersect
 * – the y-projections of (p1, q1) and (p2, q2) intersect
 */
public class SegmentsIntersection {

    public boolean intersect(
            final Point p1, final Point q1,
            final Point p2, final Point q2
    ) {
        Segment s1 = Segment.create(p1, q1);
        Segment s2 = Segment.create(p2, q2);
        return s1.intersectWith(s2);
    }

    public static void main(String[] args) {

        SegmentsIntersection m = new SegmentsIntersection();

        Point p1 = Point.create(1F, 1F);
        Point q1 = Point.create(10F, 1F);
        Point p2 = Point.create(1F, 2F);
        Point q2 = Point.create(10F, 2F);

        if (m.intersect(p1, q1, p2, q2)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        p1 = Point.create(10F, 1F);
        q1 = Point.create(0F, 10F);
        p2 = Point.create(0F, 0F);
        q2 = Point.create(10F, 10F);

        if (m.intersect(p1, q1, p2, q2)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        p1 = Point.create(-5F, -5F);
        q1 = Point.create(0F, 0F);
        p2 = Point.create(1F, 1F);
        q2 = Point.create(10F, 10F);

        if (m.intersect(p1, q1, p2, q2)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }
}
