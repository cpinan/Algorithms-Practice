import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 6 Maximum Number of Prizes
public class DifferentSummands {
    private static List<Long> optimalSummands(long n) {
        List<Long> summands = new ArrayList<>();
        long sum = 0;
        long left = 1;
        while (true) {
            sum += left;
            long remaining = n - sum;
            if (remaining > left) {
                summands.add(left);
            } else {
                summands.add(n - (sum - left));
                break;
            }
            left++;
        }
        return summands;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        List<Long> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Long summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

