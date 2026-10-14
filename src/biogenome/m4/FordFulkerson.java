package biogenome.m4;

import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {

    private int vertices;

    public FordFulkerson(int vertices) {
        this.vertices = vertices;
    }

    // BFS finds an augmenting path
    private boolean bfs(
            int[][] residualGraph,
            int source,
            int sink,
            int[] parent) {

        boolean[] visited =
                new boolean[vertices];

        Queue<Integer> queue =
                new LinkedList<>();

        queue.add(source);

        visited[source] = true;

        parent[source] = -1;

        while (!queue.isEmpty()) {

            int current =
                    queue.poll();

            for (int next = 0;
                 next < vertices;
                 next++) {

                if (!visited[next]
                        && residualGraph[current][next] > 0) {

                    queue.add(next);

                    parent[next] = current;

                    visited[next] = true;

                    if (next == sink) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // Calculate maximum flow
    public int maxFlow(
            int[][] graph,
            int source,
            int sink) {

        int[][] residualGraph =
                new int[vertices][vertices];

        for (int i = 0;
             i < vertices;
             i++) {

            for (int j = 0;
                 j < vertices;
                 j++) {

                residualGraph[i][j] =
                        graph[i][j];
            }
        }

        int[] parent =
                new int[vertices];

        int maxFlow = 0;

        while (
                bfs(
                        residualGraph,
                        source,
                        sink,
                        parent
                )
        ) {

            int pathFlow =
                    Integer.MAX_VALUE;

            int current = sink;

            while (current != source) {

                int previous =
                        parent[current];

                pathFlow =
                        Math.min(
                                pathFlow,
                                residualGraph[
                                        previous
                                ][current]
                        );

                current = previous;
            }

            current = sink;

            while (current != source) {

                int previous =
                        parent[current];

                residualGraph[
                        previous
                ][current] -= pathFlow;

                residualGraph[
                        current
                ][previous] += pathFlow;

                current = previous;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    public void printMaxFlow(
            int[][] graph,
            int source,
            int sink) {

        int result =
                maxFlow(
                        graph,
                        source,
                        sink
                );

        System.out.println(
                "\n========== FORD-FULKERSON =========="
        );

        System.out.println(
                "Source : " + source
        );

        System.out.println(
                "Sink   : " + sink
        );

        System.out.println(
                "Maximum Flow: " + result
        );

        System.out.println(
                "===================================="
        );
    }
}