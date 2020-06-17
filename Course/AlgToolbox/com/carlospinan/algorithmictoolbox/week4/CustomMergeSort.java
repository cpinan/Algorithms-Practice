package com.carlospinan.algorithmictoolbox.week4;

import java.util.Arrays;

public class CustomMergeSort {

    private static void mergeSort(int[] array, int l, int r) {
        if (r - l > 1) {
            int mid = (r - l) / 2 + l;
            mergeSort(array, l, mid);
            mergeSort(array, mid, r);
            merge(array, l, mid, r);
        }
    }

    private static void merge(int[] array, int l, int mid, int r) {
        int i, j;

        int n1 = mid - l;
        int n2 = r - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (i = 0; i < n1; i++) {
            L[i] = array[i + l];
        }

        for (i = 0; i < n2; i++) {
            R[i] = array[mid + i];
        }

        i = 0;
        j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] < R[j]) {
                array[k++] = L[i++];
            } else {
                array[k++] = R[j++];
            }
        }

        while (i < n1) {
            array[k++] = L[i++];
        }

        while (j < n2) {
            array[k++] = R[j++];
        }

    }

    public static void main(String[] args) {
        int[] arr1 = {2, 4, 5, 7, 1, 2, 3, 6};
        mergeSort(arr1, 0, arr1.length);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        mergeSort(arr2, 0, arr2.length);
        System.out.println(Arrays.toString(arr2));
    }
}
