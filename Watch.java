package _helper;

/**
 * @author Carlos Piñan
 */
public class Watch {

    private static final long SECOND = 1000L;
    private long startTime;

    public Watch() {
        reset();
    }

    public void reset() {
        this.startTime = System.currentTimeMillis();
    }

    public long ellapsedTime() {
        long finishTime = System.currentTimeMillis();
        long diffTime = finishTime - startTime;
        this.startTime = finishTime;
        return diffTime * SECOND;
    }

    public static void main(String[] args) {

    }
}
