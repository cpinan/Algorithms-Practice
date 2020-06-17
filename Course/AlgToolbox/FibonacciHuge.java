import java.util.Scanner;

public class FibonacciHuge {

    private static long getPisanoPeriodLength(long m) {
        long previous = 0;
        long current = 1;

        for (int i = 0; i < m * m; i++) {
            long tmp = previous;
            previous = current;
            current = (tmp + current) % m;
            if (previous == 1 && current == 0) {
                return i + 2;
            }
        }
        return 0;
    }

    private static long getFibonacciLastMDigits(long n, long m) {
        if (n <= 1)
            return n;
        long previous = 0;
        long current = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % m;
        }
        return current;
    }

    private static long getFibonacciHuge(long n, long m) {
        if (n <= 1)
            return n;
        long pisanoPeriodLength = getPisanoPeriodLength(m);
        n %= pisanoPeriodLength;
        return getFibonacciLastMDigits(n, m);
    }

    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;
        if (m <= 1) {
            return 0;
        }

        long previous = 0;
        long current = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }
        return current % m;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHuge(n, m));
    }
}

