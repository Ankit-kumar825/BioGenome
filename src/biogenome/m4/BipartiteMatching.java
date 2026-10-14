package biogenome.m4;

import java.util.Arrays;

public class BipartiteMatching {

    private int researchers;
    private int experiments;

    private int[][] graph;

    public BipartiteMatching(
            int researchers,
            int experiments) {

        this.researchers = researchers;
        this.experiments = experiments;

        graph =
                new int[researchers][experiments];
    }

    // Add researcher -> experiment connection
    public void addConnection(
            int researcher,
            int experiment) {

        if (researcher < 0
                || researcher >= researchers
                || experiment < 0
                || experiment >= experiments) {

            return;
        }

        graph[researcher][experiment] = 1;
    }

    // DFS to find an experiment
    private boolean dfs(
            int researcher,
            boolean[] visited,
            int[] assignedExperiment) {

        for (int experiment = 0;
             experiment < experiments;
             experiment++) {

            if (graph[researcher][experiment] == 1
                    && !visited[experiment]) {

                visited[experiment] = true;

                if (assignedExperiment[experiment] == -1
                        || dfs(
                                assignedExperiment[experiment],
                                visited,
                                assignedExperiment
                        )) {

                    assignedExperiment[experiment] =
                            researcher;

                    return true;
                }
            }
        }

        return false;
    }

    // Find maximum matching
    public int maximumMatching() {

        int[] assignedExperiment =
                new int[experiments];

        Arrays.fill(
                assignedExperiment,
                -1
        );

        int matching = 0;

        for (int researcher = 0;
             researcher < researchers;
             researcher++) {

            boolean[] visited =
                    new boolean[experiments];

            if (
                    dfs(
                            researcher,
                            visited,
                            assignedExperiment
                    )
            ) {

                matching++;
            }
        }

        return matching;
    }

    // Display assignments
    public void printMatching() {

        int[] assignedExperiment =
                new int[experiments];

        Arrays.fill(
                assignedExperiment,
                -1
        );

        int matching = 0;

        for (int researcher = 0;
             researcher < researchers;
             researcher++) {

            boolean[] visited =
                    new boolean[experiments];

            if (
                    dfs(
                            researcher,
                            visited,
                            assignedExperiment
                    )
            ) {

                matching++;
            }
        }

        System.out.println(
                "\n======= BIPARTITE MATCHING ======="
        );

        System.out.println(
                "Researchers : "
                + researchers
        );

        System.out.println(
                "Experiments : "
                + experiments
        );

        System.out.println(
                "\nAssignments:"
        );

        for (int experiment = 0;
             experiment < experiments;
             experiment++) {

            if (assignedExperiment[experiment] != -1) {

                System.out.println(
                        "Researcher "
                        + (assignedExperiment[experiment] + 1)
                        + " -> Experiment "
                        + (experiment + 1)
                );
            }
        }

        System.out.println(
                "\nMaximum Matching: "
                + matching
        );

        System.out.println(
                "================================="
        );
    }
}