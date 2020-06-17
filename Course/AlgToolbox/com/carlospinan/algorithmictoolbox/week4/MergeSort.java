package com.carlospinan.algorithmictoolbox.week4;

import java.util.Arrays;

public class MergeSort {

    public void sort(int[] array, int left, int right) {
        if (right > left) {
            int mid = (right + left) / 2;
            sort(array, left, mid);
            sort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int[] L = new int[mid - left + 1];
        int[] R = new int[right - mid];

        int n1 = L.length;
        int n2 = R.length;

        for (int i = 0; i < n1; i++) {
            L[i] = array[i + left];
        }

        for (int i = 0; i < n2; i++) {
            R[i] = array[i + mid + 1];
        }

        int k = left;
        int i = 0;
        int j = 0;

        while (i < n1 && j < n2) {
            if (L[i] < R[j]) {
                array[k] = L[i++];
            } else {
                array[k] = R[j++];
            }
            k++;
        }

        while (i < n1) {
            array[k++] = L[i++];
        }
        while (j < n2) {
            array[k++] = R[j++];
        }

    }

    public static void main(String[] args) {
        int[] array = new int[]{4, 3, 2, 1, 0};
        MergeSort m = new MergeSort();
        m.sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

}
