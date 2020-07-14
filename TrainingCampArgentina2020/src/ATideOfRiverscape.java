import java.io.*;
import java.util.StringTokenizer;

/**
 * @author Carlos Piñan
 */
public class ATideOfRiverscape {

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

    private void start() {

        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        PrintStream out = System.out;

        int N = in.nextInt();
        int P = in.nextInt();
        int X = N - P;
        String S = in.next();
        char[] arr = S.toCharArray();

        int count = 0;

        for (int i = 0; i <= X && i + P < N; i++) {
            if (arr[i] != '.' && arr[i] == arr[i + P]) {
                count++;
            } else {
                set(arr, i, i + P);
            }
        }

        for (int i = 0; i < N; i++) {
            if (arr[i] == '.') {
                arr[i] = '0';
            }
        }

        if (count < X) {
            out.println(new String(arr));
        } else {
            out.println("NO");
        }

        out.close();

    }

    private void set(char[] arr, int L, int R) {
        if (arr[L] == '.' || arr[R] == '.') {
            if (arr[L] == '.' && arr[R] == '.') {
                arr[L] = '1';
                arr[R] = '0';
            } else {
                if (arr[L] != '.') {
                    arr[R] = get(arr[L]);
                } else {
                    arr[L] = get(arr[R]);
                }
            }
        }
    }

    private char get(char c) {
        return c == '1' ? '0' : '1';
    }

    public static void main(String[] args) {
        ATideOfRiverscape p = new ATideOfRiverscape();
        p.start();
    }
}
