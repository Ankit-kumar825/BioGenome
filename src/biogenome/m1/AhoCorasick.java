package biogenome.m1;

import java.util.*;

public class AhoCorasick {

    // Trie node
    static class Node {

        Node[] children = new Node[4];

        Node failure;

        ArrayList<String> output = new ArrayList<>();
    }

    private Node root;

    public AhoCorasick() {
        root = new Node();
    }

    // Convert DNA character into index
    private int getIndex(char ch) {

        switch (ch) {

            case 'A':
                return 0;

            case 'C':
                return 1;

            case 'G':
                return 2;

            case 'T':
                return 3;

            default:
                return -1;
        }
    }

    // Insert a pattern into Trie
    public void addPattern(String pattern) {

        pattern = pattern.toUpperCase();

        Node current = root;

        for (int i = 0; i < pattern.length(); i++) {

            char ch = pattern.charAt(i);

            int index = getIndex(ch);

            if (index == -1) {
                continue;
            }

            if (current.children[index] == null) {
                current.children[index] = new Node();
            }

            current = current.children[index];
        }

        current.output.add(pattern);
    }

    // Build failure links using BFS
    public void buildFailureLinks() {

        Queue<Node> queue = new LinkedList<>();

        root.failure = root;

        for (int i = 0; i < 4; i++) {

            if (root.children[i] != null) {

                root.children[i].failure = root;

                queue.add(root.children[i]);

            } else {

                root.children[i] = root;
            }
        }

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            for (int i = 0; i < 4; i++) {

                Node child = current.children[i];

                if (child == null) {
                    continue;
                }

                child.failure =
                        current.failure.children[i];

                child.output.addAll(
                        child.failure.output
                );

                queue.add(child);
            }
        }
    }

    // Search multiple patterns
    public Map<String, ArrayList<Integer>> search(
            String text,
            ArrayList<String> patterns) {

        Map<String, ArrayList<Integer>> results =
                new LinkedHashMap<>();

        text = text.toUpperCase();

        // Add patterns
        for (String pattern : patterns) {

            pattern = pattern.toUpperCase();

            results.put(
                    pattern,
                    new ArrayList<>()
            );

            addPattern(pattern);
        }

        // Build failure links
        buildFailureLinks();

        Node current = root;

        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            int index = getIndex(ch);

            if (index == -1) {
                current = root;
                continue;
            }

            current = current.children[index];

            for (String pattern : current.output) {

                int position =
                        i - pattern.length() + 1;

                results.get(pattern).add(position);
            }
        }

        return results;
    }

    // Display search results
    public static void printSearchResult(
            String text,
            ArrayList<String> patterns) {

        AhoCorasick ac = new AhoCorasick();

        Map<String, ArrayList<Integer>> results =
                ac.search(text, patterns);

        System.out.println(
                "\n======= AHO-CORASICK SEARCH ======="
        );

        System.out.println("Genome: " + text);

        System.out.println(
                "Number of motifs: " + patterns.size()
        );

        for (String pattern : patterns) {

            ArrayList<Integer> positions =
                    results.get(pattern.toUpperCase());

            System.out.println("\nMotif: " + pattern);

            if (positions == null || positions.isEmpty()) {

                System.out.println("Not found.");

            } else {

                System.out.println(
                        "Occurrences: "
                        + positions.size()
                );

                System.out.println(
                        "Positions: " + positions
                );
            }
        }

        System.out.println(
                "==================================="
        );
    }
}