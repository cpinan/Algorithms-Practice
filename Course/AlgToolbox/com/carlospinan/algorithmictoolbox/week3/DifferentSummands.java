package com.carlospinan.algorithmictoolbox.week3;

import java.util.*;

// 6 Maximum Number of Prizes
public class DifferentSummands {
    private static List<Long> optimalSummands(long n) {
        List<Long> summands = new ArrayList<>();
        long sum = 0;
        long left = 1;
        while (sum < n) {
            sum += left;
            long remaining = n - sum;
            if (remaining > left) {
                summands.add(left);
            } else {
                summands.add(n - (sum - left));
                sum = n;
            }
            left++;
        }
        return summands;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        List<Long> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Long summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

