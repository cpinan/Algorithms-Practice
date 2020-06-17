package com.carlospinan.algorithmictoolbox.week2;

import java.util.*;

public class FibonacciPartialSum {

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

    private static int[] saveOneDigitResult(int length) {
        int[] res = new int[length + 1];
        res[0] = 0;
        res[1] = 1;
        for (int i = 2; i <= length; i++) {
            res[i] = (res[i - 1] + res[i - 2] + 1) % 10;
        }
        return res;
    }

    private static long getFibonacciPartialSum(long from, long to) {
        int pisanoLength = pisanoLength();
        int[] fib = saveOneDigitResult(pisanoLength);

        to %= pisanoLength;
        from %= pisanoLength;

        int toIndex = (int) to;
        int fromIndex = (int) from;

        int toValue = fib[toIndex];
        int fromValue = fromIndex > 0 ? fib[fromIndex - 1] : 0;

        int res = toValue - fromValue;
        if (res < 0) {
            res += 10;
        }
        return res % 10;
    }

    private static long getFibonacciPartialSumNaive(long from, long to) {
        long sum = 0;

        long current = 0;
        long next = 1;

        for (long i = 0; i <= to; ++i) {
            if (i >= from) {
                sum += current;
            }

            long new_current = next;
            next = next + current;
            current = new_current;
        }

        return sum % 10;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSum(from, to));
    }
}

