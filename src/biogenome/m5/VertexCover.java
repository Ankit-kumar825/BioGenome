package biogenome.m5;

import java.util.ArrayList;

public class VertexCover {

    private int vertices;
    private int[][] graph;

    public VertexCover(
            int vertices,
            int[][] graph) {

        this.vertices = vertices;
        this.graph = graph;
    }

    // Check whether selected vertices
    // cover all edges
    private boolean coversAllEdges(
            boolean[] selected) {

        for (int i = 0;
             i < vertices;
             i++) {

            for (int j = i + 1;
                 j < vertices;
                 j++) {

                if (graph[i][j] == 1) {

                    if (!selected[i]
                            && !selected[j]) {

                        return false;
                    }
                }
            }
        }

        return true;
    }

    // Find minimum vertex cover
    public ArrayList<Integer> findMinimumVertexCover() {

        ArrayList<Integer> bestCover =
                new ArrayList<>();

        int totalSubsets =
                1 << vertices;

        for (int mask = 0;
             mask < totalSubsets;
             mask++) {

            int size =
                    Integer.bitCount(mask);

            if (!bestCover.isEmpty()
                    && size >= bestCover.size()) {

                continue;
            }

            boolean[] selected =
                    new boolean[vertices];

            ArrayList<Integer> currentCover =
                    new ArrayList<>();

            for (int i = 0;
                 i < vertices;
                 i++) {

                if ((mask & (1 << i)) != 0) {

                    selected[i] = true;

                    currentCover.add(i);
                }
            }

            if (coversAllEdges(selected)) {

                bestCover =
                        currentCover;
            }
        }

        return bestCover;
    }

    // Display result
    public void printResult() {

        ArrayList<Integer> cover =
                findMinimumVertexCover();

        System.out.println(
                "\n======= VERTEX COVER ANALYSIS ======="
        );

        System.out.println(
                "Number of Vertices: "
                + vertices
        );

        System.out.println(
                "Minimum Vertex Cover Size: "
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
                "====================================="
        );
    }
}