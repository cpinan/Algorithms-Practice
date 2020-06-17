import java.util.*;

// 5_collecting_signatures
public class CoveringSegments {

    private static long[] optimalPoints(Segment[] segments) {
        int n = segments.length;
        // Sort by left most.
        Arrays.sort(segments, new Comparator<Segment>() {
            @Override
            public int compare(Segment curr, Segment next) {
                if (curr.end < next.end) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        // System.out.println(Arrays.toString(segments));
        List<Long> array = new ArrayList<>();
        long point = segments[0].end;
        array.add(point);

        for (int i = 1; i < n; i++) {
            Segment curr = segments[i];
            if (point < curr.start || point > curr.end) {
                point = curr.end;
                array.add(point);
            }
        }


        /*
        int i = 0;
        long previous = -1;
        while (i < n) {
            Segment current = segments[i];
            while (i + 1 < n && segments[i + 1].start <= current.end) {
                if (current.end > segments[i + 1].end) {
                    current = segments[i + 1];
                    break;
                }
                i++;
            }
            if (previous == -1 || current.end != previous) {
                previous = current.end;
                array.add(previous);
                //System.out.print(previous + " ");
            }
            i++;
        }
        //System.out.println();

         */
        long[] points = new long[array.size()];
        for (int i = 0; i < points.length; i++) {
            points[i] = array.get(i);
        }

        return points;
    }

    private static class Segment {
        long start, end;

        Segment(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Segment{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            long start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        long[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (long point : points) {
            System.out.print(point + " ");
        }
    }
}
 
