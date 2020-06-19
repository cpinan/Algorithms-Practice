package geometry;

import common.Geometry;
import common.Orientation;
import common.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author Carlos Piñan
 */
public class ConvexHullGrahamScan {

    public void convexHull(final List<Point> points) {
        final int N = points.size();
        if (N < 3)
            return;
        int index = 0;

        // Pick bottom-most point by Y
        // if they are equals. Then choose the one with lowest X
        for (int i = 1; i < N; i++) {
            Point selected = points.get(index);
            Point current = points.get(i);
            if (current.y < selected.y || current.y == selected.y && current.x < selected.x) {
                index = i;
            }
        }

        // Swap to have the chosen one at the beginning
        Point tmp = points.get(0);
        points.set(0, points.get(index));
        points.set(index, tmp);

        // Sort by polar angle and distance
        final Point p0 = points.get(0);
        points.remove(0);
        Collections.sort(points, (o1, o2) -> {
            Orientation orientation = Geometry.orientation(
                    p0, o1, o2
            );
            if (orientation == Orientation.COLLINEAR) {
                return p0.squareDistance(o2) >= p0.squareDistance(o1) ? -1 : 1;
            }
            return orientation == Orientation.COUNTER_CLOCKWISE ? -1 : 1;
        });
        points.add(0, p0);

        System.out.println(points);

        int m = 1;
        for (int i = 1; i < N; i++) {
            while (i < N - 1 &&
                    Geometry.orientation(p0, points.get(i), points.get(i + 1)) == Orientation.COLLINEAR
            ) {
                i++;
            }
            points.set(m, points.get(i));
            m++;
        }
        if (m < 3) {
            System.out.println("CONVEX HULL NOT POSSIBLE");
            return;
        }
        Stack<Point> stack = new Stack<>();
        stack.push(points.get(0));
        stack.push(points.get(1));
        stack.push(points.get(2));

        for (int i = 3; i < m; i++) {
            while (
                    Geometry.orientation(
                            nextTop(stack),
                            stack.peek(),
                            points.get(i)
                    ) != Orientation.COUNTER_CLOCKWISE
            ) {
                stack.pop();
            }
            stack.push(points.get(i));
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

    }

    private Point nextTop(final Stack<Point> stack) {
        Point p1 = stack.pop();
        Point p2 = stack.peek();
        stack.push(p1);
        return p2;
    }

    public static void main(String[] args) {
        ConvexHullGrahamScan m = new ConvexHullGrahamScan();

        final List<Point> list = new ArrayList<>();
        /*
        list.add(Point.create(0F, 3F));
        list.add(Point.create(2F, 3F));
        list.add(Point.create(1F, 1F));
        list.add(Point.create(2F, 1F));
        list.add(Point.create(3F, 0F));
        list.add(Point.create(0F, 0F));
        list.add(Point.create(3F, 3F));
        */

        list.add(Point.create(0F, 3F));
        list.add(Point.create(1F, 1F));
        list.add(Point.create(2F, 2F));
        list.add(Point.create(4F, 4F));
        list.add(Point.create(0F, 0F));
        list.add(Point.create(1F, 2F));
        list.add(Point.create(3F, 1F));
        list.add(Point.create(3F, 3F));

        m.convexHull(list);
    }

}
