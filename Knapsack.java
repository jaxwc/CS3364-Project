import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Knapsack {
  private static final int[] IDS = {
    1, 2, 3, 4, 5,
  };
  private static final int[] WEIGHTS = {6, 4, 5, 3, 7};
  private static final int[] SCORES = {1600, 1000, 1800, 1200, 2000};
  private static final int CAPACITY = 18;

  public static void main(String[] args) {
    int[][] dp = buildTable();

    System.out.println("Maximum biodiversity score: " + dp[IDS.length][CAPACITY]);
    System.out.println("Optimal item combinations: ");
    printOptimalCombinations(dp);
  }

  // builds the dynamic programming table where dp[i][w] stores the best
  // biodiversity score using the first i items with capacity w
  private static int[][] buildTable() {
    int itemCount = IDS.length;
    int[][] dp = new int[itemCount + 1][CAPACITY + 1];

    for (int i = 1; i <= itemCount; i++) {
      int itemWeight = WEIGHTS[i - 1];
      int itemScore = SCORES[i - 1];

      for (int w = 0; w <= CAPACITY; w++) {
        // if current item is too heavy, it can not be included.
        if (itemWeight > w) {
          dp[i][w] = dp[i - 1][w];
          // otherwise choose the better option: skip the item or take it
        } else {
          int skipItem = dp[i - 1][w];
          int takeItem = dp[i - 1][w - itemWeight] + itemScore;
          dp[i][w] = Math.max(skipItem, takeItem);
        }
      }
    }
    return dp;
  }

  // backtracks through the dp table to print every combination that achieves the max biodiversity
  // score.
  private static void printOptimalCombinations(int[][] dp) {
    List<Integer> currentItems = new ArrayList<>();
    backtrack(dp, IDS.length, CAPACITY, currentItems);
  }

  private static void backtrack(
      int[][] dp, int i, int remainingCapacity, List<Integer> currentItems) {
    if (i == 0) {
      printCombination(currentItems);
      return;
    }

    int itemWeight = WEIGHTS[i - 1];
    int itemScore = SCORES[i - 1];

    // if skipping item i still gives the optimal value, explore that given path.
    if (dp[i][remainingCapacity] == dp[i - 1][remainingCapacity]) {
      backtrack(dp, i - 1, remainingCapacity, currentItems);
    }

    // if taking item i will produce the optimal value, include it and continue.
    if (itemWeight <= remainingCapacity
        && dp[i][remainingCapacity] == dp[i - 1][remainingCapacity - itemWeight] + itemScore) {
      currentItems.add(IDS[i - 1]);
      backtrack(dp, i - 1, remainingCapacity - itemWeight, currentItems);
      currentItems.remove(currentItems.size() - 1);
    }
  }

  private static void printCombination(List<Integer> items) {
    List<Integer> orderedItems = new ArrayList<>(items);
    Collections.sort(orderedItems);

    int totalWeight = 0;
    int totalScore = 0;

    for (int itemId : orderedItems) {
      int index = itemId - 1;
      totalWeight += WEIGHTS[index];
      totalScore += SCORES[index];
    }

    System.out.println(
        "Items "
            + orderedItems
            + " | total weight = "
            + totalWeight
            + " | total biodiversity score = "
            + totalScore);
  }
}
