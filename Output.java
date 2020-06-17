package _helper;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author Carlos Piñan
 */
public class Output {

    private PrintWriter printWriter;
    private PrintStream printStream;

    public Output() {
        close();
        printWriter = null;
        printStream = System.out;
    }

    public Output(String outputFile) {
        close();
        printStream = null;
        try {
            printWriter = new PrintWriter(outputFile, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void println(Object x) {
        if (printWriter != null) {
            printWriter.println(x);
        } else if (printStream != null) {
            printStream.println(x);
        }
    }

    public void print(Object x) {
        if (printWriter != null) {
            printWriter.print(x);
        } else if (printStream != null) {
            printStream.print(x);
        }
    }

    public void close() {
        if (printWriter != null) {
            printWriter.close();
        } else if (printStream != null) {
            printStream.close();
        }
    }

    public static void main(String[] args) {

    }
}
