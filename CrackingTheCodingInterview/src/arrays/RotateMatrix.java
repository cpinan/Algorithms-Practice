package arrays;

import java.util.Arrays;

/**
 * @author Carlos Piñan
 * <p>
 * Rotate Matrix:
 * <p>
 * Given an image represented by a NxN matrix, where each pixel
 * in the image is 4 bytes, write a method to rotate the image
 * by 90 degrees. Do it in place.
 */
public class RotateMatrix {

    private static final int[][][] INPUT = {
            {
                    {1, 2},
                    {3, 4}
            },
            {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9}
            },
            {
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 16}
            },
            {

                    {1, 2, 3, 4, 5},
                    {6, 7, 8, 9, 10},
                    {11, 12, 13, 14, 15},
                    {16, 17, 18, 19, 20},
                    {21, 22, 23, 24, 25}
            }
    };

    private static final String[] OUTPUT = {
            "[2, 4][1, 3]",
            "[3, 6, 9][2, 5, 8][1, 4, 7]",
            "[4, 8, 12, 16][3, 7, 11, 15][2, 6, 10, 14][1, 5, 9, 13]",
            "[5, 10, 15, 20, 25][4, 9, 14, 19, 24][3, 8, 13, 18, 23][2, 7, 12, 17, 22][1, 6, 11, 16, 21]"
    };

    public void rotateMatrix(int[][] matrix) {
        if (matrix != null) {
            int N = matrix.length;
            int row = 0;
            int until = (N / 2) + ((N % 2 != 0) ? 1 : 0);
            while (row < until) {
                for (int col = 0; col < N / 2; col++) {
                    swap(matrix, row, col);
                }
                row++;
            }

        }
    }

    // N = 3
    // 1. Row 1; Col 0
    // 2. Row 0; Col 1
    // 3. Row 1; Col 2
    // 4. Row 2; Col 1
    private void swap(int[][] matrix, int row, int col) {
        int N = matrix.length;
        int tmp = matrix[row][col];
        matrix[row][col] = matrix[col][N - (row + 1)];
        matrix[col][N - (row + 1)] = matrix[N - (row + 1)][N - (col + 1)];
        matrix[N - (row + 1)][N - (col + 1)] = matrix[N - (col + 1)][row];
        matrix[N - (col + 1)][row] = tmp;
    }

    public static void main(String[] args) {
        RotateMatrix m = new RotateMatrix();

        for (int i = 0; i < INPUT.length; i++) {
            System.out.println("Case # " + (i + 1));
            int[][] matrix = INPUT[i];
            m.rotateMatrix(matrix);
            String outcome = printHelper(matrix);
            String expected = OUTPUT[i];
            if (matrix != null) {
                System.out.println();
                System.out.println("Outcome: " + outcome);
                System.out.println("Expected: " + expected);
                System.out.println(outcome.equalsIgnoreCase(expected));
                System.out.println();
            }
            System.out.println();
        }

    }

    private static String printHelper(int[][] matrix) {
        StringBuilder b = new StringBuilder();
        if (matrix != null) {
            int N = matrix.length;
            for (int row = 0; row < N; row++) {
                b.append(Arrays.toString(matrix[row]));
            }
        }
        return b.toString();
    }
}
