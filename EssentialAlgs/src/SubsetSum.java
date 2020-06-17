/**
 * @author Carlos Piñan
 * @explanation Given N numbers. Check if there is a subset of them, with the
 * sum equals to the target S
 * <p>
 * N <= 20
 */
public class SubsetSum {

    public boolean subsetSum(final int N, final long target) {
        for (int mask = 0; mask < (1 << N); mask++) {
            long subset_sum = 0;
            for (int i = 0; i < N; i++) {
                if ((mask & (1 << i)) != 0) {
                    subset_sum += i;
                }
            }
            if (subset_sum == target) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
