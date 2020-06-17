/**
 * Created by cpinan on 15/06/17.
 */
public class StopWatch {

    private static final double SECOND_IN_MILLIS = 1000.0;

    private long start;

    public StopWatch() {
        start = System.currentTimeMillis();
    }

    public void ellapsedTime() {
        double time = ((System.currentTimeMillis() - start) / SECOND_IN_MILLIS);
        start = System.currentTimeMillis();
        System.out.println("*** Ellapsed time: " + time);
    }
}
