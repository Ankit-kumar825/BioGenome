package biogenome.m1;

import java.util.ArrayList;

public class ZFunction {

    // Build Z array
    public static int[] buildZArray(String text) {

        int n = text.length();
        int[] z = new int[n];

        int left = 0;
        int right = 0;

        for (int i = 1; i < n; i++) {

            if (i <= right) {
                z[i] = Math.min(right - i + 1, z[i - left]);
            }

            while (i + z[i] < n &&
                   text.charAt(z[i]) == text.charAt(i + z[i])) {

                z[i]++;
            }

            if (i + z[i] - 1 > right) {
                left = i;
                right = i + z[i] - 1;
            }
        }

        return z;
    }

    // Search pattern using Z algorithm
    public static ArrayList<Integer> search(String text, String pattern) {

        ArrayList<Integer> positions = new ArrayList<>();

        if (text == null || pattern == null || pattern.isEmpty()) {
            return positions;
        }

        text = text.toUpperCase();
        pattern = pattern.toUpperCase();

        String combined = pattern + "$" + text;

        int[] z = buildZArray(combined);

        int patternLength = pattern.length();

        for (int i = patternLength + 1; i < combined.length(); i++) {

            if (z[i] == patternLength) {

                positions.add(i - patternLength - 1);
            }
        }

        return positions;
    }

    // Display search result
    public static void printSearchResult(String text, String pattern) {

        ArrayList<Integer> positions = search(text, pattern);

        System.out.println("\n========== Z-FUNCTION SEARCH ==========");

        System.out.println("Text   : " + text);
        System.out.println("Pattern: " + pattern);

        if (positions.isEmpty()) {

            System.out.println("Pattern not found.");

        } else {

            System.out.println("Pattern found successfully!");
            System.out.println("Number of occurrences: " + positions.size());

            System.out.println("Positions:");

            for (int position : positions) {
                System.out.println("Index: " + position);
            }
        }

        System.out.println("=======================================");
    }
}