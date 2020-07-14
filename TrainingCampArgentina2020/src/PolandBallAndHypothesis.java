import java.io.*;
import java.util.StringTokenizer;

/**
 * @author Carlos Piñan
 */
public class PolandBallAndHypothesis {

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

        int M = 1;
        while (isPrime(N * M + 1)) {
            M++;
        }
        out.println(M);
        out.close();
    }

    private boolean isPrime(final int X) {
        for (int i = 2; i < X; i++) {
            if (X % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PolandBallAndHypothesis p = new PolandBallAndHypothesis();
        p.start();
    }
}
