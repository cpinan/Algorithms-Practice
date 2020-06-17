import java.util.Scanner;

public class CarFueling {

    static int computeMinRefills(int dist, int tank, int[] stops) {
        int n = stops.length;

        int[] reqStop = new int[n + 1];
        reqStop[0] = stops[0];
        for (int i = 1; i < n; i++) {
            reqStop[i] = stops[i] - stops[i - 1];
        }
        reqStop[n] = dist - stops[n - 1];

        for (int i = 0; i <= n; i++) {
            if (tank < reqStop[i]) {
                return -1; // Not enough to come here.
            }
        }

        int numRefills = 0;
        int currentTank = tank;

        for (int i = 0; i < n; i++) {
            currentTank -= reqStop[i];
            if (currentTank < reqStop[i + 1]) {
                numRefills++;
                currentTank = tank;
            }
        }

        return numRefills;
    }


    static int computeMinRefills2(int dist, int tank, int[] stops) {
        int n = stops.length;
        int refills = 0;
        int[] requiredTankPerStop = new int[n + 1];

        requiredTankPerStop[0] = stops[0];
        for (int i = 1; i < n; i++) {
            requiredTankPerStop[i] = stops[i] - stops[i - 1];
        }
        requiredTankPerStop[n] = dist - stops[n - 1];

        int currentTank = tank;
        for (int i = 0; i <= n; i++) {
            int currentStop = requiredTankPerStop[i];

            if (tank < currentStop)
                return -1;

            currentTank -= currentStop;
            if (currentTank <= 0) {
                currentTank = tank;
                refills++;
            }
        }

        return refills;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int stops[] = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }
}
