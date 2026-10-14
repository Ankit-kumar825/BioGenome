package biogenome.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportGenerator {

    // Generate and save a report
    public static void generateReport(
            String reportTitle,
            String genomeId,
            String analysisName,
            String result) {

        String fileName = "BioGenome_Report.txt";

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(fileName, true))) {

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(
                            "dd-MM-yyyy HH:mm:ss"
                    );

            writer.write("========================================");
            writer.newLine();

            writer.write("          BIOGENOME ANALYSIS REPORT");
            writer.newLine();

            writer.write("========================================");
            writer.newLine();

            writer.write("Report Title: " + reportTitle);
            writer.newLine();

            writer.write("Date: " + LocalDateTime.now().format(formatter));
            writer.newLine();

            writer.write("Genome ID: " + genomeId);
            writer.newLine();

            writer.write("Analysis: " + analysisName);
            writer.newLine();

            writer.write("Result:");
            writer.newLine();

            writer.write(result == null ? "No result available." : result);
            writer.newLine();

            writer.write("========================================");
            writer.newLine();
            writer.newLine();

            System.out.println(
                    "Report saved successfully: " + fileName
            );

        } catch (IOException e) {

            System.out.println(
                    "Error generating report: " + e.getMessage()
            );
        }
    }
}