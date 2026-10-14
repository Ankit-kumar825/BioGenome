package biogenome.utils;

public class InputValidator {

    // Validate DNA sequence
    public static boolean isValidDNA(String sequence) {

        if (sequence == null || sequence.isEmpty()) {
            return false;
        }

        sequence = sequence.toUpperCase();

        for (int i = 0; i < sequence.length(); i++) {

            char ch = sequence.charAt(i);

            if (ch != 'A' && ch != 'T'
                    && ch != 'G' && ch != 'C') {
                return false;
            }
        }

        return true;
    }

    // Validate positive integer
    public static boolean isPositiveInteger(int number) {
        return number > 0;
    }

    // Validate non-negative integer
    public static boolean isNonNegativeInteger(int number) {
        return number >= 0;
    }

    // Validate non-empty text
    public static boolean isValidText(String text) {
        return text != null && !text.trim().isEmpty();
    }

    // Validate email format (basic check)
    public static boolean isValidEmail(String email) {

        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        return email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        );
    }

    // Validate nucleotide
    public static boolean isValidNucleotide(char nucleotide) {

        nucleotide = Character.toUpperCase(nucleotide);

        return nucleotide == 'A'
                || nucleotide == 'T'
                || nucleotide == 'G'
                || nucleotide == 'C';
    }
}