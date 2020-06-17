import java.io.*;
import java.util.*;

// 3_improving_quicksort
public class Sorting {

    private static Random random = new Random();

    private static int[] partition3(int[] a, int l, int r) {
        int mid = l;
        int pivot = a[r];

        // 4 4 3 3 2 4
        while (mid <= r) {
            if (a[mid] < pivot) {
                swap(a, l++, mid++);
            } else if (a[mid] > pivot) {
                swap(a, mid, r--);
            } else if (a[mid] == pivot) {
                mid++;
            }
        }
        // 3 4 4 3 2 4
        // 3 3 4 4 2 4
        // 3 3 2 4 4 4

        int m1 = l;
        int m2 = r;
        int[] m = {m1, m2};
        return m;
    }

    private static int partition2(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                swap(a, i, j++);
            }
        }
        swap(a, l, j);
        return j;
    }

    private static void randomizedQuickSort(int[] a, int l, int r) {
        if (l < r) {
            int k = random.nextInt(r - l + 1) + l;
            swap(a, l, k);
            //use partition3
            // int m = partition2(a, l, r);
            int[] m = partition3(a, l, r);
            randomizedQuickSort(a, l, m[0] - 1);
            randomizedQuickSort(a, m[1], r);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        randomizedQuickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
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

