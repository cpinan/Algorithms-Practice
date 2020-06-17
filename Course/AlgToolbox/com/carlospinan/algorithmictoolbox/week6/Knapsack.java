package com.carlospinan.algorithmictoolbox.week6;

import java.util.*;

public class Knapsack {

    static int optimalWeight(int W, int[] weights) {
        int n = weights.length;
        int[][] M = new int[W + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            int wi = weights[i - 1];
            for (int w = 1; w <= W; w++) {
                M[w][i] = M[w][i - 1];
                if (wi <= w) {
                    int val = M[w - wi][i - 1] + wi;
                    M[w][i] = Math.max(val, M[w][i]);
                }
            }
        }

        return M[W][n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

