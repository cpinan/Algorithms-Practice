package com.carlospinan.algorithmictoolbox.week4;

import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Closest {

    static class Point implements Comparable<Point> {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public long squareDistance(Point p) {
            long x = this.x - p.x;
            long y = this.y - p.y;
            return x * x + y * y;
        }

        @Override
        public int compareTo(Point o) {
            return o.x == x ? Long.signum(y - o.y) : Long.signum(x - o.x);
        }
    }

    static double minimalDistance(int[] x, int y[]) {
        int n = x.length;
        Point[] points = new Point[n];

        long ans = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        Arrays.sort(points);
        for (int i = 1; i < n; i++) {
            ans = Math.min(ans, points[i].squareDistance(points[i - 1]));
        }


        return Math.sqrt(ans);
    }

    static long closestPointUtil(Point[] points, int left, int right) {
        if (right - left <= 3) {
            return 0;
        }
        int mid = (right + left) / 2;
        long dl = closestPointUtil(points, left, mid);
        long dr = closestPointUtil(points, mid + 1, right);
        long d = Math.min(dl, dr);



        return 0;
    }

    static long bruteForce(Point[] points, int left, int right) {
        return 0;
    }

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }
        System.out.println(minimalDistance(x, y));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
