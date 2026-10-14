package biogenome.utils;

public class DNAUtils {

    public static boolean isValidDNA(String sequence) {

        if (sequence == null || sequence.isEmpty()) {
            return false;
        }

        sequence = sequence.toUpperCase();

        for (int i = 0; i < sequence.length(); i++) {

            char ch = sequence.charAt(i);

            if (ch != 'A' && ch != 'T' && ch != 'G' && ch != 'C') {
                return false;
            }
        }

        return true;
    }

    public static int countNucleotide(String sequence, char nucleotide) {

        int count = 0;

        sequence = sequence.toUpperCase();
        nucleotide = Character.toUpperCase(nucleotide);

        for (int i = 0; i < sequence.length(); i++) {

            if (sequence.charAt(i) == nucleotide) {
                count++;
            }
        }

        return count;
    }

    public static double calculateGCContent(String sequence) {

        if (sequence == null || sequence.isEmpty()) {
            return 0;
        }

        int g = countNucleotide(sequence, 'G');
        int c = countNucleotide(sequence, 'C');

        return ((double) (g + c) / sequence.length()) * 100;
    }

    public static double calculateATContent(String sequence) {

        if (sequence == null || sequence.isEmpty()) {
            return 0;
        }

        int a = countNucleotide(sequence, 'A');
        int t = countNucleotide(sequence, 'T');

        return ((double) (a + t) / sequence.length()) * 100;
    }

    public static void printStatistics(String sequence) {

        System.out.println("\n========== DNA Statistics ==========");

        System.out.println("Length : " + sequence.length());
        System.out.println("A      : " + countNucleotide(sequence, 'A'));
        System.out.println("T      : " + countNucleotide(sequence, 'T'));
        System.out.println("G      : " + countNucleotide(sequence, 'G'));
        System.out.println("C      : " + countNucleotide(sequence, 'C'));

        System.out.printf("GC %%   : %.2f%%%n", calculateGCContent(sequence));
        System.out.printf("AT %%   : %.2f%%%n", calculateATContent(sequence));

        System.out.println("====================================");
    }
}