package biogenome.m2;

import java.util.HashMap;
import java.util.Map;

public class SuffixAutomaton {

    // State of suffix automaton
    static class State {

        int length;
        State suffixLink;

        Map<Character, State> next;

        State() {
            length = 0;
            suffixLink = null;
            next = new HashMap<>();
        }
    }

    private State root;
    private State last;

    private String text;

    public SuffixAutomaton(String text) {

        this.text = text.toUpperCase();

        root = new State();

        last = root;

        buildAutomaton();
    }

    // Build suffix automaton
    private void buildAutomaton() {

        for (int i = 0;
             i < text.length();
             i++) {

            extend(text.charAt(i));
        }
    }

    // Add one character
    private void extend(char character) {

        State current = new State();

        current.length =
                last.length + 1;

        State previous = last;

        while (
                previous != null
                && !previous.next.containsKey(character)
        ) {

            previous.next.put(
                    character,
                    current
            );

            previous =
                    previous.suffixLink;
        }

        if (previous == null) {

            current.suffixLink = root;

        } else {

            State existing =
                    previous.next.get(character);

            if (existing.length
                    == previous.length + 1) {

                current.suffixLink =
                        existing;

            } else {

                State clone =
                        new State();

                clone.length =
                        previous.length + 1;

                clone.next.putAll(
                        existing.next
                );

                clone.suffixLink =
                        existing.suffixLink;

                while (
                        previous != null
                        && previous.next.get(character)
                        == existing
                ) {

                    previous.next.put(
                            character,
                            clone
                    );

                    previous =
                            previous.suffixLink;
                }

                existing.suffixLink =
                        clone;

                current.suffixLink =
                        clone;
            }
        }

        last = current;
    }

    // Search substring
    public boolean contains(String pattern) {

        if (pattern == null
                || pattern.isEmpty()) {

            return false;
        }

        pattern =
                pattern.toUpperCase();

        State current = root;

        for (int i = 0;
             i < pattern.length();
             i++) {

            char character =
                    pattern.charAt(i);

            State nextState =
                    current.next.get(character);

            if (nextState == null) {

                return false;
            }

            current = nextState;
        }

        return true;
    }

    // Count number of states
    public int getStateCount() {

        return countStates();
    }

    private int countStates() {

        java.util.HashSet<State> visited =
                new java.util.HashSet<>();

        countFromState(
                root,
                visited
        );

        return visited.size();
    }

    private void countFromState(
            State state,
            java.util.HashSet<State> visited) {

        if (state == null
                || visited.contains(state)) {

            return;
        }

        visited.add(state);

        for (State nextState :
                state.next.values()) {

            countFromState(
                    nextState,
                    visited
            );
        }
    }

    // Display automaton
    public void displayAutomaton() {

        System.out.println(
                "\n========== SUFFIX AUTOMATON =========="
        );

        System.out.println(
                "Genome Sequence: " + text
        );

        System.out.println(
                "Number of States: "
                + getStateCount()
        );

        System.out.println(
                "\nTransitions:"
        );

        displayTransitions(
                root,
                0,
                new java.util.HashSet<State>()
        );

        System.out.println(
                "======================================="
        );
    }

    private void displayTransitions(
            State state,
            int stateNumber,
            java.util.HashSet<State> visited) {

        if (state == null
                || visited.contains(state)) {

            return;
        }

        visited.add(state);

        for (
                Map.Entry<Character, State> entry :
                state.next.entrySet()
        ) {

            System.out.println(
                    "State length "
                    + state.length
                    + " --"
                    + entry.getKey()
                    + "--> State length "
                    + entry.getValue().length
            );
        }

        for (State nextState :
                state.next.values()) {

            displayTransitions(
                    nextState,
                    stateNumber + 1,
                    visited
            );
        }
    }

    public String getText() {

        return text;
    }
}