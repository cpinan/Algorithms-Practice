package com.carlospinan.algorithmictoolbox.week4;

import java.util.*;

// 5_organizing_a_lottery
public class PointsAndSegments {

    private static final int LEFT = 0;
    private static final int POINT = 1;
    private static final int RIGHT = 2;

    static class Point {
        long value;
        int type;
        int index;

        public Point(long value, int type, int index) {
            this.value = value;
            this.type = type;
            this.index = index;
        }

        @Override
        public String toString() {
            return value + "_" + getType();
        }

        private char getType() {
            switch (type) {
                case LEFT:
                    return 'L';
                case RIGHT:
                    return 'R';
                default:
                    return 'P';
            }
        }
    }

    private static int[] countSegments(long[] starts, long[] ends, long[] points) {
        int m = points.length;
        int[] cnt = new int[m];

        int k = 0;
        Point[] arrayPoints = new Point[starts.length + ends.length + points.length];
        for (int i = 0; i < starts.length; i++) {
            arrayPoints[k++] = new Point(starts[i], LEFT, i);
        }
        for (int i = 0; i < ends.length; i++) {
            arrayPoints[k++] = new Point(ends[i], RIGHT, i);
        }
        for (int i = 0; i < points.length; i++) {
            arrayPoints[k++] = new Point(points[i], POINT, i);
        }

        Arrays.sort(arrayPoints, (o1, o2) -> (o1.value == o2.value) ? (o1.type - o2.type) : (int) (o1.value - o2.value));

        // System.out.println(Arrays.toString(arrayPoints));

        int segments = 0;
        for (k = 0; k < arrayPoints.length; k++) {
            Point curr = arrayPoints[k];
            if (curr.type == LEFT) {
                segments++;
            } else if (curr.type == RIGHT) {
                segments--;
            } else {
                if (segments > 0) {
                    cnt[curr.index] = segments;
                }
            }
        }

        return cnt;
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();

        long[] starts = new long[n];
        long[] ends = new long[n];
        long[] points = new long[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextLong();
            ends[i] = scanner.nextLong();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextLong();
        }
        //use fastCountSegments
        //
        // int[] cnt = naiveCountSegments(starts, ends, points);
        int[] cnt = countSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }

}

