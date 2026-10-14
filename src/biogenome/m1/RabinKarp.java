package biogenome.m1;

import java.util.ArrayList;

public class RabinKarp {

    private static final int BASE = 256;
    private static final int PRIME = 101;

    public static ArrayList<Integer> search(
            String text,
            String pattern) {

        ArrayList<Integer> positions = new ArrayList<>();

        if (text == null || pattern == null || pattern.isEmpty()) {
            return positions;
        }

        text = text.toUpperCase();
        pattern = pattern.toUpperCase();

        int n = text.length();
        int m = pattern.length();

        if (m > n) {
            return positions;
        }

        int patternHash = 0;
        int textHash = 0;

        int highestPower = 1;

        // BASE^(m-1)
        for (int i = 0; i < m - 1; i++) {
            highestPower = (highestPower * BASE) % PRIME;
        }

        // Calculate initial hashes
        for (int i = 0; i < m; i++) {

            patternHash =
                    (BASE * patternHash + pattern.charAt(i))
                    % PRIME;

            textHash =
                    (BASE * textHash + text.charAt(i))
                    % PRIME;
        }

        // Slide pattern over text
        for (int i = 0; i <= n - m; i++) {

            // If hash matches, verify characters
            if (patternHash == textHash) {

                boolean match = true;

                for (int j = 0; j < m; j++) {

                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    positions.add(i);
                }
            }

            // Calculate next window hash
            if (i < n - m) {

                textHash =
                        (BASE * (textHash
                        - text.charAt(i) * highestPower)
                        + text.charAt(i + m))
                        % PRIME;

                if (textHash < 0) {
                    textHash += PRIME;
                }
            }
        }

        return positions;
    }

    public static void printSearchResult(
            String text,
            String pattern) {

        ArrayList<Integer> positions =
                search(text, pattern);

        System.out.println("\n========== RABIN-KARP SEARCH ==========");

        System.out.println("Text   : " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("Algorithm: Rabin-Karp");

        if (positions.isEmpty()) {

            System.out.println("Pattern not found.");

        } else {

            System.out.println("Pattern found successfully!");

            System.out.println(
                    "Number of occurrences: "
                    + positions.size()
            );

            System.out.println("Positions:");

            for (int position : positions) {
                System.out.println("Index: " + position);
            }
        }

        System.out.println("=======================================");
    }
}