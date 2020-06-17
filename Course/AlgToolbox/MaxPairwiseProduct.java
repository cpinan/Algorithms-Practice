import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.max;

public class MaxPairwiseProduct {

    static long getMaxPairwiseProduct(long[] numbers) {
        int n = numbers.length;
        int max_index1 = -1;
        int max_index2 = -1;
        long max_product = 0;

        for (int i = 0; i < n; i++) {
            if (max_index1 == -1 || numbers[i] > numbers[max_index1]) {
                max_index1 = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (i != max_index1 && (max_index2 == -1 || numbers[i] > numbers[max_index2])) {
                max_index2 = i;
                max_product = numbers[max_index1] * numbers[max_index2];
            }
        }

        return max_product;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        long[] numbers = new long[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProduct(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                        InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}
