package geometry;

import common.Geometry;
import common.Orientation;
import common.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carlos Piñan
 * @source https://www.geeksforgeeks.org/convex-hull-set-1-jarviss-algorithm-or-wrapping/?ref=lbp
 * <p>
 * Given a set of points in the plane.
 * The convex hull of the set is the smallest convex
 * polygon that contains all the points of it.
 */
public class ConvexHullJarvisAlg {

    public void convexHull(final List<Point> points) {
        final int N = points.size();
        if (N < 3)
            return;

        // Get most left point.
        int index = 0;
        for (int i = 1; i < N; i++) {
            Point current = points.get(i);
            if (current.x < points.get(index).x) {
                index = i;
            }
        }

        List<Point> listConvexHull = new ArrayList<>();
        int p = index, q;
        do {
            listConvexHull.add(points.get(p));
            q = (p + 1) % N;
            for (int i = 0; i < N; i++) {
                // If i is more counterclockwise than
                // current q, then update q
                if (Geometry.orientation(
                        points.get(p),
                        points.get(i),
                        points.get(q)
                ) == Orientation.COUNTER_CLOCKWISE) {
                    q = i;
                }
            }
            p = q;
        } while (p != index);

        System.out.println(listConvexHull);

    }

    public static void main(String[] args) {
        ConvexHullJarvisAlg m = new ConvexHullJarvisAlg();

        final List<Point> list = new ArrayList<>();
        list.add(Point.create(0F, 3F));
        list.add(Point.create(2F, 3F));
        list.add(Point.create(1F, 1F));
        list.add(Point.create(2F, 1F));
        list.add(Point.create(3F, 0F));
        list.add(Point.create(0F, 0F));
        list.add(Point.create(3F, 3F));

        m.convexHull(list);

    }

}
