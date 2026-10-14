package biogenome.m4;

import java.util.*;

public class Dinic {

    static class Edge {

        int to;
        int capacity;
        int reverse;

        Edge(int to, int capacity, int reverse) {
            this.to = to;
            this.capacity = capacity;
            this.reverse = reverse;
        }
    }

    private int vertices;

    private ArrayList<Edge>[] graph;

    private int[] level;

    private int[] pointer;

    @SuppressWarnings("unchecked")
    public Dinic(int vertices) {

        this.vertices = vertices;

        graph =
                new ArrayList[vertices];

        for (int i = 0;
             i < vertices;
             i++) {

            graph[i] =
                    new ArrayList<>();
        }

        level =
                new int[vertices];

        pointer =
                new int[vertices];
    }

    // Add directed edge
    public void addEdge(
            int from,
            int to,
            int capacity) {

        Edge forward =
                new Edge(
                        to,
                        capacity,
                        graph[to].size()
                );

        Edge backward =
                new Edge(
                        from,
                        0,
                        graph[from].size()
                );

        graph[from].add(forward);

        graph[to].add(backward);
    }

    // Build level graph using BFS
    private boolean buildLevelGraph(
            int source,
            int sink) {

        Arrays.fill(
                level,
                -1
        );

        Queue<Integer> queue =
                new LinkedList<>();

        queue.add(source);

        level[source] = 0;

        while (!queue.isEmpty()) {

            int current =
                    queue.poll();

            for (Edge edge :
                    graph[current]) {

                if (edge.capacity > 0
                        && level[edge.to] == -1) {

                    level[edge.to] =
                            level[current] + 1;

                    queue.add(edge.to);
                }
            }
        }

        return level[sink] != -1;
    }

    // Send blocking flow
    private int sendFlow(
            int current,
            int sink,
            int flow) {

        if (current == sink) {
            return flow;
        }

        for (int i = pointer[current];
             i < graph[current].size();
             i++) {

            pointer[current] = i;

            Edge edge =
                    graph[current].get(i);

            if (edge.capacity > 0
                    && level[edge.to]
                    == level[current] + 1) {

                int pushedFlow =
                        sendFlow(
                                edge.to,
                                sink,
                                Math.min(
                                        flow,
                                        edge.capacity
                                )
                        );

                if (pushedFlow > 0) {

                    edge.capacity -= pushedFlow;

                    graph[edge.to]
                            .get(edge.reverse)
                            .capacity += pushedFlow;

                    return pushedFlow;
                }
            }
        }

        return 0;
    }

    // Calculate maximum flow
    public int maxFlow(
            int source,
            int sink) {

        int maxFlow = 0;

        while (
                buildLevelGraph(
                        source,
                        sink
                )
        ) {

            Arrays.fill(
                    pointer,
                    0
            );

            int flow;

            while (
                    (flow =
                            sendFlow(
                                    source,
                                    sink,
                                    Integer.MAX_VALUE
                            )) > 0
            ) {

                maxFlow += flow;
            }
        }

        return maxFlow;
    }

    public void printMaxFlow(
            int source,
            int sink) {

        int result =
                maxFlow(
                        source,
                        sink
                );

        System.out.println(
                "\n========== DINIC'S ALGORITHM =========="
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
                "========================================"
        );
    }
}