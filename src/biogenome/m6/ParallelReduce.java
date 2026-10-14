package biogenome.m6;

public class ParallelReduce {

    public static int reduceSum(int[] data) {

        if (data == null || data.length == 0) {
            return 0;
        }

        int size = 1;

        while (size < data.length) {
            size *= 2;
        }

        int[] tree = new int[size];

        // Copy data
        for (int i = 0; i < data.length; i++) {
            tree[i] = data[i];
        }

        // Reduction
        for (int step = 1;
             step < size;
             step *= 2) {

            for (int i = 0;
                 i < size;
                 i += 2 * step) {

                int right = i + 2 * step - 1;
                int left = i + step - 1;

                tree[right] += tree[left];
            }
        }

        return tree[size - 1];
    }

    public static void printResult(int[] data) {

        int result = reduceSum(data);

        System.out.println(
                "\n========== PARALLEL REDUCE =========="
        );

        System.out.println("Input Data:");

        for (int value : data) {
            System.out.print(value + " ");
        }

        System.out.println(
                "\n\nTotal Sum: " + result
        );

        System.out.println(
                "====================================="
        );
    }
}