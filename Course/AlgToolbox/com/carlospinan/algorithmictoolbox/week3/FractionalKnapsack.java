package com.carlospinan.algorithmictoolbox.week3;

import java.util.Arrays;
import java.util.Scanner;

// Maximum Value of the Loot
public class FractionalKnapsack {

    private static double roundDigits(double value, int digits) {
        double roundValue = Math.pow(10, digits);
        return Math.round(value * roundValue) / roundValue;
    }

    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        int n = values.length;
        double[] unitValue = new double[n];
        for (int i = 0; i < n; i++) {
            unitValue[i] = (double) values[i] / (double) weights[i];
        }

        for (int i = 0; i < n && capacity > 0; i++) {
            int index = -1;
            for (int j = 0; j < n; j++) {
                if (unitValue[j] != -1 && (index == -1 || unitValue[j] > unitValue[index])) {
                    index = j;
                }
            }
            if (index != -1) {
                int weight = weights[index];
                int minWeight = Math.min(capacity, weight);
                value += unitValue[index] * (double) minWeight;
                capacity -= minWeight;
                unitValue[index] = -1;
            }
        }

        return roundDigits(value, 4);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(String.format("%.4f", getOptimalValue(capacity, values, weights)));
    }
} 
