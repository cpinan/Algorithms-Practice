import java.util.Scanner;

public class LargestNumber {

    private static boolean isCurrentLarger(String current, String other) {
        String leftValue = current + other;
        String rightValue = other + current;
        return Integer.parseInt(leftValue) > Integer.parseInt(rightValue);
    }

    private static String largestNumber(String[] a) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            int idx = i;
            String currentValue = a[i];
            for (int j = 0; j < a.length; j++) {
                if (currentValue == null) {
                    currentValue = a[j];
                }
                if (currentValue != null && i != j && a[j] != null) {
                    if (!isCurrentLarger(currentValue, a[j])) {
                        idx = j;
                        currentValue = a[j];
                    }
                }
            }
            a[idx] = null;
            if (currentValue != null) {
                builder.append(currentValue);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}

