package common;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author Carlos Piñan
 */

public class IOTemplate {

    private static final String UTF8 = "UTF-8";

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String read() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int readInt() {
            return Integer.parseInt(read());
        }

        public long readLong() {
            return Long.parseLong(read());
        }

        public String readLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                return null;
            }
        }

    }

    private boolean isConsoleMode;

    private InputReader reader;

    private PrintStream outConsole;
    private PrintWriter outFile;

    // Default will use console
    public IOTemplate() {
        this.isConsoleMode = true;

        InputStream inputStream = System.in;
        InputReader inputReader = new InputReader(inputStream);
        PrintStream out = System.out;

        this.reader = inputReader;
        this.outConsole = out;
    }

    public IOTemplate(final String inputFile, final String outputFile) {
        this.isConsoleMode = false;
        try {
            InputStream inputStream = new FileInputStream(new File(inputFile));
            InputReader inputReader = new InputReader(inputStream);
            PrintWriter out = new PrintWriter(outputFile, UTF8);

            this.reader = inputReader;
            this.outFile = out;
        } catch (Exception e) {
            System.out.println(inputFile + " or " + outputFile + " are invalid: " + e.getLocalizedMessage());
        }
    }

    // INPUT UTILITY
    protected int scanInt() {
        return reader.readInt();
    }

    protected long scanLong() {
        return reader.readLong();
    }

    protected String scanLine() {
        return reader.readLine();
    }

    protected String scanWord() {
        return reader.read();
    }

    // OUTPUT UTILITY
    protected void print(Object trace) {
        if (this.isConsoleMode) {
            outConsole.print(trace);
        } else {
            outFile.print(trace);
        }
    }

    protected void println(Object trace) {
        print(trace);
        if (this.isConsoleMode) {
            outConsole.println();
        } else {
            outFile.println();
        }
    }

    /**
     * Close the outputFile.
     */
    protected void close() {
        if (!this.isConsoleMode) {
            outFile.close();
        }
    }

}