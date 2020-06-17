import java.util.*;
import java.io.*;

public class Partition3 {

    private static int partition3(int[] A) {
        int n = A.length;
        if (n < 3) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += A[i];
        }
        if (sum % 3 != 0) {
            return 0;
        }
        int res = sum / 3;
        Arrays.sort(A);
        Set<Integer> s = new HashSet<>();

        for (int i = 0; i < 3; i++) {
            int total = res;
            for (int j = n - 1; j >= 0; j--) {
                if (A[j] <= total && !s.contains(j)) {
                    s.add(j);
                    total -= A[j];
                }
            }
        }
        return s.size() == A.length ? 1 : 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3(A));
    }
}

