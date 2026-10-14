package biogenome.m2;

public class KasaiLCP {

    // Build LCP array using Kasai Algorithm
    public static int[] buildLCP(
            String text,
            int[] suffixArray) {

        int n = text.length();

        int[] lcp = new int[n];

        int[] rank = new int[n];

        // Store rank of every suffix
        for (int i = 0; i < n; i++) {

            rank[suffixArray[i]] = i;
        }

        int k = 0;

        // Calculate LCP
        for (int i = 0; i < n; i++) {

            int currentRank = rank[i];

            // Last suffix has no next suffix
            if (currentRank == n - 1) {

                k = 0;
                continue;
            }

            int j =
                    suffixArray[currentRank + 1];

            // Compare characters
            while (i + k < n &&
                   j + k < n &&
                   text.charAt(i + k)
                   == text.charAt(j + k)) {

                k++;
            }

            lcp[currentRank] = k;

            if (k > 0) {
                k--;
            }
        }

        return lcp;
    }

    // Display LCP array
    public static void printLCP(
            String text,
            int[] suffixArray) {

        text = text.toUpperCase();

        int[] lcp =
                buildLCP(
                        text,
                        suffixArray
                );

        System.out.println(
                "\n========== KASAI LCP =========="
        );

        System.out.println(
                "Genome Sequence: " + text
        );

        System.out.println(
                "\nIndex\tSuffix\t\tLCP"
        );

        for (int i = 0;
             i < suffixArray.length;
             i++) {

            int suffixIndex =
                    suffixArray[i];

            String suffix =
                    text.substring(suffixIndex);

            System.out.println(
                    i
                    + "\t"
                    + suffix
                    + "\t\t"
                    + lcp[i]
            );
        }

        System.out.println(
                "==============================="
        );
    }

    // Find longest repeated substring
    public static String findLongestRepeatedSubstring(
            String text,
            int[] suffixArray) {

        text = text.toUpperCase();

        int[] lcp =
                buildLCP(
                        text,
                        suffixArray
                );

        int maxLCP = 0;
        int maxIndex = 0;

        for (int i = 0; i < lcp.length; i++) {

            if (lcp[i] > maxLCP) {

                maxLCP = lcp[i];
                maxIndex = i;
            }
        }

        if (maxLCP == 0) {
            return "";
        }

        return text.substring(
                suffixArray[maxIndex],
                suffixArray[maxIndex] + maxLCP
        );
    }
}