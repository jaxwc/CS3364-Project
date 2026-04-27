import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class Main {
  public static void main(String[] args) throws IOException {
    runCorrectChecks();
    runEdgeCases();
    runRandomBenchmarks();
  }

  public static int[] randomArray(int n, Random rng) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = rng.nextInt(10000);
    }
    return arr;
  }

  public static boolean testOne(int n, int k, Random rng) {
    int[] arr = randomArray(n, rng);
    int[] expected = arr.clone();
    Arrays.sort(expected);
    Sorting.quickHybridSort(arr, k);
    return Arrays.equals(arr, expected);
  }

  public static void runCorrectChecks() {
    Random rng = new Random(42);

    int[] testSizes = {0, 1, 2, 5, 10, 50, 1000, 5000};
    int[] kValues = {0, 1, 5, 10, 20, 50};

    int passed = 0;
    int total = 0;
    for (int n : testSizes) {
      for (int k : kValues) {
        total++;
        if (testOne(n, k, rng)) {
          passed++;
        } else {
          System.out.println("FAIL: n = " + n + " k = " + k);
        }
      }
    }
    System.out.println("Random tests: " + passed + "/" + total + " passed");
  }

  public static void runEdgeCases() {
    int k = 10;
    int[][] cases = {
      {}, {42}, {1, 2, 3, 4, 5}, {5, 4, 3, 2, 1}, {7, 7, 7, 7, 7}, {3, 1, 4, 1, 5, 9, 2, 6},
    };
    int passed = 0;
    for (int[] arr : cases) {
      int[] expected = arr.clone();
      Arrays.sort(expected);
      Sorting.quickHybridSort(arr, k);
      if (Arrays.equals(arr, expected)) {
        passed++;
      } else {
        System.out.println("FAIL: " + Arrays.toString(arr));
      }
    }
    System.out.println("Edge cases: " + passed + "/" + cases.length + " passed");
  }

  public static void runRandomBenchmarks() throws IOException {
    Random rng = new Random(123);

    int[] nValues = {1000, 5000, 10000, 50000, 100000};
    int[] kValues = {0, 1, 2, 5, 10, 15, 20, 30, 40, 50, 75, 100};
    int trials = 20;

    PrintWriter writer = new PrintWriter(new FileWriter("benchmark_random.csv"));
    writer.println("n,k,avg_time_ms");

    for (int n : nValues) {
      for (int k : kValues) {
        long totalTimeNs = 0;

        for (int trial = 0; trial < trials; trial++) {
          int[] arr = randomArray(n, rng);

          long start = System.nanoTime();
          Sorting.quickHybridSort(arr, k);
          long end = System.nanoTime();
          totalTimeNs += end - start;
        }

        double avgTimeMS = totalTimeNs / 1_000_000.0 / trials;

        writer.println(n + "," + k + "," + avgTimeMS);
        System.out.println("n = " + n + ", k = " + k + ", avg ms = " + avgTimeMS);
      }
    }
    writer.close();
    System.out.println("Wrote to benchmark_random.csv");
  }
}
