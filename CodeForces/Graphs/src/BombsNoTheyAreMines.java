import java.io.*;
import java.util.*;

/**
 * @author Carlos Piñan
 */
public class BombsNoTheyAreMines {

    private static final String INPUT_FILE = "bombs_case_001.txt";
    private static final String OUTPUT_FILE = "bombs_result.txt";

    private static final int dX[] = {1, -1, 0, 0};
    private static final int dY[] = {0, 0, 1, -1};

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }

    // 1 == BOMB and 0 == FREE
    private void resolve() throws FileNotFoundException, UnsupportedEncodingException {
        // StopWatch stopWatch = new StopWatch();

        //File file = new File(INPUT_FILE);
        //InputStream inputStream = new FileInputStream(file);

        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        PrintStream out = System.out;

        int R, C;
        do {
            R = in.nextInt();
            C = in.nextInt();
            if (R > 0 && C > 0) {
                int[][] map = new int[R][C];
                int rows = in.nextInt();

                for (int i = 0; i < rows; i++) {
                    int bombRow = in.nextInt();
                    int totalBombs = in.nextInt();
                    for (int j = 0; j < totalBombs; j++) {
                        int bombCol = in.nextInt();
                        map[bombRow][bombCol] = 1;
                    }
                }

                int startRow = in.nextInt();
                int startCol = in.nextInt();

                int endRow = in.nextInt();
                int endCol = in.nextInt();

                int steps = bestPath(startRow, startCol, endRow, endCol, C, R, map);
                out.println(steps);
            }
        }
        while (R != 0 && C != 0);

        // stopWatch.ellapsedTime();
    }

    private int bestPath(int startRow, int startCol, int endRow, int endCol, int C, int R, int[][] map) {
        if (startRow != endRow || startCol != endCol) {
            int[][] distance = new int[R][C];
            Queue<Integer> bfs = new LinkedList<>();
            bfs.add(startRow);
            bfs.add(startCol);
            while (!bfs.isEmpty()) {
                int row = bfs.poll();
                int col = bfs.poll();
                for (int i = 0; i < 4; i++) {
                    int trow = row + dY[i];
                    int tcol = col + dX[i];
                    if (trow >= 0 && trow < R && tcol >= 0 && tcol < C && map[trow][tcol] == 0) {
                        map[trow][tcol] = 1;
                        distance[trow][tcol] = distance[row][col] + 1;
                        bfs.add(trow);
                        bfs.add(tcol);
                        if (endRow == trow && endCol == tcol) {
                            return distance[trow][tcol];
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        BombsNoTheyAreMines p = new BombsNoTheyAreMines();
        try {
            p.resolve();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
