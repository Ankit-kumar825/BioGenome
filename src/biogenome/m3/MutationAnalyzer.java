package biogenome.m3;

public class MutationAnalyzer {

    public static void analyze(
            String original,
            String modified) {

        original = original.toUpperCase();
        modified = modified.toUpperCase();

        System.out.println(
                "\n========== MUTATION ANALYSIS =========="
        );

        System.out.println(
                "Original Sequence : " + original
        );

        System.out.println(
                "Modified Sequence : " + modified
        );

        int minLength =
                Math.min(
                        original.length(),
                        modified.length()
                );

        int substitutions = 0;

        // Compare common positions
        for (int i = 0; i < minLength; i++) {

            if (original.charAt(i)
                    != modified.charAt(i)) {

                substitutions++;

                System.out.println(
                        "Substitution at position "
                        + i
                        + ": "
                        + original.charAt(i)
                        + " -> "
                        + modified.charAt(i)
                );
            }
        }

        // Insertion
        if (modified.length() > original.length()) {

            int insertionCount =
                    modified.length()
                    - original.length();

            System.out.println(
                    "\nInsertion detected."
            );

            System.out.println(
                    "Number of inserted bases: "
                    + insertionCount
            );
        }

        // Deletion
        else if (original.length() > modified.length()) {

            int deletionCount =
                    original.length()
                    - modified.length();

            System.out.println(
                    "\nDeletion detected."
            );

            System.out.println(
                    "Number of deleted bases: "
                    + deletionCount
            );
        }

        System.out.println(
                "\nTotal substitutions: "
                + substitutions
        );

        int levenshteinDistance =
                Levenshtein.calculate(
                        original,
                        modified
                );

        int damerauDistance =
                DamerauLevenshtein.calculate(
                        original,
                        modified
                );

        System.out.println(
                "Levenshtein Distance: "
                + levenshteinDistance
        );

        System.out.println(
                "Damerau-Levenshtein Distance: "
                + damerauDistance
        );

        System.out.println(
                "======================================="
        );
    }
}