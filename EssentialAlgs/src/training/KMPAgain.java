package training;

import java.util.Arrays;

/**
 * @author Carlos Piñan
 */
public class KMPAgain {

    private int find(final String text, final String pattern) {
        int[] patternArray = pattern(pattern);
        int i = 0, j = 0;
        while (i < text.length() && j < patternArray.length) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if (j > 0) {
                    j = patternArray[j - 1];
                } else {
                    i++;
                }
            }
        }
        if (j < patternArray.length) {
            System.out.println("Pattern not found");
        } else {
            System.out.println("Pattern was found");
        }
        System.out.println(i + " ; " + j + " ; " + (i - j));
        return j < patternArray.length ? -1 : (i - j);
    }

    private int[] pattern(final String pattern) {
        int[] arr = new int[pattern.length()];
        int i = 1;
        int j = 0;
        while (i < arr.length) {
            if (pattern.charAt(i) != pattern.charAt(j)) {
                if (j == 0) {
                    i++;
                } else {
                    j = arr[j - 1];
                }
            } else {
                arr[i] = j + 1;
                i++;
                j++;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        KMPAgain m = new KMPAgain();
        m.find("abxabcabcabyppppp", "abcaby");
        m.find("abcdefghi", "abcf");
    }
}
