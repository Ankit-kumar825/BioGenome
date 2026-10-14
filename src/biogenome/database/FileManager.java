package biogenome.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import biogenome.model.Genome;

public class FileManager {

    private static final String FILE_NAME = "genomes.txt";

    // Save all genomes to file
    public static void saveGenomes(ArrayList<Genome> genomes) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Genome genome : genomes) {

                writer.write(
                        encode(genome.getGenomeId()) + "\t"
                        + encode(genome.getSpecies()) + "\t"
                        + encode(genome.getSequence()) + "\t"
                        + encode(genome.getDescription())
                );

                writer.newLine();
            }

            System.out.println("Genome data saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving genome data: " + e.getMessage());
        }
    }

    // Load genomes from file
    public static ArrayList<Genome> loadGenomes() {

        ArrayList<Genome> genomes = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\t", -1);

                if (data.length == 4) {

                    Genome genome = new Genome(
                            decode(data[0]),
                            decode(data[1]),
                            decode(data[2]),
                            decode(data[3])
                    );

                    genomes.add(genome);
                }
            }

            System.out.println("Genome data loaded successfully.");

        } catch (IOException e) {

            System.out.println(
                    "Genome file not found or could not be read. "
                    + "Starting with empty data."
            );
        }

        return genomes;
    }

    private static String encode(String value) {
        return value == null ? "" : value
                .replace("\\", "\\\\")
                .replace("\t", "\\t")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }

    private static String decode(String value) {

        StringBuilder result = new StringBuilder();
        boolean escaped = false;

        for (int i = 0; i < value.length(); i++) {

            char ch = value.charAt(i);

            if (escaped) {

                if (ch == 't') {
                    result.append('\t');
                } else if (ch == 'n') {
                    result.append('\n');
                } else if (ch == 'r') {
                    result.append('\r');
                } else {
                    result.append(ch);
                }

                escaped = false;

            } else if (ch == '\\') {
                escaped = true;
            } else {
                result.append(ch);
            }
        }

        if (escaped) {
            result.append('\\');
        }

        return result.toString();
    }
}