package biogenome.m3;

import java.util.Arrays;

public class BitmaskDP {

    private static final int INF = 1000000000;

    /*
     * Finds minimum cost to assign every task
     * to a different resource.
     *
     * cost[i][j] =
     * cost of assigning resource j to task i
     */
    public static int minimumAssignmentCost(int[][] cost) {

        int n = cost.length;

        if (n == 0) {
            return 0;
        }

        int[][] dp =
                new int[1 << n][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int mask = 0;
             mask < (1 << n);
             mask++) {

            int assigned =
                    Integer.bitCount(mask);

            if (assigned >= n) {
                continue;
            }

            for (int resource = 0;
                 resource < n;
                 resource++) {

                if ((mask & (1 << resource)) == 0) {

                    int newMask =
                            mask | (1 << resource);

                    int newCost =
                            dp[mask][assigned]
                            + cost[assigned][resource];

                    dp[newMask][assigned + 1] =
                            Math.min(
                                    dp[newMask][assigned + 1],
                                    newCost
                            );
                }
            }
        }

        return dp[(1 << n) - 1][n];
    }

    public static void printAssignmentResult(
            int[][] cost) {

        int result =
                minimumAssignmentCost(cost);

        System.out.println(
                "\n========== BITMASK DP =========="
        );

        System.out.println(
                "Minimum Assignment Cost: "
                + result
        );

        System.out.println(
                "Number of Tasks: "
                + cost.length
        );

        System.out.println(
                "================================"
        );
    }
}