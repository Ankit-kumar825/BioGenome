package biogenome.m2;

import java.util.HashMap;
import java.util.Map;

public class SuffixTree {

    static class Node {

        Map<Character, Node> children;
        int start;
        int end;
        int suffixIndex;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
            this.suffixIndex = -1;
            this.children = new HashMap<>();
        }
    }

    private Node root;
    private String text;

    public SuffixTree(String text) {

        this.text = text.toUpperCase() + "$";
        root = new Node(-1, -1);

        buildTree();
    }

    // Build a simple compressed suffix tree
    private void buildTree() {

        for (int i = 0; i < text.length(); i++) {

            insertSuffix(i);
        }
    }

    // Insert one suffix
    private void insertSuffix(int suffixStart) {

        Node current = root;
        int position = suffixStart;

        while (position < text.length()) {

            char currentChar = text.charAt(position);

            Node child =
                    current.children.get(currentChar);

            if (child == null) {

                Node newNode =
                        new Node(
                                position,
                                text.length() - 1
                        );

                newNode.suffixIndex = suffixStart;

                current.children.put(
                        currentChar,
                        newNode
                );

                return;
            }

            int edgePosition = child.start;

            int matched = 0;

            while (
                    edgePosition + matched <= child.end
                    && position + matched < text.length()
                    && text.charAt(edgePosition + matched)
                    == text.charAt(position + matched)
            ) {

                matched++;
            }

            // Entire edge matched
            if (edgePosition + matched > child.end) {

                position += matched;
                current = child;

            } else {

                // Split existing edge
                Node split =
                        new Node(
                                child.start,
                                child.start + matched - 1
                        );

                current.children.put(
                        currentChar,
                        split
                );

                child.start += matched;

                split.children.put(
                        text.charAt(child.start),
                        child
                );

                Node newLeaf =
                        new Node(
                                position + matched,
                                text.length() - 1
                        );

                newLeaf.suffixIndex =
                        suffixStart;

                split.children.put(
                        text.charAt(position + matched),
                        newLeaf
                );

                return;
            }
        }
    }

    // Search for a substring
    public boolean contains(String pattern) {

        pattern = pattern.toUpperCase();

        int position = 0;
        Node current = root;

        while (position < pattern.length()) {

            Node child =
                    current.children.get(
                            pattern.charAt(position)
                    );

            if (child == null) {
                return false;
            }

            int edgePosition = child.start;

            while (
                    edgePosition <= child.end
                    && position < pattern.length()
            ) {

                if (
                        text.charAt(edgePosition)
                        != pattern.charAt(position)
                ) {

                    return false;
                }

                edgePosition++;
                position++;
            }

            current = child;
        }

        return true;
    }

    // Display tree
    public void displayTree() {

        System.out.println(
                "\n========== SUFFIX TREE =========="
        );

        displayNode(root, "");

        System.out.println(
                "================================="
        );
    }

    private void displayNode(
            Node node,
            String indent) {

        for (Node child :
                node.children.values()) {

            String edge =
                    text.substring(
                            child.start,
                            child.end + 1
                    );

            System.out.println(
                    indent + "└── " + edge
            );

            displayNode(
                    child,
                    indent + "    "
            );
        }
    }

    public String getText() {
        return text;
    }
}