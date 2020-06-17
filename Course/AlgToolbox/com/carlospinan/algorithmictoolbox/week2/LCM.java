package com.carlospinan.algorithmictoolbox.week2;

import java.util.*;

public class LCM {

    private static long gcd(long a, long b) {
        if (b == 0)
            return a;
        long r = a % b;
        return gcd(b, r);
    }

    private static long lcm(long a, long b) {
        long gcd = gcd(a, b);
        return gcd * a / gcd * b / gcd;
    }

    private static long lcm_naive(int a, int b) {
        for (long l = 1; l <= (long) a * b; ++l)
            if (l % a == 0 && l % b == 0)
                return l;

        return (long) a * b;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();

        System.out.println(lcm(a, b));
    }
}
