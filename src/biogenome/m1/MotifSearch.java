package biogenome.m1;

import java.util.ArrayList;

public class MotifSearch {

    // KMP Search
    public static void searchUsingKMP(String genomeSequence, String motif) {

        System.out.println("\n========== KMP MOTIF SEARCH ==========");

        System.out.println("Genome Sequence : " + genomeSequence);
        System.out.println("Motif           : " + motif);
        System.out.println("Algorithm       : KMP");

        ArrayList<Integer> positions =
                KMP.search(genomeSequence, motif);

        displayResults(positions);

        System.out.println("======================================");
    }

    // Z-Function Search
    public static void searchUsingZFunction(
            String genomeSequence,
            String motif) {

        System.out.println("\n======= Z-FUNCTION MOTIF SEARCH =======");

        System.out.println("Genome Sequence : " + genomeSequence);
        System.out.println("Motif           : " + motif);
        System.out.println("Algorithm       : Z-Function");

        ArrayList<Integer> positions =
                ZFunction.search(genomeSequence, motif);

        displayResults(positions);

        System.out.println("=======================================");
    }
    public static void searchUsingRabinKarp(
            String genomeSequence,
            String motif) {

        System.out.println("\n======= RABIN-KARP MOTIF SEARCH =======");

        System.out.println("Genome Sequence : " + genomeSequence);
        System.out.println("Motif           : " + motif);
        System.out.println("Algorithm       : Rabin-Karp");

        ArrayList<Integer> positions =
                RabinKarp.search(genomeSequence, motif);

        displayResults(positions);

        System.out.println("=======================================");
    }

    // Common result display method
    private static void displayResults(ArrayList<Integer> positions) {

        if (positions.isEmpty()) {

            System.out.println("\nMotif not found.");

        } else {

            System.out.println("\nMotif found successfully!");
            System.out.println(
                    "Number of occurrences: " + positions.size()
            );

            System.out.println("Positions:");

            for (int position : positions) {
                System.out.println("Index: " + position);
            }
        }
    }
}