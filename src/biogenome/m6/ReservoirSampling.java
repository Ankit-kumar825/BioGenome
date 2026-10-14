package biogenome.m6;

import java.util.Random;

public class ReservoirSampling {

    private static Random random =
            new Random();

    // Select k random elements
    // from a stream of data
    public static int[] sample(
            int[] data,
            int k) {

        if (data == null
                || k <= 0
                || k > data.length) {

            return new int[0];
        }

        int[] reservoir =
                new int[k];

        // Fill reservoir initially
        for (int i = 0;
             i < k;
             i++) {

            reservoir[i] =
                    data[i];
        }

        // Process remaining elements
        for (int i = k;
             i < data.length;
             i++) {

            int randomIndex =
                    random.nextInt(i + 1);

            if (randomIndex < k) {

                reservoir[randomIndex] =
                        data[i];
            }
        }

        return reservoir;
    }

    // Display sampling result
    public static void printResult(
            int[] data,
            int k) {

        int[] result =
                sample(
                        data,
                        k
                );

        System.out.println(
                "\n======= RESERVOIR SAMPLING ======="
        );

        System.out.println(
                "Original Data:"
        );

        printArray(data);

        System.out.println(
                "\nSample Size: " + k
        );

        System.out.println(
                "Random Sample:"
        );

        printArray(result);

        System.out.println(
                "==================================="
        );
    }

    private static void printArray(
            int[] array) {

        for (int value : array) {

            System.out.print(
                    value + " "
            );
        }

        System.out.println();
    }
}