package common;

/**
 * @author Carlos Piñan
 */
public class Segment {
    Point p;
    Point q;

    public Segment(Point p, Point q) {
        this.p = p;
        this.q = q;
    }

    public boolean intersectWith(Segment other) {
        return Geometry.doIntersect(this, other);
    }

    public static Segment create(Point p, Point q) {
        return new Segment(p, q);
    }

}