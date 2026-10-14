package biogenome.m6;

import java.util.Random;

public class RandomizedQuickSort {

    private static Random random =
            new Random();

    // Public method
    public static void sort(int[] array) {

        if (array == null
                || array.length <= 1) {

            return;
        }

        quickSort(
                array,
                0,
                array.length - 1
        );
    }

    // Randomized QuickSort
    private static void quickSort(
            int[] array,
            int low,
            int high) {

        if (low < high) {

            int pivotIndex =
                    randomizedPartition(
                            array,
                            low,
                            high
                    );

            quickSort(
                    array,
                    low,
                    pivotIndex - 1
            );

            quickSort(
                    array,
                    pivotIndex + 1,
                    high
            );
        }
    }

    // Randomly select pivot
    private static int randomizedPartition(
            int[] array,
            int low,
            int high) {

        int randomIndex =
                low + random.nextInt(
                        high - low + 1
                );

        swap(
                array,
                randomIndex,
                high
        );

        int pivot =
                array[high];

        int i = low - 1;

        for (int j = low;
             j < high;
             j++) {

            if (array[j] <= pivot) {

                i++;

                swap(
                        array,
                        i,
                        j
                );
            }
        }

        swap(
                array,
                i + 1,
                high
        );

        return i + 1;
    }

    private static void swap(
            int[] array,
            int i,
            int j) {

        int temp =
                array[i];

        array[i] =
                array[j];

        array[j] =
                temp;
    }

    // Display sorted array
    public static void printResult(
            int[] array) {

        System.out.println(
                "\n======= RANDOMIZED QUICKSORT ======="
        );

        System.out.println(
                "Original Array:"
        );

        printArray(array);

        sort(array);

        System.out.println(
                "\nSorted Array:"
        );

        printArray(array);

        System.out.println(
                "===================================="
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