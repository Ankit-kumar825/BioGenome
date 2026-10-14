package biogenome.utils;

public class PerformanceTimer {

    private long startTime;
    private long endTime;

    // Start measuring time
    public void start() {
        startTime = System.nanoTime();
        endTime = 0;
    }

    // Stop measuring time
    public void stop() {
        endTime = System.nanoTime();
    }

    // Get execution time in nanoseconds
    public long getExecutionTimeNanos() {
        if (endTime == 0) {
            return -1;
        }

        return endTime - startTime;
    }

    // Get execution time in milliseconds
    public double getExecutionTimeMillis() {
        long elapsed = getExecutionTimeNanos();

        if (elapsed < 0) {
            return -1;
        }

        return elapsed / 1_000_000.0;
    }

    // Display execution time
    public void printExecutionTime(String algorithmName) {

        if (endTime == 0) {
            System.out.println("Timer has not been stopped.");
            return;
        }

        System.out.println("\n========== PERFORMANCE REPORT ==========");
        System.out.println("Algorithm: " + algorithmName);
        System.out.println(
                "Execution Time: "
                + getExecutionTimeNanos()
                + " ns"
        );

        System.out.printf(
                "Execution Time: %.4f ms%n",
                getExecutionTimeMillis()
        );

        System.out.println("=========================================");
    }
}