import java.util.*;

public class PrimitiveCalculator {

    // times 2, times 3, plus 1
    private static List<Integer> optimal_sequence(int n) {
        int m = n + 1;

        int[] calculator = new int[m];
        calculator[1] = 1;

        int[] prevIndex = new int[m];
        prevIndex[0] = -1;

        for (int i = 2; i < m; i++) {
            int prev3Idx = -1;
            int prev2Idx = -1;
            int prevPlus1Idx = i - 1;

            int selectedIndex = prevPlus1Idx;
            int minValue = calculator[prevPlus1Idx] + 1;

            if (i % 3 == 0) {
                prev3Idx = i / 3;
            }
            if (i % 2 == 0) {
                prev2Idx = i / 2;
            }

            if (prev3Idx >= 1 && calculator[prev3Idx] + 1 < minValue) {
                minValue = calculator[prev3Idx] + 1;
                selectedIndex = prev3Idx;
            }
            if (prev2Idx >= 1 && calculator[prev2Idx] + 1 <= minValue) {
                minValue = calculator[prev2Idx] + 1;
                selectedIndex = prev2Idx;
            }

            calculator[i] = minValue;
            prevIndex[i] = selectedIndex;
        }

        //System.out.println(Arrays.toString(calculator));
        //System.out.println(Arrays.toString(prevIndex));

        List<Integer> sequence = new ArrayList<>();

        int idx = m - 1;
        while (prevIndex[idx] != -1) {
            sequence.add(idx);
            idx = prevIndex[idx];
        }

        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

