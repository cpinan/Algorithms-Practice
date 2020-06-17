package com.carlospinan.algorithmictoolbox.week5;

import java.util.Arrays;
import java.util.Scanner;

public class ChangeDP {

    private static final int[] COINS = {1, 3, 4};

    private static int getChange(int m) {
        int n = m + 1;
        int[] coins = new int[n];
        for (int i = 1; i < n; i++) {
            int minCoin = Integer.MAX_VALUE;
            for (int j = 0; j < COINS.length; j++) {
                int prevIndex = i - COINS[j];
                if (prevIndex >= 0) {
                    minCoin = Math.min(minCoin, coins[prevIndex] + 1);
                }
            }
            coins[i] = minCoin;
        }
        System.out.println(Arrays.toString(coins));
        return coins[n - 1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

