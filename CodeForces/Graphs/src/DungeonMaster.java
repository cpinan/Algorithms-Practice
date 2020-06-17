import java.io.*;
import java.util.*;

/**
 * Created by cpinan on 6/16/17.
 */
public class DungeonMaster {

    private static final String INPUT_FILE = "dungeon_case.txt";
    private static final String OUTPUT_FILE = "dungeon_result.txt";

    private static final char START = 'S';
    private static final char EXIT = 'E';
    private static final char ROCK = '#';

    private static final String TRAPPED = "Trapped!";
    private static final String ESCAPED_FORMAT = "Escaped in %d minute(s).";

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

    static class Cell {
        public int level;
        public int row;
        public int col;
        public int w;

        public static Cell create(int level, int row, int col) {
            return new Cell(level, row, col);
        }

        public Cell(int level, int row, int col) {
            this.w = 0;
            this.level = level;
            this.row = row;
            this.col = col;
        }

        @Override
        public int hashCode() {
            return level * 30000 + row * 300 + col;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof Cell)) {
                return false;
            }
            Cell cell = (Cell) obj;
            return hashCode() == cell.hashCode();
        }
    }

    private void resolve() throws FileNotFoundException, UnsupportedEncodingException {
//        File file = new File(INPUT_FILE);
//        InputStream inputStream = new FileInputStream(file);
//        Main.InputReader in = new Main.InputReader(inputStream);

        InputStream inputStream = System.in;
        Main.InputReader in = new Main.InputReader(inputStream);

//        PrintWriter out = new PrintWriter(OUTPUT_FILE, "UTF-8");
        PrintStream out = System.out;

        // Level, Rows, Cols
        int L, R, C;

        do {
            L = in.nextInt();
            R = in.nextInt();
            C = in.nextInt();

            if (L > 0 && R > 0 && C > 0) {

                char[][][] map = new char[L][R][C];

                Cell start = null, exit = null;

                for (int l = 0; l < L; l++) {
                    for (int row = 0; row < R; row++) {
                        String s = in.next();
                        for (int col = 0; col < C; col++) {
                            char c = s.charAt(col);
                            if (c == START) {
                                start = Cell.create(l, row, col);
                            } else if (c == EXIT) {
                                exit = Cell.create(l, row, col);
                            }
                            map[l][row][col] = c;
                        }
                    }
                }

                if (start != null && exit != null) {
                    int time = resolve3DMaze(map, start, exit);
                    if (time == -1) {
                        out.println(TRAPPED);
                    } else {
                        out.println(String.format(ESCAPED_FORMAT, time));
                    }
                }
            }
        } while (L != 0 && R != 0 && C != 0);
        out.close();
    }

    private int resolve3DMaze(char[][][] map, Cell start, Cell exit) {
        if (!start.equals(exit)) {
            start.w = 0;
            int LEVELS = map.length;
            int ROWS = map[0].length;
            int COLS = map[0][0].length;
            map[start.level][start.row][start.col] = ROCK;
            Queue<Cell> queue = new LinkedList<>();
            queue.add(start);
            while (!queue.isEmpty()) {
                Cell cell = queue.remove();
                // System.out.println(debugPath(cell, map));
                for (Cell edge : getEdges(LEVELS, ROWS, COLS, cell)) {
                    if (edge != null) {
                        // System.out.println("\t" + debugPath(edge, map));
                        if (map[edge.level][edge.row][edge.col] != ROCK) {
                            edge.w = cell.w + 1;
                            if (edge.equals(exit)) {
                                return edge.w;
                            }
                            map[edge.level][edge.row][edge.col] = ROCK;
                            queue.add(edge);
                        }
                    }
                }
            }
            return -1;
        }
        return 0;
    }

    private String debugPath(Cell cell, char[][][] map) {
        return cell.level + " ; " + cell.row + " ; " + cell.col + " === " +
                map[cell.level][cell.row][cell.col];
    }

    private Cell[] getEdges(int LEVELS, int ROWS, int COLS, Cell cell) {
        int left = cell.col - 1;
        int right = cell.col + 1;
        int top = cell.row - 1;
        int bottom = cell.row + 1;
        int previousLevel = cell.level - 1;
        int nextLevel = cell.level + 1;
        Cell[] cells = new Cell[6];
        if (left >= 0) {
            cells[0] = Cell.create(cell.level, cell.row, left);
        }
        if (right < COLS) {
            cells[1] = Cell.create(cell.level, cell.row, right);
        }
        if (top >= 0) {
            cells[2] = Cell.create(cell.level, top, cell.col);
        }
        if (bottom < ROWS) {
            cells[3] = Cell.create(cell.level, bottom, cell.col);
        }
        if (previousLevel >= 0) {
            cells[4] = Cell.create(previousLevel, cell.row, cell.col);
        }
        if (nextLevel < LEVELS) {
            cells[5] = Cell.create(nextLevel, cell.row, cell.col);
        }
        return cells;
    }


    public static void main(String[] args) {
        DungeonMaster p = new DungeonMaster();
        try {
            p.resolve();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
