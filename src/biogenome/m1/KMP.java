package biogenome.m1;

import java.util.ArrayList;

public class KMP {

    // Build LPS (Longest Prefix Suffix) array
    public static int[] buildLPS(String pattern) {

        int[] lps = new int[pattern.length()];

        int length = 0;
        int i = 1;

        while (i < pattern.length()) {

            if (pattern.charAt(i) == pattern.charAt(length)) {

                length++;
                lps[i] = length;
                i++;

            } else {

                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    // KMP pattern searching
    public static ArrayList<Integer> search(String text, String pattern) {

        ArrayList<Integer> positions = new ArrayList<>();

        if (text == null || pattern == null || pattern.isEmpty()) {
            return positions;
        }

        text = text.toUpperCase();
        pattern = pattern.toUpperCase();

        int[] lps = buildLPS(pattern);

        int i = 0;
        int j = 0;

        while (i < text.length()) {

            if (text.charAt(i) == pattern.charAt(j)) {

                i++;
                j++;

                if (j == pattern.length()) {

                    positions.add(i - j);

                    j = lps[j - 1];
                }

            } else {

                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return positions;
    }

    // Display KMP search result
    public static void printSearchResult(String text, String pattern) {

        ArrayList<Integer> positions = search(text, pattern);

        System.out.println("\n========== KMP SEARCH ==========");

        System.out.println("Text   : " + text);
        System.out.println("Pattern: " + pattern);

        if (positions.isEmpty()) {

            System.out.println("Pattern not found.");

        } else {

            System.out.println("Pattern found at positions:");

            for (int position : positions) {
                System.out.println("Index: " + position);
            }
        }

        System.out.println("================================");
    }
}