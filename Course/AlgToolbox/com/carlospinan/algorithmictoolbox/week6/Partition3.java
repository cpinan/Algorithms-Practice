package com.carlospinan.algorithmictoolbox.week6;

import java.util.*;
import java.io.*;

public class Partition3 {

    private static int partition3(int[] A) {
        int n = A.length;
        if (n < 3) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += A[i];
        }
        if (sum % 3 != 0) {
            return 0;
        }
        return 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3(A));
    }
}

