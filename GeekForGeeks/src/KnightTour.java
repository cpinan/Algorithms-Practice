import java.util.Arrays;

/**
 * @author Carlos Piñan
 * @source https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1/
 * <p>
 * Backtracking is an algorithmic paradigm that tries different solutions until
 * finds a solution that “works”. Problems which are typically solved using
 * backtracking technique have following property in common.
 * These problems can only be solved by trying every possible configuration
 * and each configuration is tried only once. A Naive solution for
 * these problems is to try all configurations and output a configuration
 * that follows given problem constraints.
 * <p>
 * Backtracking works in incremental way and is an optimization over the Naive solution
 * where all possible configurations are generated and tried.
 * <p>
 * For example, consider the following Knight’s Tour problem.
 * The knight is placed on the first block of an empty board and,
 * moving according to the rules of chess, must visit each square exactly once.
 */
public class KnightTour {

    private static final int N = 8;
    private static int[][] MOVES = {
            // ROW, COLS
            {2, 1},
            {1, 2},

            {-1, 2},
            {-2, 1},

            {-2, -1},
            {-1, -2},

            {1, -2},
            {2, -1}
    };

    private int[][] board;

    public void solve() {
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = -1;
            }
        }

        if (knightTour(0, 0)) {
            print();
        } else {
            System.out.println("CANNOT BE COMPLETED");
        }

    }

    private boolean knightTour(int row, int col) {
        board[row][col] = 0;
        return knightTourUtil(row, col, 1);
    }

    private boolean knightTourUtil(int row, int col, int index) {
        if (index >= N * N) {
            System.out.println("BOARD IS COMPLETED!");
            return true;
        } else {
            for (int[] moves : MOVES) {
                int newRow = row + moves[0];
                int newCol = col + moves[1];
                if (isSafe(newRow, newCol)) {
                    board[newRow][newCol] = index;
                    if (knightTourUtil(newRow, newCol, index + 1)) {
                        return true;
                    }
                    board[newRow][newCol] = -1;
                }
            }
        }
        return false;
    }

    private void print() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    private boolean isSafe(int row, int col) {
        return row >= 0 && row < N &&
                col >= 0 && col < N &&
                board[row][col] == -1;
    }

    public static void main(String[] args) {
        KnightTour m = new KnightTour();
        m.solve();
    }

}
