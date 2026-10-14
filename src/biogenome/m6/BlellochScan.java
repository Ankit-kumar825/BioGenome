package biogenome.m6;

public class BlellochScan {

    public static int[] scan(int[] input) {

        if (input == null || input.length == 0) {
            return new int[0];
        }

        int n = input.length;

        int size = 1;

        while (size < n) {
            size *= 2;
        }

        int[] tree = new int[size];

        // Copy input
        for (int i = 0; i < n; i++) {
            tree[i] = input[i];
        }

        // Up-Sweep Phase
        for (int step = 1; step < size; step *= 2) {

            for (int i = 0;
                 i < size;
                 i += 2 * step) {

                int right = i + 2 * step - 1;
                int left = i + step - 1;

                tree[right] += tree[left];
            }
        }

        // Root = 0
        tree[size - 1] = 0;

        // Down-Sweep Phase
        for (int step = size / 2;
             step >= 1;
             step /= 2) {

            for (int i = 0;
                 i < size;
                 i += 2 * step) {

                int left = i + step - 1;
                int right = i + 2 * step - 1;

                int temp = tree[left];

                tree[left] = tree[right];

                tree[right] += temp;
            }
        }

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = tree[i];
        }

        return result;
    }

    public static void printResult(int[] input) {

        int[] result = scan(input);

        System.out.println(
                "\n========== BLELLOCH SCAN =========="
        );

        System.out.println("Input Array:");

        for (int value : input) {
            System.out.print(value + " ");
        }

        System.out.println("\n\nExclusive Prefix Sum:");

        for (int value : result) {
            System.out.print(value + " ");
        }

        System.out.println(
                "\n==================================="
        );
    }
}