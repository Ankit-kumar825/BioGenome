package biogenome.m3;

public class Levenshtein {

    // Calculate Levenshtein Distance
    public static int calculate(
            String sequence1,
            String sequence2) {

        sequence1 = sequence1.toUpperCase();
        sequence2 = sequence2.toUpperCase();

        int m = sequence1.length();
        int n = sequence2.length();

        int[][] dp = new int[m + 1][n + 1];

        // First column
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // First row
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill DP table
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (sequence1.charAt(i - 1)
                        == sequence2.charAt(j - 1)) {

                    dp[i][j] =
                            dp[i - 1][j - 1];

                } else {

                    int insert =
                            dp[i][j - 1] + 1;

                    int delete =
                            dp[i - 1][j] + 1;

                    int replace =
                            dp[i - 1][j - 1] + 1;

                    dp[i][j] =
                            Math.min(
                                    insert,
                                    Math.min(
                                            delete,
                                            replace
                                    )
                            );
                }
            }
        }

        return dp[m][n];
    }

    // Display comparison result
    public static void printDistance(
            String sequence1,
            String sequence2) {

        int distance =
                calculate(
                        sequence1,
                        sequence2
                );

        System.out.println(
                "\n========== LEVENSHTEIN DISTANCE =========="
        );

        System.out.println(
                "Sequence 1 : " + sequence1
        );

        System.out.println(
                "Sequence 2 : " + sequence2
        );

        System.out.println(
                "Minimum Changes Required: "
                + distance
        );

        System.out.println(
                "==========================================="
        );
    }
}