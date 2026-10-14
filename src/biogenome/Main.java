package biogenome;

import java.util.ArrayList;



import java.util.Scanner;

import biogenome.m1.AhoCorasick;
import biogenome.m1.MotifSearch;
import biogenome.m2.GenomeIndexer;
import biogenome.database.GenomeManager;
import biogenome.model.Genome;
import biogenome.utils.DNAUtils;
import biogenome.m2.GenomeIndexer;
import biogenome.m3.Levenshtein;
import biogenome.m3.DamerauLevenshtein;
import biogenome.m3.NeedlemanWunsch;
import biogenome.m3.SmithWaterman;
import biogenome.m3.BitmaskDP;
import biogenome.m3.MutationAnalyzer;
import biogenome.m4.FordFulkerson;
import biogenome.m4.EdmondsKarp;
import biogenome.m4.Dinic;
import biogenome.m4.BipartiteMatching;
import biogenome.m4.ResourceAllocator;
import biogenome.m5.ThreeSAT;
import biogenome.m5.Clique;
import biogenome.m5.VertexCover;
import biogenome.m5.VertexCoverApprox;
import biogenome.m6.RandomizedQuickSort;
import biogenome.m6.ReservoirSampling;
import biogenome.m6.BlellochScan;
import biogenome.m6.ParallelReduce;
import biogenome.database.FileManager;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static GenomeManager manager = new GenomeManager();
    

    public static void main(String[] args) {

        // Load previously saved genomes
        ArrayList<Genome> savedGenomes = FileManager.loadGenomes();

        for (Genome genome : savedGenomes) {
            manager.addGenome(genome);
        }

        int choice;

        do {
            printMenu();

            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addGenome();
                    break;

                case 2:
                    viewGenomes();
                    break;

                case 3:
                    searchGenome();
                    break;

                case 4:
                    deleteGenome();
                    break;

                case 5:
                    genomeStatistics();
                    break;
                case 6:
                    kmpMotifSearch();
                    break;
                case 7:
                    zFunctionMotifSearch();
                    break;
                case 8:
                    rabinKarpMotifSearch();
                    break;   
                case 10:
                    suffixArrayIndex();
                    break;
                case 11:
                    saisGenomeIndex();
                    break;
                case 12:
                    lcpAnalysis();
                    break;
                case 13:
                    suffixTreeAnalysis();
                    break;
                case 14:
                    suffixAutomatonAnalysis();
                    break;
                case 15:
                    levenshteinAnalysis();
                    break;

                case 16:
                    damerauAnalysis();
                    break;

                case 17:
                    needlemanWunschAnalysis();
                    break;

                case 18:
                    smithWatermanAnalysis();
                    break;

                case 19:
                    bitmaskAnalysis();
                    break;

                case 20:
                    mutationAnalysis();
                    break;
                case 21:
                    fordFulkersonAnalysis();
                    break;

                case 22:
                    edmondsKarpAnalysis();
                    break;

                case 23:
                    dinicAnalysis();
                    break;

                case 24:
                    bipartiteMatchingAnalysis();
                    break;

                case 25:
                    resourceAllocationAnalysis();
                    break;
                    
                case 26:
                    threeSATAnalysis();
                    break;

                case 27:
                    cliqueAnalysis();
                    break;

                case 28:
                    vertexCoverAnalysis();
                    break;

                case 29:
                    vertexCoverApproxAnalysis();
                    break;
                case 30:
                    randomizedQuickSortAnalysis();
                    break;

                case 31:
                    reservoirSamplingAnalysis();
                    break;

                case 32:
                    blellochScanAnalysis();
                    break;

                case 33:
                    parallelReduceAnalysis();
                    break;


                    

                case 34:
                    System.out.println("Exiting BioGenome...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 30 );

        scanner.close();
    }

    public static void printMenu() {

        System.out.println("\n========================================");
        System.out.println("              BIOGENOME");
        System.out.println(" Genome Research & Analysis Platform");
        System.out.println("========================================");
        System.out.println("1. Add Genome");
        System.out.println("2. View All Genomes");
        System.out.println("3. Search Genome");
        System.out.println("4. Delete Genome");
        System.out.println("5. Genome Statistics");
        System.out.println("6. KMP Motif Search");  
        System.out.println("7. Z-Function Motif Search");
        System.out.println("8. Rabin-Karp Motif Search");
        System.out.println("9. Aho-Corasick Multi-Motif Search");

        System.out.println("10. Suffix Array Genome Index");
        System.out.println("11. SA-IS Genome Index");
        System.out.println("12. LCP / Repeated Pattern Analysis");
        System.out.println("13. Suffix Tree Analysis");
        System.out.println("14. Suffix Automaton Analysis");

        System.out.println("15. Levenshtein Distance");
        System.out.println("16. Damerau-Levenshtein Distance");
        System.out.println("17. Needleman-Wunsch Global Alignment");
        System.out.println("18. Smith-Waterman Local Alignment");
        System.out.println("19. Bitmask DP Assignment");
        System.out.println("20. Mutation Analysis");
        System.out.println("21. Ford-Fulkerson Maximum Flow");
        System.out.println("22. Edmonds-Karp Maximum Flow");
        System.out.println("23. Dinic Maximum Flow");
        System.out.println("24. Researcher-Experiment Matching");
        System.out.println("25. Resource Allocation Comparison");
        System.out.println("26. 3-SAT Biological Constraints");
        System.out.println("27. Maximum Clique Analysis");
        System.out.println("28. Minimum Vertex Cover");
        System.out.println("29. Vertex Cover 2-Approximation");
        System.out.println("30. Randomized QuickSort");
        System.out.println("31. Reservoir Sampling");
        System.out.println("32. Blelloch Scan");
        System.out.println("33. Parallel Reduce");


        System.out.println("34. Exit");
     
        System.out.println("========================================");
    }

    public static void addGenome() {

        System.out.print("Enter Genome ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Species: ");
        String species = scanner.nextLine();

        System.out.print("Enter DNA Sequence: ");
        String sequence = scanner.nextLine().toUpperCase();

        if (!DNAUtils.isValidDNA(sequence)) {
            System.out.println("Invalid DNA sequence!");
            System.out.println("Only A, T, G and C are allowed.");
            return;
        }

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        Genome genome =
                new Genome(id, species, sequence, description);

        if (manager.addGenome(genome)) {

            // Save genomes to file
            FileManager.saveGenomes(manager.getAllGenomes());

            System.out.println("Genome added successfully!");

        } else {
            System.out.println("Genome ID already exists!");
        }
    }

    public static void viewGenomes() {

        System.out.println("\n========== All Genomes ==========");

        if (manager.getAllGenomes().isEmpty()) {
            System.out.println("No genomes available.");
            return;
        }

        for (Genome genome : manager.getAllGenomes()) {
            System.out.println("----------------------------");
            System.out.println(genome);
        }
    }

    public static void searchGenome() {

        System.out.print("Enter Genome ID: ");
        String id = scanner.nextLine();

        Genome genome = manager.findGenome(id);

        if (genome == null) {
            System.out.println("Genome not found!");
        } else {
            System.out.println("\nGenome Found:");
            System.out.println(genome);
            System.out.println("Sequence: " + genome.getSequence());
        }
    }

    public static void deleteGenome() {

        System.out.print("Enter Genome ID: ");
        String id = scanner.nextLine();

        if (manager.deleteGenome(id)) {
            System.out.println("Genome deleted successfully!");
        } else {
            System.out.println("Genome not found!");
        }
    }

    public static void genomeStatistics() {

        System.out.print("Enter Genome ID: ");
        String id = scanner.nextLine();

        Genome genome = manager.findGenome(id);

        if (genome == null) {
            System.out.println("Genome not found!");
            return;
        }

        DNAUtils.printStatistics(genome.getSequence());
    }
    public static void kmpMotifSearch() {

        System.out.print("Enter Genome ID: ");
        String id = scanner.nextLine();

        Genome genome = manager.findGenome(id);

        if (genome == null) {
            System.out.println("Genome not found!");
            return;
        }

        System.out.print("Enter DNA motif to search: ");
        String motif = scanner.nextLine().toUpperCase();

        if (!DNAUtils.isValidDNA(motif)) {
            System.out.println("Invalid DNA motif!");
            System.out.println("Only A, T, G and C are allowed.");
            return;
        }

        MotifSearch.searchUsingKMP(
                genome.getSequence(),
                motif
        );
    }
    public static void zFunctionMotifSearch() {

        System.out.print("Enter Genome ID: ");
        String id = scanner.nextLine();

        Genome genome = manager.findGenome(id);

        if (genome == null) {
            System.out.println("Genome not found!");
            return;
        }

        System.out.print("Enter DNA motif to search: ");
        String motif = scanner.nextLine().toUpperCase();

        if (!DNAUtils.isValidDNA(motif)) {
            System.out.println("Invalid DNA motif!");
            System.out.println("Only A, T, G and C are allowed.");
            return;
        }

        MotifSearch.searchUsingZFunction(
                genome.getSequence(),
                motif
        );
    }
    public static void rabinKarpMotifSearch() {

        System.out.print("Enter Genome ID: ");
        String id = scanner.nextLine();

        Genome genome = manager.findGenome(id);

        if (genome == null) {
            System.out.println("Genome not found!");
            return;
        }

        System.out.print("Enter DNA motif to search: ");
        String motif = scanner.nextLine().toUpperCase();

        if (!DNAUtils.isValidDNA(motif)) {
            System.out.println("Invalid DNA motif!");
            System.out.println("Only A, T, G and C are allowed.");
            return;
        }

        MotifSearch.searchUsingRabinKarp(
                genome.getSequence(),
                motif
        );
    }
    public static void ahoCorasickSearch() {

        System.out.print("Enter Genome ID: ");
        String id = scanner.nextLine();

        Genome genome = manager.findGenome(id);

        if (genome == null) {
            System.out.println("Genome not found!");
            return;
        }

        System.out.print(
                "Enter number of motifs: "
        );

        int numberOfMotifs = scanner.nextInt();
        scanner.nextLine();

        ArrayList<String> motifs = new ArrayList<>();

        for (int i = 0; i < numberOfMotifs; i++) {

            System.out.print(
                    "Enter motif " + (i + 1) + ": "
            );

            String motif =
                    scanner.nextLine().toUpperCase();

            if (!DNAUtils.isValidDNA(motif)) {

                System.out.println(
                        "Invalid DNA motif!"
                );

                i--;
                continue;
            }

            motifs.add(motif);
        }

        AhoCorasick.printSearchResult(
                genome.getSequence(),
                motifs
        );
    }
    public static void suffixArrayIndex() {

        System.out.print("Enter Genome ID: ");
        String id = scanner.nextLine();

        Genome genome =
                manager.findGenome(id);

        if (genome == null) {

            System.out.println(
                    "Genome not found!"
            );

            return;
        }

        GenomeIndexer indexer =
                new GenomeIndexer(
                        genome.getSequence()
                );

        indexer.displayIndex();

        System.out.print(
                "\nEnter motif to search using index: "
        );

        String motif =
                scanner.nextLine().toUpperCase();

        if (!DNAUtils.isValidDNA(motif)) {

            System.out.println(
                    "Invalid DNA motif!"
            );

            return;
        }

        if (indexer.contains(motif)) {

            System.out.println(
                    "Motif found in genome index!"
            );

        } else {

            System.out.println(
                    "Motif not found in genome index!"
            );
        }
    }
    public static void saisGenomeIndex() {

        System.out.print("Enter Genome ID: ");
        String id = scanner.nextLine();

        Genome genome =
                manager.findGenome(id);

        if (genome == null) {

            System.out.println(
                    "Genome not found!"
            );

            return;
        }

        GenomeIndexer indexer =
                new GenomeIndexer(
                        genome.getSequence()
                );

        indexer.displaySAISIndex();

        System.out.println(
                "\nVerifying SA-IS..."
        );

        if (indexer.verifySAIS()) {

            System.out.println(
                    "SA-IS verification successful!"
            );

        } else {

            System.out.println(
                    "SA-IS verification failed!"
            );
        }
    }
    public static void lcpAnalysis() {

        System.out.print("Enter Genome ID: ");
        String id = scanner.nextLine();

        Genome genome =
                manager.findGenome(id);

        if (genome == null) {

            System.out.println(
                    "Genome not found!"
            );

            return;
        }

        GenomeIndexer indexer =
                new GenomeIndexer(
                        genome.getSequence()
                );

        indexer.displayIndex();
    }
    public static void suffixTreeAnalysis() {

        System.out.print("Enter Genome ID: ");

        String id = scanner.nextLine();

        Genome genome =
                manager.findGenome(id);

        if (genome == null) {

            System.out.println(
                    "Genome not found!"
            );

            return;
        }

        GenomeIndexer indexer =
                new GenomeIndexer(
                        genome.getSequence()
                );

        System.out.println(
                "\n========== SUFFIX TREE ANALYSIS =========="
        );

        System.out.println(
                "Genome ID: " + genome.getGenomeId()
        );

        System.out.println(
                "Species: " + genome.getSpecies()
        );

        indexer.displaySuffixTree();

        System.out.print(
                "\nEnter DNA pattern to search: "
        );

        String pattern =
                scanner.nextLine().toUpperCase();

        if (!DNAUtils.isValidDNA(pattern)) {

            System.out.println(
                    "Invalid DNA pattern!"
            );

            System.out.println(
                    "Only A, T, G and C are allowed."
            );

            return;
        }

        if (indexer.containsUsingSuffixTree(pattern)) {

            System.out.println(
                    "Pattern found using Suffix Tree!"
            );

        } else {

            System.out.println(
                    "Pattern not found using Suffix Tree!"
            );
        }

        System.out.println(
                "=========================================="
        );
    }
    public static void suffixAutomatonAnalysis() {

        System.out.print("Enter Genome ID: ");

        String id = scanner.nextLine();

        Genome genome =
                manager.findGenome(id);

        if (genome == null) {

            System.out.println(
                    "Genome not found!"
            );

            return;
        }

        GenomeIndexer indexer =
                new GenomeIndexer(
                        genome.getSequence()
                );

        System.out.println(
                "\n======= SUFFIX AUTOMATON ANALYSIS ======="
        );

        System.out.println(
                "Genome ID: "
                + genome.getGenomeId()
        );

        System.out.println(
                "Species: "
                + genome.getSpecies()
        );

        indexer.displaySuffixAutomaton();

        System.out.print(
                "\nEnter DNA pattern to search: "
        );

        String pattern =
                scanner.nextLine().toUpperCase();

        if (!DNAUtils.isValidDNA(pattern)) {

            System.out.println(
                    "Invalid DNA pattern!"
            );

            System.out.println(
                    "Only A, T, G and C are allowed."
            );

            return;
        }

        if (
            indexer.containsUsingSuffixAutomaton(
                    pattern
            )
        ) {

            System.out.println(
                    "Pattern found using Suffix Automaton!"
            );

        } else {

            System.out.println(
                    "Pattern not found using Suffix Automaton!"
            );
        }

        System.out.println(
                "=========================================="
        );
    }
    
    public static void levenshteinAnalysis() {

        System.out.print("Enter first DNA sequence: ");
        String sequence1 =
                scanner.nextLine().toUpperCase();

        System.out.print("Enter second DNA sequence: ");
        String sequence2 =
                scanner.nextLine().toUpperCase();

        if (!DNAUtils.isValidDNA(sequence1)
                || !DNAUtils.isValidDNA(sequence2)) {

            System.out.println("Invalid DNA sequence!");
            return;
        }

        Levenshtein.printDistance(
                sequence1,
                sequence2
        );
    }
    public static void damerauAnalysis() {

        System.out.print("Enter first DNA sequence: ");
        String sequence1 =
                scanner.nextLine().toUpperCase();

        System.out.print("Enter second DNA sequence: ");
        String sequence2 =
                scanner.nextLine().toUpperCase();

        if (!DNAUtils.isValidDNA(sequence1)
                || !DNAUtils.isValidDNA(sequence2)) {

            System.out.println("Invalid DNA sequence!");
            return;
        }

        DamerauLevenshtein.printDistance(
                sequence1,
                sequence2
        );
    }
    
    public static void needlemanWunschAnalysis() {

        System.out.print("Enter first DNA sequence: ");
        String sequence1 =
                scanner.nextLine().toUpperCase();

        System.out.print("Enter second DNA sequence: ");
        String sequence2 =
                scanner.nextLine().toUpperCase();

        if (!DNAUtils.isValidDNA(sequence1)
                || !DNAUtils.isValidDNA(sequence2)) {

            System.out.println("Invalid DNA sequence!");
            return;
        }

        NeedlemanWunsch.align(
                sequence1,
                sequence2
        );
    }
    public static void smithWatermanAnalysis() {

        System.out.print("Enter first DNA sequence: ");
        String sequence1 =
                scanner.nextLine().toUpperCase();

        System.out.print("Enter second DNA sequence: ");
        String sequence2 =
                scanner.nextLine().toUpperCase();

        if (!DNAUtils.isValidDNA(sequence1)
                || !DNAUtils.isValidDNA(sequence2)) {

            System.out.println("Invalid DNA sequence!");
            return;
        }

        SmithWaterman.align(
                sequence1,
                sequence2
        );
    }
    public static void bitmaskAnalysis() {

        System.out.print(
                "Enter number of experiments/resources: "
        );

        int n =
                Integer.parseInt(
                        scanner.nextLine()
                );

        if (n <= 0 || n > 10) {

            System.out.println(
                    "Please enter a value between 1 and 10."
            );

            return;
        }

        int[][] cost =
                new int[n][n];

        System.out.println(
                "\nEnter cost matrix:"
        );

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                System.out.print(
                        "Experiment "
                        + (i + 1)
                        + " -> Resource "
                        + (j + 1)
                        + ": "
                );

                cost[i][j] =
                        Integer.parseInt(
                                scanner.nextLine()
                        );
            }
        }

        BitmaskDP.printAssignmentResult(cost);
    }
    public static void mutationAnalysis() {

        System.out.print(
                "Enter original DNA sequence: "
        );

        String original =
                scanner.nextLine().toUpperCase();

        System.out.print(
                "Enter modified DNA sequence: "
        );

        String modified =
                scanner.nextLine().toUpperCase();

        if (!DNAUtils.isValidDNA(original)
                || !DNAUtils.isValidDNA(modified)) {

            System.out.println(
                    "Invalid DNA sequence!"
            );

            return;
        }

        MutationAnalyzer.analyze(
                original,
                modified
        );
    }
    
    public static void fordFulkersonAnalysis() {

        System.out.print("Enter number of vertices: ");

        int n =
                Integer.parseInt(
                        scanner.nextLine()
                );

        int[][] graph =
                new int[n][n];

        System.out.println(
                "\nEnter capacity matrix:"
        );

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                System.out.print(
                        "Capacity " + i + " -> " + j + ": "
                );

                graph[i][j] =
                        Integer.parseInt(
                                scanner.nextLine()
                        );
            }
        }

        System.out.print("Enter source vertex: ");
        int source =
                Integer.parseInt(
                        scanner.nextLine()
                );

        System.out.print("Enter sink vertex: ");
        int sink =
                Integer.parseInt(
                        scanner.nextLine()
                );

        FordFulkerson algorithm =
                new FordFulkerson(n);

        algorithm.printMaxFlow(
                graph,
                source,
                sink
        );
    }
    public static void edmondsKarpAnalysis() {

        System.out.print("Enter number of vertices: ");

        int n =
                Integer.parseInt(
                        scanner.nextLine()
                );

        int[][] graph =
                new int[n][n];

        System.out.println(
                "\nEnter capacity matrix:"
        );

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                System.out.print(
                        "Capacity " + i + " -> " + j + ": "
                );

                graph[i][j] =
                        Integer.parseInt(
                                scanner.nextLine()
                        );
            }
        }

        System.out.print("Enter source vertex: ");
        int source =
                Integer.parseInt(
                        scanner.nextLine()
                );

        System.out.print("Enter sink vertex: ");
        int sink =
                Integer.parseInt(
                        scanner.nextLine()
                );

        EdmondsKarp algorithm =
                new EdmondsKarp(n);

        algorithm.printMaxFlow(
                graph,
                source,
                sink
        );
    }
    public static void dinicAnalysis() {

        System.out.print("Enter number of vertices: ");

        int n =
                Integer.parseInt(
                        scanner.nextLine()
                );

        Dinic dinic =
                new Dinic(n);

        System.out.print(
                "Enter number of edges: "
        );

        int edges =
                Integer.parseInt(
                        scanner.nextLine()
                );

        System.out.println(
                "\nEnter edges:"
        );

        for (int i = 0; i < edges; i++) {

            System.out.print("From: ");

            int from =
                    Integer.parseInt(
                            scanner.nextLine()
                    );

            System.out.print("To: ");

            int to =
                    Integer.parseInt(
                            scanner.nextLine()
                    );

            System.out.print("Capacity: ");

            int capacity =
                    Integer.parseInt(
                            scanner.nextLine()
                    );

            dinic.addEdge(
                    from,
                    to,
                    capacity
            );
        }

        System.out.print("Enter source vertex: ");

        int source =
                Integer.parseInt(
                        scanner.nextLine()
                );

        System.out.print("Enter sink vertex: ");

        int sink =
                Integer.parseInt(
                        scanner.nextLine()
                );

        dinic.printMaxFlow(
                source,
                sink
        );
    }
    
    public static void bipartiteMatchingAnalysis() {

        System.out.print(
                "Enter number of researchers: "
        );

        int researchers =
                Integer.parseInt(
                        scanner.nextLine()
                );

        System.out.print(
                "Enter number of experiments: "
        );

        int experiments =
                Integer.parseInt(
                        scanner.nextLine()
                );

        BipartiteMatching matching =
                new BipartiteMatching(
                        researchers,
                        experiments
                );

        System.out.println(
                "\nEnter possible assignments."
        );

        System.out.println(
                "Enter 1 if assignment is possible, otherwise 0."
        );

        for (int i = 0;
             i < researchers;
             i++) {

            for (int j = 0;
                 j < experiments;
                 j++) {

                System.out.print(
                        "Researcher "
                        + (i + 1)
                        + " -> Experiment "
                        + (j + 1)
                        + ": "
                );

                int possible =
                        Integer.parseInt(
                                scanner.nextLine()
                        );

                if (possible == 1) {

                    matching.addConnection(
                            i,
                            j
                    );
                }
            }
        }

        matching.printMatching();
    }
    
    public static void resourceAllocationAnalysis() {

        System.out.print(
                "Enter number of vertices: "
        );

        int n =
                Integer.parseInt(
                        scanner.nextLine()
                );

        int[][] graph =
                new int[n][n];

        System.out.println(
                "\nEnter capacity matrix:"
        );

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                System.out.print(
                        "Capacity " + i + " -> " + j + ": "
                );

                graph[i][j] =
                        Integer.parseInt(
                                scanner.nextLine()
                        );
            }
        }

        System.out.print("Enter source vertex: ");

        int source =
                Integer.parseInt(
                        scanner.nextLine()
                );

        System.out.print("Enter sink vertex: ");

        int sink =
                Integer.parseInt(
                        scanner.nextLine()
                );

        ResourceAllocator.compareAlgorithms(
                graph,
                source,
                sink
        );
    }
    
    public static void threeSATAnalysis() {

        System.out.print(
                "Enter number of variables: "
        );

        int variables =
                Integer.parseInt(
                        scanner.nextLine()
                );

        System.out.print(
                "Enter number of clauses: "
        );

        int numberOfClauses =
                Integer.parseInt(
                        scanner.nextLine()
                );

        int[][] clauses =
                new int[numberOfClauses][3];

        System.out.println(
                "\nEnter 3 literals for each clause."
        );

        System.out.println(
                "Use positive number for TRUE variable"
        );

        System.out.println(
                "Use negative number for NOT variable"
        );

        for (int i = 0;
             i < numberOfClauses;
             i++) {

            System.out.println(
                    "\nClause " + (i + 1)
            );

            for (int j = 0; j < 3; j++) {

                System.out.print(
                        "Literal " + (j + 1) + ": "
                );

                clauses[i][j] =
                        Integer.parseInt(
                                scanner.nextLine()
                        );
            }
        }

        ThreeSAT threeSAT =
                new ThreeSAT(
                        variables,
                        clauses
                );

        threeSAT.printResult();
    }
    public static void cliqueAnalysis() {

        System.out.print(
                "Enter number of vertices: "
        );

        int n =
                Integer.parseInt(
                        scanner.nextLine()
                );

        int[][] graph =
                new int[n][n];

        System.out.println(
                "\nEnter adjacency matrix:"
        );

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                System.out.print(
                        "Edge " + i + " -> " + j + ": "
                );

                graph[i][j] =
                        Integer.parseInt(
                                scanner.nextLine()
                        );
            }
        }

        Clique clique =
                new Clique(
                        n,
                        graph
                );

        clique.printResult();
    }
    
    public static void vertexCoverApproch() {

        System.out.print(
                "Enter number of vertices: "
        );

        int n =
                Integer.parseInt(
                        scanner.nextLine()
                );

        int[][] graph =
                new int[n][n];

        System.out.println(
                "\nEnter adjacency matrix:"
        );

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                System.out.print(
                        "Edge " + i + " -> " + j + ": "
                );

                graph[i][j] =
                        Integer.parseInt(
                                scanner.nextLine()
                        );
            }
        }

        VertexCover cover =
                new VertexCover(
                        n,
                        graph
                );

        cover.printResult();
    }
    
    public static void vertexCoverAnalysis() {

        System.out.print(
                "Enter number of vertices: "
        );

        int n =
                Integer.parseInt(
                        scanner.nextLine()
                );

        int[][] graph =
                new int[n][n];

        System.out.println(
                "\nEnter adjacency matrix:"
        );

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                System.out.print(
                        "Edge " + i + " -> " + j + ": "
                );

                graph[i][j] =
                        Integer.parseInt(
                                scanner.nextLine()
                        );
            }
        }

        VertexCover cover =
                new VertexCover(
                        n,
                        graph
                );

        cover.printResult();
    }
    
    public static void vertexCoverApproxAnalysis() {

        System.out.print(
                "Enter number of vertices: "
        );

        int n =
                Integer.parseInt(
                        scanner.nextLine()
                );

        int[][] graph =
                new int[n][n];

        System.out.println(
                "\nEnter adjacency matrix:"
        );

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                System.out.print(
                        "Edge " + i + " -> " + j + ": "
                );

                graph[i][j] =
                        Integer.parseInt(
                                scanner.nextLine()
                        );
            }
        }

        VertexCoverApprox approximation =
                new VertexCoverApprox(
                        n,
                        graph
                );

        approximation.printResult();
    }
    
    private static void randomizedQuickSortAnalysis() {

        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        int[] data = new int[n];

        System.out.println("Enter elements:");

        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextInt();
        }

        RandomizedQuickSort.printResult(data);
    }
    private static void reservoirSamplingAnalysis() {

        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        int[] data = new int[n];

        System.out.println("Enter elements:");

        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextInt();
        }

        System.out.print("Enter sample size: ");
        int k = scanner.nextInt();

        ReservoirSampling.printResult(data, k);
    }
    
    private static void blellochScanAnalysis() {

        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        int[] data = new int[n];

        System.out.println("Enter elements:");

        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextInt();
        }

        BlellochScan.printResult(data);
    }
    
    private static void parallelReduceAnalysis() {

        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        int[] data = new int[n];

        System.out.println("Enter elements:");

        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextInt();
        }

        ParallelReduce.printResult(data);
    }
    
    
}