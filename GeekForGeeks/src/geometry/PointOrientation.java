package geometry;

import common.Geometry;
import common.Orientation;
import common.Point;

/**
 * @author Carlos Piñan
 */
public class PointOrientation {

    public void orientation(
            final Point p1,
            final Point p2,
            final Point p3
    ) {
        System.out.println(p1 + "\t" + p2 + "\t" + p3);
        Orientation orientation = Geometry.orientation(p1, p2, p3);
        System.out.println(orientation.toString());
        System.out.println();
    }

    public static void main(String[] args) {
        PointOrientation m = new PointOrientation();

        m.orientation(
                Point.create(0F, 0F),
                Point.create(4F, 4F),
                Point.create(1F, 2F)
        );

        m.orientation(
                Point.create(0F, 0F),
                Point.create(4F, 4F),
                Point.create(1F, 1F)
        );

    }
}
