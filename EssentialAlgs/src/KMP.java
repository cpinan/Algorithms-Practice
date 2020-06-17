import java.util.Arrays;

/**
 * @author Carlos Piñan
 * Knuth-Morris-Pratt Algorithm (KMP)
 */
public class KMP {

    public int patternMatch(final String text, final String pattern) {
        int n = text.length();
        int m = pattern.length();

        int[] patternArr = new int[m];
        int i = 1;
        int j = 0;
        while (i < m) {
            char charI = pattern.charAt(i);
            char charJ = pattern.charAt(j);
            if (charI != charJ) {
                if (j > 0) {
                    j = patternArr[j - 1];
                } else {
                    i++;
                }
            } else {
                patternArr[i] = j + 1;
                i++;
                j++;
            }
        }

        System.out.println(pattern);
        System.out.println(Arrays.toString(patternArr));

        i = 0;
        j = 0;
        while (i < n && j < m) {
            char charText = text.charAt(i);
            char charPattern = pattern.charAt(j);
            // System.out.println("Compare " + charText + " with " + charPattern);
            if (charText == charPattern) {
                i++;
                j++;
            } else {
                if (j > 0) {
                    j = patternArr[j - 1];
                } else {
                    i++;
                }
            }
        }

        if (j >= m) {
            System.out.println("Pattern found in: " + (i - j));
            System.out.println(text.substring((i - j), (i - j) + j));
            System.out.println(j + " - patternWasFound");
        }

        System.out.println();

        return -1;
    }

    public static void main(String[] args) {
        KMP m = new KMP();
        // m.patternMatch("", "abcdabca");
        // m.patternMatch("", "aabaabaaa");
        m.patternMatch("abxabcabcaby", "abcaby");
        m.patternMatch("ababcabyxpqoaop", "abcaby");
    }
}
