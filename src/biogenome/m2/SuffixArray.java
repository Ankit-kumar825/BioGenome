package biogenome.m2;

import java.util.Arrays;

public class SuffixArray {

    // Build suffix array
    public static int[] buildSuffixArray(String text) {

        String sequence = text.toUpperCase();

        int n = sequence.length();

        Integer[] suffixes = new Integer[n];

        // Store suffix starting positions
        for (int i = 0; i < n; i++) {
            suffixes[i] = i;
        }

        // Sort suffixes lexicographically
        Arrays.sort(
                suffixes,
                (a, b) -> sequence.substring(a)
                        .compareTo(sequence.substring(b))
        );

        // Convert Integer[] to int[]
        int[] suffixArray = new int[n];

        for (int i = 0; i < n; i++) {
            suffixArray[i] = suffixes[i];
        }

        return suffixArray;
    }

    // Print suffix array
    public static void printSuffixArray(String text) {

        String sequence = text.toUpperCase();

        int[] suffixArray =
                buildSuffixArray(sequence);

        System.out.println(
                "\n========== SUFFIX ARRAY =========="
        );

        System.out.println(
                "Genome Sequence: " + sequence
        );

        System.out.println(
                "\nIndex\tSuffix"
        );

        for (int index : suffixArray) {

            System.out.println(
                    index + "\t" + sequence.substring(index)
            );
        }

        System.out.println(
                "=================================="
        );
    }
}