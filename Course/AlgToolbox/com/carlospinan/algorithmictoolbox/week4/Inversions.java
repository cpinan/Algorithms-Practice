package com.carlospinan.algorithmictoolbox.week4;

import java.util.*;

public class Inversions {

    private static int merge(int[] a, int[] b, int left, int mid, int right) {
        int numberOfInversions = 0;

        int i, j, k;

        i = left;
        j = mid;
        k = left;

        int leftEnd = mid - 1;
        int rightEnd = right;

        while (i <= leftEnd && j <= rightEnd) {
            if (a[i] <= a[j]) {
                b[k++] = a[i++];
            } else {
                b[k++] = a[j++];
                numberOfInversions += (mid - i);
            }
        }

        while (i <= leftEnd) {
            b[k++] = a[i++];
        }

        while (j <= rightEnd) {
            b[k++] = a[j++];
        }

        for (i = left; i <= right; i++) {
            a[i] = b[i];
        }

        return numberOfInversions;
    }

    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left) {
            return numberOfInversions;
        }
        int mid = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, b, left, mid);
        numberOfInversions += getNumberOfInversions(a, b, mid + 1, right);

        numberOfInversions += merge(a, b, left, mid + 1, right);

        return numberOfInversions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(getNumberOfInversions(a, b, 0, a.length - 1));
    }
}

