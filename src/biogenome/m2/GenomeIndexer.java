package biogenome.m2;

public class GenomeIndexer {

    private String genomeSequence;

    private int[] suffixArray;
    private int[] saisSuffixArray;

    private SuffixTree suffixTree;
    private SuffixAutomaton suffixAutomaton;

    public GenomeIndexer(String genomeSequence) {

        this.genomeSequence =
                genomeSequence.toUpperCase();

        buildIndex();

        suffixTree =
                new SuffixTree(this.genomeSequence);

        suffixAutomaton =
                new SuffixAutomaton(this.genomeSequence);
    }

    // ================= SUFFIX ARRAY =================

    public void buildIndex() {

        suffixArray =
                SuffixArray.buildSuffixArray(
                        genomeSequence
                );
    }

    public void displayIndex() {

        System.out.println(
                "\n========== SUFFIX ARRAY INDEX =========="
        );

        System.out.println(
                "Genome Sequence: "
                + genomeSequence
        );

        System.out.println(
                "\nIndex\tSuffix"
        );

        for (int index : suffixArray) {

            System.out.println(
                    index
                    + "\t"
                    + genomeSequence.substring(index)
            );
        }

        System.out.println(
                "========================================="
        );
    }

    // ================= SA-IS =================

    public void buildSAISIndex() {

        saisSuffixArray =
                SAIS.buildSuffixArray(
                        genomeSequence
                );
    }

    public void displaySAISIndex() {

        buildSAISIndex();

        System.out.println(
                "\n========== SA-IS INDEX =========="
        );

        System.out.println(
                "Genome Sequence: "
                + genomeSequence
        );

        System.out.println(
                "\nIndex\tSuffix"
        );

        for (int index : saisSuffixArray) {

            if (index < genomeSequence.length()) {

                System.out.println(
                        index
                        + "\t"
                        + genomeSequence.substring(index)
                );
            }
        }

        System.out.println(
                "================================="
        );
    }

    public boolean verifySAIS() {

        buildSAISIndex();

        if (suffixArray.length
                != saisSuffixArray.length) {

            return false;
        }

        for (int i = 0;
             i < suffixArray.length;
             i++) {

            if (suffixArray[i]
                    != saisSuffixArray[i]) {

                return false;
            }
        }

        return true;
    }

    // ================= SUFFIX ARRAY SEARCH =================

    public boolean contains(String pattern) {

        pattern = pattern.toUpperCase();

        int left = 0;

        int right =
                suffixArray.length - 1;

        while (left <= right) {

            int mid =
                    (left + right) / 2;

            int suffixIndex =
                    suffixArray[mid];

            String suffix =
                    genomeSequence.substring(
                            suffixIndex
                    );

            if (suffix.startsWith(pattern)) {

                return true;
            }

            int comparison =
                    suffix.compareTo(pattern);

            if (comparison < 0) {

                left = mid + 1;

            } else {

                right = mid - 1;
            }
        }

        return false;
    }

    // ================= KASAI LCP =================

    public void displayLCP() {

        int[] lcp =
                KasaiLCP.buildLCP(
                        genomeSequence,
                        suffixArray
                );

        KasaiLCP.printLCP(
                genomeSequence,
                suffixArray
        );

        String repeated =
                KasaiLCP.findLongestRepeatedSubstring(
                        genomeSequence,
                        suffixArray
                );

        System.out.println(
                "\nLongest Repeated DNA Pattern: "
                + (repeated.isEmpty()
                    ? "None"
                    : repeated)
        );
    }

    // ================= SUFFIX TREE =================

    public void displaySuffixTree() {

        suffixTree.displayTree();
    }

    public boolean containsUsingSuffixTree(
            String pattern) {

        return suffixTree.contains(pattern);
    }

    // ================= SUFFIX AUTOMATON =================

    public void displaySuffixAutomaton() {

        suffixAutomaton.displayAutomaton();
    }

    public boolean containsUsingSuffixAutomaton(
            String pattern) {

        return suffixAutomaton.contains(pattern);
    }

    public int getSuffixAutomatonStateCount() {

        return suffixAutomaton.getStateCount();
    }

    // ================= GETTERS =================

    public int[] getSuffixArray() {

        return suffixArray;
    }

    public int[] getSAISSuffixArray() {

        return saisSuffixArray;
    }

    public String getGenomeSequence() {

        return genomeSequence;
    }
}