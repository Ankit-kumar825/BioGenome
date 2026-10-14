package biogenome.m5;

public class ThreeSAT {

    private int variables;
    private int[][] clauses;

    public ThreeSAT(
            int variables,
            int[][] clauses) {

        this.variables = variables;
        this.clauses = clauses;
    }

    // Check whether a clause is satisfied
    private boolean isClauseSatisfied(
            int[] clause,
            int assignment) {

        for (int literal : clause) {

            int variable =
                    Math.abs(literal) - 1;

            boolean value =
                    ((assignment >> variable) & 1) == 1;

            // Positive literal
            if (literal > 0 && value) {
                return true;
            }

            // Negative literal
            if (literal < 0 && !value) {
                return true;
            }
        }

        return false;
    }

    // Check complete assignment
    private boolean isSatisfied(
            int assignment) {

        for (int[] clause : clauses) {

            if (!isClauseSatisfied(
                    clause,
                    assignment)) {

                return false;
            }
        }

        return true;
    }

    // Solve 3-SAT using exhaustive search
    public boolean solve() {

        int totalAssignments =
                1 << variables;

        for (int assignment = 0;
             assignment < totalAssignments;
             assignment++) {

            if (isSatisfied(assignment)) {

                printAssignment(
                        assignment
                );

                return true;
            }
        }

        return false;
    }

    // Display satisfying assignment
    private void printAssignment(
            int assignment) {

        System.out.println(
                "\nSatisfying Assignment:"
        );

        for (int i = 0;
             i < variables;
             i++) {

            boolean value =
                    ((assignment >> i) & 1) == 1;

            System.out.println(
                    "Variable "
                    + (i + 1)
                    + " = "
                    + value
            );
        }
    }

    // Display result
    public void printResult() {

        System.out.println(
                "\n========== 3-SAT ANALYSIS =========="
        );

        System.out.println(
                "Number of Variables: "
                + variables
        );

        System.out.println(
                "Number of Clauses: "
                + clauses.length
        );

        boolean result = solve();

        if (result) {

            System.out.println(
                    "\nResult: All biological constraints "
                    + "can be satisfied."
            );

        } else {

            System.out.println(
                    "\nResult: Biological constraints "
                    + "cannot be satisfied together."
            );
        }

        System.out.println(
                "====================================="
        );
    }
}