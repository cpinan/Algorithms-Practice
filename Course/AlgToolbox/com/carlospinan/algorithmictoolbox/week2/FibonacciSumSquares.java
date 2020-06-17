package com.carlospinan.algorithmictoolbox.week2;

import java.util.*;

public class FibonacciSumSquares {

    private static int pisanoLength() {
        final long m = 10;

        long previous = 0;
        long current = 1;

        int length = 0;
        while (true) {
            length++;
            long tmp = previous;
            previous = current;
            current = (tmp + current) % m;
            if (previous == 0 && current == 1) {
                break;
            }
        }
        return length;
    }

    private static long getFibonacciSumSquares(long n) {
        long pisanoLength = pisanoLength();
        n %= pisanoLength;

        long previous = 0;
        long current = 1;
        long sum = 1;

        for (long i = 0; i < n - 1; i++) {
            long tmp = previous;
            previous = current;
            long val = (tmp + current) % 10;
            long squareVal = (val * val) % 10;
            sum = (squareVal + sum) % 10;
            current = val;
        }
        return sum;
    }

    private static long getFibonacciSumSquaresNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current = 1;
        long sum = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current * current;
        }

        return sum % 10;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSumSquares(n);
        System.out.println(s);
    }
}

