package com.carlospinan.algorithmictoolbox.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.max;

public class MaxPairwiseProduct {

    static int getMaxPairwiseProductFast(int[] numbers) {
        int n = numbers.length;
        int max_index1 = -1;
        int max_index2 = -1;
        int max_product = 0;

        for (int i = 0; i < n; i++) {
            if (max_index1 == -1 || numbers[i] > numbers[max_index1]) {
                max_index1 = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (i != max_index1 && (max_index2 == -1 || numbers[i] > numbers[max_index2])) {
                max_index2 = i;
                max_product = numbers[max_index1] * numbers[max_index2];
            }
        }

        return max_product;
    }

    static int getMaxPairwiseProduct(int[] numbers) {
        int max_product = 0;
        int n = numbers.length;

        for (int first = 0; first < n; ++first) {
            for (int second = first + 1; second < n; ++second) {
                max_product = max(max_product, numbers[first] * numbers[second]);
            }
        }

        return max_product;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        // System.out.println(getMaxPairwiseProduct(numbers));
        System.out.println(getMaxPairwiseProductFast(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                        InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}
