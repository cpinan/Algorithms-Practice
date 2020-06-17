package com.carlospinan.algorithmictoolbox.week5;

import java.util.*;

class EditDistance {

    public static int EditDistance(String s, String t) {
        int n = s.length() + 1;
        int m = t.length() + 1;
        int[][] matrix = new int[n][m];

        for (int row = 1; row < n; row++) {
            matrix[row][0] = row;
        }

        for (int col = 1; col < m; col++) {
            matrix[0][col] = col;
        }

        for (int col = 1; col < m; col++) {
            char tChar = t.charAt(col - 1);
            for (int row = 1; row < n; row++) {
                char sChar = s.charAt(row - 1);

                int topLeft = matrix[row - 1][col - 1] + (sChar != tChar ? 1 : 0);
                int top = matrix[row - 1][col] + 1;
                int left = matrix[row][col - 1] + 1;

                int value = Math.min(topLeft, Math.min(top, left));
                matrix[row][col] = value;
            }
        }

        /*
        for (int row = 0; row < n; row++) {
            System.out.println(Arrays.toString(matrix[row]));
        }
         */

        return matrix[n - 1][m - 1];
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        System.out.println(EditDistance(s, t));
    }

}
