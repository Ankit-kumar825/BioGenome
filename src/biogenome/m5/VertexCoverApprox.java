package biogenome.m5;

import java.util.ArrayList;

public class VertexCoverApprox {

    private int vertices;
    private int[][] graph;

    public VertexCoverApprox(
            int vertices,
            int[][] graph) {

        this.vertices = vertices;
        this.graph = graph;
    }

    // 2-Approximation algorithm
    public ArrayList<Integer> findApproximateCover() {

        boolean[] covered =
                new boolean[vertices];

        ArrayList<Integer> cover =
                new ArrayList<>();

        for (int i = 0;
             i < vertices;
             i++) {

            for (int j = i + 1;
                 j < vertices;
                 j++) {

                // If edge exists and is not covered
                if (graph[i][j] == 1
                        && !covered[i]
                        && !covered[j]) {

                    // Select both endpoints
                    cover.add(i);
                    cover.add(j);

                    covered[i] = true;
                    covered[j] = true;
                }
            }
        }

        return cover;
    }

    // Display result
    public void printResult() {

        ArrayList<Integer> cover =
                findApproximateCover();

        System.out.println(
                "\n===== VERTEX COVER 2-APPROXIMATION ====="
        );

        System.out.println(
                "Number of Vertices: "
                + vertices
        );

        System.out.println(
                "Approximate Cover Size: "
                + cover.size()
        );

        System.out.println(
                "Selected Vertices:"
        );

        for (int vertex : cover) {

            System.out.println(
                    "Vertex " + (vertex + 1)
            );
        }

        System.out.println(
                "\nGuarantee: Cover size is "
                + "at most 2 times the optimal solution."
        );

        System.out.println(
                "=========================================="
        );
    }
}