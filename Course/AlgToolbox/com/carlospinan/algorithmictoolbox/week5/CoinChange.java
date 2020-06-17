package com.carlospinan.algorithmictoolbox.week5;

import java.util.Arrays;

public class CoinChange {

    private static final int[] COINS = {25, 20, 4, 1};
    private static final int[] INPUT = {
            29,
            40,
            44
    };

    private static int getChangeNaive(int m) {
        int coins = 0;
        int[] coinsArray = {10, 5, 1};
        for (int i = 0; i < coinsArray.length; i++) {
            if (m >= coinsArray[i]) {
                coins += m / coinsArray[i];
                m %= coinsArray[i];
            }
        }
        return coins;
    }

    private static int getChangeDp(int m) {
        int n = m + 1;
        int[] coinChange = new int[n];
        for (int i = 1; i < n; i++) {
            int minCoin = Integer.MAX_VALUE;
            for (int coin : COINS) {
                int previous = i - coin;
                if (previous >= 0) {
                    minCoin = Math.min(minCoin, coinChange[previous] + 1);
                }
            }
            coinChange[i] = minCoin;
        }
        // System.out.println(Arrays.toString(coinChange));
        return coinChange[m];
    }

    public static void main(String[] args) {
        int n = INPUT.length;
        for (int i = 0; i < n; i++) {
            int v = INPUT[i];
            // System.out.println();
            int coinsNeeded = getChangeDp(v);
            System.out.println("value: " + v);
            System.out.println("coinsNeeded: " + coinsNeeded);
            System.out.println("************************************");
        }
    }

}
