package biogenome.m5;

public class Clique {

    private int vertices;
    private int[][] graph;

    public Clique(
            int vertices,
            int[][] graph) {

        this.vertices = vertices;
        this.graph = graph;
    }

    // Check whether selected vertices
    // form a clique
    private boolean isClique(
            int[] selected,
            int size) {

        for (int i = 0; i < size; i++) {

            for (int j = i + 1;
                 j < size;
                 j++) {

                if (graph[selected[i]]
                        [selected[j]] == 0) {

                    return false;
                }
            }
        }

        return true;
    }

    // Find maximum clique
    public int[] findMaximumClique() {

        int maxSize = 0;
        int[] bestClique = new int[vertices];

        int totalSubsets =
                1 << vertices;

        for (int mask = 1;
             mask < totalSubsets;
             mask++) {

            int[] selected =
                    new int[vertices];

            int size = 0;

            for (int i = 0;
                 i < vertices;
                 i++) {

                if ((mask & (1 << i)) != 0) {

                    selected[size++] = i;
                }
            }

            if (size > maxSize
                    && isClique(
                            selected,
                            size
                    )) {

                maxSize = size;

                for (int i = 0;
                     i < size;
                     i++) {

                    bestClique[i] =
                            selected[i];
                }
            }
        }

        int[] result =
                new int[maxSize];

        for (int i = 0;
             i < maxSize;
             i++) {

            result[i] =
                    bestClique[i];
        }

        return result;
    }

    // Display result
    public void printResult() {

        int[] clique =
                findMaximumClique();

        System.out.println(
                "\n========== CLIQUE ANALYSIS =========="
        );

        System.out.println(
                "Number of Vertices: "
                + vertices
        );

        System.out.println(
                "Maximum Clique Size: "
                + clique.length
        );

        System.out.println(
                "Clique Members:"
        );

        for (int vertex : clique) {

            System.out.println(
                    "Vertex " + (vertex + 1)
            );
        }

        System.out.println(
                "======================================"
        );
    }
}