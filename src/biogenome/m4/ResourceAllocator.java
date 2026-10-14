package biogenome.m4;

public class ResourceAllocator {

    // ===============================
    // FORD-FULKERSON
    // ===============================

    public static int allocateUsingFordFulkerson(
            int[][] network,
            int source,
            int sink) {

        FordFulkerson fordFulkerson =
                new FordFulkerson(
                        network.length
                );

        return fordFulkerson.maxFlow(
                network,
                source,
                sink
        );
    }

    // ===============================
    // EDMONDS-KARP
    // ===============================

    public static int allocateUsingEdmondsKarp(
            int[][] network,
            int source,
            int sink) {

        EdmondsKarp edmondsKarp =
                new EdmondsKarp(
                        network.length
                );

        return edmondsKarp.maxFlow(
                network,
                source,
                sink
        );
    }

    // ===============================
    // DINIC
    // ===============================

    public static int allocateUsingDinic(
            int[][] network,
            int source,
            int sink) {

        Dinic dinic =
                new Dinic(
                        network.length
                );

        // Add all network edges
        for (int i = 0;
             i < network.length;
             i++) {

            for (int j = 0;
                 j < network[i].length;
                 j++) {

                if (network[i][j] > 0) {

                    dinic.addEdge(
                            i,
                            j,
                            network[i][j]
                    );
                }
            }
        }

        return dinic.maxFlow(
                source,
                sink
        );
    }

    // ===============================
    // DISPLAY COMPARISON
    // ===============================

    public static void compareAlgorithms(
            int[][] network,
            int source,
            int sink) {

        int fordFulkerson =
                allocateUsingFordFulkerson(
                        network,
                        source,
                        sink
                );

        int edmondsKarp =
                allocateUsingEdmondsKarp(
                        network,
                        source,
                        sink
                );

        int dinic =
                allocateUsingDinic(
                        network,
                        source,
                        sink
                );

        System.out.println(
                "\n======= RESOURCE ALLOCATION ======="
        );

        System.out.println(
                "Ford-Fulkerson Maximum Flow : "
                + fordFulkerson
        );

        System.out.println(
                "Edmonds-Karp Maximum Flow   : "
                + edmondsKarp
        );

        System.out.println(
                "Dinic Maximum Flow           : "
                + dinic
        );

        System.out.println(
                "\nAll algorithms should produce "
                + "the same maximum flow."
        );

        System.out.println(
                "==================================="
        );
    }

    // ===============================
    // RESEARCHER-EXPERIMENT ASSIGNMENT
    // ===============================

    public static void assignResearchers(
            int researchers,
            int experiments,
            int[][] connections) {

        BipartiteMatching matching =
                new BipartiteMatching(
                        researchers,
                        experiments
                );

        for (int i = 0;
             i < researchers;
             i++) {

            for (int j = 0;
                 j < experiments;
                 j++) {

                if (connections[i][j] == 1) {

                    matching.addConnection(
                            i,
                            j
                    );
                }
            }
        }

        matching.printMatching();
    }
}