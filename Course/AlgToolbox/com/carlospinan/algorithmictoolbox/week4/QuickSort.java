package com.carlospinan.algorithmictoolbox.week4;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public void randomSort(int[] array, int left, int right) {
        if (left < right) {
            int k = new Random().nextInt(right - left + 1) + left;
            swap(array, left, k);
            int m = partition(array, left, right);
            randomSort(array, left, m - 1);
            randomSort(array, m + 1, right);
        }
    }

    public void sort(int[] array, int left, int right) {
        if (left < right) {
            int m = partition(array, left, right);
            sort(array, left, m - 1);
            sort(array, m + 1, right);
        }
    }

    private int partition(int[] array, int left, int right) {
        int index = left;
        int pivot = array[left];

        for (int i = left + 1; i <= right; i++) {
            if (array[i] <= pivot) {
                index++;
                swap(array, i, index);
            }
        }
        swap(array, left, index);

        return index;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        // int[] array = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        // int[] array = new int[]{6, 4, 2, 3, 4, 6, 1, 9, 7, 1, 9};
        int[] array = new int[]{4, 3, 2, 1};
        new QuickSort().sort(array, 0, array.length - 1);
        // new QuickSort().randomSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

}
