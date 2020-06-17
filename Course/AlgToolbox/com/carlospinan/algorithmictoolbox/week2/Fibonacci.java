package com.carlospinan.algorithmictoolbox.week2;

import java.util.Scanner;

public class Fibonacci {

    private static long calc_fib(int n, long[] memory) {
        if (n <= 1)
            return n;
        if (memory[n] == 0) {
            memory[n] = calc_fib(n - 1, memory) + calc_fib(n - 2, memory);
        }
        return memory[n];
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] memory = new long[n + 1];

        System.out.println(calc_fib(n, memory));
    }
}
