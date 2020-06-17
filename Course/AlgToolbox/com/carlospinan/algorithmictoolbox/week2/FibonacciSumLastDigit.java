package com.carlospinan.algorithmictoolbox.week2;

import java.util.*;

public class FibonacciSumLastDigit {

    private static long pisanoLength() {
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

    private static long getFibonacciSum(long n) {
        if (n <= 1)
            return n;

        long pisanoLength = pisanoLength();
        n %= pisanoLength;

        long previous = 0;
        long current = 1;

        n %= pisanoLength; // After checking results. Sum repeats after 60 digits, similar tu Pinaso Numbers

        for (long i = 0; i < n; i++) {
            long tmp = previous;
            previous = current;
            current = (tmp + current + 1) % 10;
        }
        return previous;
    }

    private static long getFibonacciSumNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current = 1;
        long sum = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current;
        }

        return sum % 10;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSum(n);
        System.out.println(s);
    }
}

