package com.carlospinan.algorithmictoolbox.week3;

import java.util.Scanner;

public class Change {

    private static int getChange(int m) {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

