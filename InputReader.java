package _helper;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author Carlos Piñan
 */
public class InputReader {

    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader() {
        this(System.in);
    }

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
    }

    public InputReader(String filePath) throws FileNotFoundException {
        this(new FileInputStream(new File(filePath)));
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

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public static void main(String[] args) {

    }

}