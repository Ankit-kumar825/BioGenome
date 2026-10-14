package biogenome.m3;

public class SmithWaterman {

    private static final int MATCH = 2;
    private static final int MISMATCH = -1;
    private static final int GAP = -2;

    public static void align(
            String sequence1,
            String sequence2) {

        sequence1 = sequence1.toUpperCase();
        sequence2 = sequence2.toUpperCase();

        int m = sequence1.length();
        int n = sequence2.length();

        int[][] dp =
                new int[m + 1][n + 1];

        int maxScore = 0;
        int maxI = 0;
        int maxJ = 0;

        // Fill DP table
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                int matchScore =
                        dp[i - 1][j - 1]
                        + getMatchScore(
                                sequence1.charAt(i - 1),
                                sequence2.charAt(j - 1)
                        );

                int deleteScore =
                        dp[i - 1][j] + GAP;

                int insertScore =
                        dp[i][j - 1] + GAP;

                dp[i][j] =
                        Math.max(
                                0,
                                Math.max(
                                        matchScore,
                                        Math.max(
                                                deleteScore,
                                                insertScore
                                        )
                                )
                        );

                if (dp[i][j] > maxScore) {

                    maxScore = dp[i][j];

                    maxI = i;
                    maxJ = j;
                }
            }
        }

        // Backtracking
        StringBuilder aligned1 =
                new StringBuilder();

        StringBuilder aligned2 =
                new StringBuilder();

        int i = maxI;
        int j = maxJ;

        while (i > 0
                && j > 0
                && dp[i][j] > 0) {

            if (
                    dp[i][j]
                    == dp[i - 1][j - 1]
                    + getMatchScore(
                            sequence1.charAt(i - 1),
                            sequence2.charAt(j - 1)
                    )
            ) {

                aligned1.append(
                        sequence1.charAt(i - 1)
                );

                aligned2.append(
                        sequence2.charAt(j - 1)
                );

                i--;
                j--;

            } else if (
                    dp[i][j]
                    == dp[i - 1][j] + GAP
            ) {

                aligned1.append(
                        sequence1.charAt(i - 1)
                );

                aligned2.append('-');

                i--;

            } else {

                aligned1.append('-');

                aligned2.append(
                        sequence2.charAt(j - 1)
                );

                j--;
            }
        }

        aligned1.reverse();
        aligned2.reverse();

        printResult(
                sequence1,
                sequence2,
                aligned1.toString(),
                aligned2.toString(),
                maxScore
        );
    }

    private static int getMatchScore(
            char a,
            char b) {

        if (a == b) {
            return MATCH;
        }

        return MISMATCH;
    }

    private static void printResult(
            String sequence1,
            String sequence2,
            String aligned1,
            String aligned2,
            int score) {

        System.out.println(
                "\n========= SMITH-WATERMAN ========="
        );

        System.out.println(
                "Local Sequence Alignment"
        );

        System.out.println(
                "\nSequence 1: "
                + sequence1
        );

        System.out.println(
                "Sequence 2: "
                + sequence2
        );

        System.out.println(
                "\nBest Local Alignment:"
        );

        System.out.println(aligned1);

        System.out.println(aligned2);

        System.out.println(
                "\nBest Alignment Score: "
                + score
        );

        System.out.println(
                "=================================="
        );
    }
}