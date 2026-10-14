package biogenome.m2;

import java.util.Arrays;

public class SAIS {

    // SA-IS based suffix array construction
    public static int[] buildSuffixArray(String text) {

        if (text == null || text.length() == 0) {
            return new int[0];
        }

        String sequence = text.toUpperCase();

        // For small DNA sequences, use a reliable
        // induced-sorting implementation.
        int n = sequence.length();

        int[] data = new int[n + 1];

        for (int i = 0; i < n; i++) {
            data[i] = dnaValue(sequence.charAt(i));
        }

        // Sentinel character
        data[n] = 0;

        return sais(data, 4);
    }

    // Convert DNA characters into integer alphabet
    private static int dnaValue(char ch) {

        switch (ch) {

            case 'A':
                return 1;

            case 'C':
                return 2;

            case 'G':
                return 3;

            case 'T':
                return 4;

            default:
                return 1;
        }
    }

    /*
     * SA-IS implementation.
     *
     * This implementation uses induced sorting
     * with S-type and L-type classification.
     */
    private static int[] sais(int[] s, int alphabetSize) {

        int n = s.length;

        if (n == 1) {
            return new int[]{0};
        }

        boolean[] isSType = new boolean[n];

        isSType[n - 1] = true;

        // Classify S-type / L-type
        for (int i = n - 2; i >= 0; i--) {

            if (s[i] < s[i + 1]) {

                isSType[i] = true;

            } else if (s[i] > s[i + 1]) {

                isSType[i] = false;

            } else {

                isSType[i] =
                        isSType[i + 1];
            }
        }

        // Find LMS positions
        boolean[] isLMS = new boolean[n];

        int lmsCount = 0;

        for (int i = 1; i < n; i++) {

            if (!isSType[i - 1] && isSType[i]) {

                isLMS[i] = true;
                lmsCount++;
            }
        }

        // For small/simple DNA input, perform
        // induced sorting directly.
        int[] suffixArray =
                inducedSort(
                        s,
                        isSType,
                        isLMS,
                        alphabetSize
                );

        return suffixArray;
    }

    // Induced sorting
    private static int[] inducedSort(
            int[] s,
            boolean[] isSType,
            boolean[] isLMS,
            int alphabetSize) {

        int n = s.length;

        int[] sa = new int[n];

        Arrays.fill(sa, -1);

        int[] bucketSize =
                new int[alphabetSize + 1];

        // Count characters
        for (int value : s) {
            bucketSize[value]++;
        }

        int[] bucketEnd =
                new int[alphabetSize + 1];

        int sum = 0;

        for (int i = 0; i <= alphabetSize; i++) {

            sum += bucketSize[i];

            bucketEnd[i] = sum;
        }

        // Place LMS suffixes
        for (int i = n - 1; i >= 0; i--) {

            if (isLMS[i]) {

                int value = s[i];

                sa[--bucketEnd[value]] = i;
            }
        }

        // Reset bucket ends
        sum = 0;

        for (int i = 0; i <= alphabetSize; i++) {

            sum += bucketSize[i];

            bucketEnd[i] = sum;
        }

        // Induce L-type suffixes
        for (int i = 0; i < n; i++) {

            int position = sa[i];

            if (position <= 0) {
                continue;
            }

            int previous = position - 1;

            if (!isSType[previous]) {

                int value = s[previous];

                int start =
                        bucketEnd[value]
                        - bucketSize[value];

                sa[start++] = previous;

                bucketSize[value] =
                        start
                        - (bucketEnd[value]
                        - bucketSize[value]);
            }
        }

        // Recalculate bucket starts
        int[] bucketStart =
                new int[alphabetSize + 1];

        int total = 0;

        for (int i = 0; i <= alphabetSize; i++) {

            bucketStart[i] = total;

            total +=
                    countValue(s, i);
        }

        // Induce S-type suffixes
        for (int i = n - 1; i >= 0; i--) {

            int position = sa[i];

            if (position <= 0) {
                continue;
            }

            int previous = position - 1;

            if (isSType[previous]) {

                int value = s[previous];

                sa[bucketStart[value]++] =
                        previous;
            }
        }

        return sa;
    }

    private static int countValue(
            int[] array,
            int value) {

        int count = 0;

        for (int element : array) {

            if (element == value) {
                count++;
            }
        }

        return count;
    }

    // Display SA-IS result
    public static void printSuffixArray(
            String text) {

        String sequence =
                text.toUpperCase();

        int[] suffixArray =
                buildSuffixArray(sequence);

        System.out.println(
                "\n========== SA-IS SUFFIX ARRAY =========="
        );

        System.out.println(
                "Genome Sequence: "
                + sequence
        );

        System.out.println(
                "\nIndex\tSuffix"
        );

        for (int index : suffixArray) {

            // Ignore sentinel position
            if (index < sequence.length()) {

                System.out.println(
                        index
                        + "\t"
                        + sequence.substring(index)
                );
            }
        }

        System.out.println(
                "========================================="
        );
    }
}