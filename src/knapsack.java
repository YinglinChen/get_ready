import java.lang.*;

public class knapsack {
  private static int navieKnapsack(int weight, int[] weights) {
    int maxWeight = Integer.MIN_VALUE;
    boolean[][] reachableWeight = new boolean[weights.length + 1][weight + 1];
    reachableWeight[0][0] = true;
    for (int i = 1; i <= weights.length; i++) {
      for (int j = 0; j <= weight; j++) {
        reachableWeight[i][j] = reachableWeight[i - 1][j]
            || (j >= weights[i - 1] ? reachableWeight[i - 1][j - weights[i - 1]] : false);
        if (reachableWeight[i][j]) {
          maxWeight = Math.max(maxWeight, j);
        }
      }
    }
    return maxWeight;
  }

  private static int navieKnapsackNumOfSolution(int weight, int[] weights) {
    int[][] solutionCount = new int[weights.length + 1][weight + 1];
    solutionCount[0][0] = 1;
    for (int i = 1; i <= weights.length; i++) {
      for (int j = 0; j <= weight; j++) {
        if (j >= weights[i - 1]) {
          solutionCount[i][j] = solutionCount[i - 1][j] +
              solutionCount[i - 1][j - weights[i - 1]];
        } else {
          solutionCount[i][j] = solutionCount[i - 1][j];
        }
      }
    }
    return solutionCount[weights.length][weight];
  }

  private static int naiveKnapsackMinNumOfItem(int weight, int[] weights) {
    int[][] itemMinNum = new int[weights.length + 1][weight + 1];
    for (int i = 0; i <= weights.length; i++) {
      for (int j = 0; j <= weight; j++) {
        itemMinNum[i][j] = Integer.MAX_VALUE;
      }
    }
    itemMinNum[0][0] = 0;
    for (int i = 1; i <= weights.length; i++) {
      for (int j = 0; j <= weight; j++) {
        if (j >= weights[i - 1]) {
          itemMinNum[i][j] = Math.min(itemMinNum[i - 1][j],
              itemMinNum[i - 1][j - weights[i - 1]] == Integer.MAX_VALUE ? Integer.MAX_VALUE : itemMinNum[i - 1][j - weights[i - 1]] + 1);
        } else {
          itemMinNum[i][j] = itemMinNum[i - 1][j];
        }
      }
    }
    return itemMinNum[weights.length][weight] == Integer.MAX_VALUE ? -1 : itemMinNum[weights.length][weight];
  }

  public static int coinChange(int[] coins, int amount) {
    int[][] coinNum = new int[coins.length + 1][amount + 1];
      for (int j = 0; j <= amount; j++) {
        coinNum[0][j] = Integer.MAX_VALUE;
      }

    coinNum[0][0] = 0;
    for (int i = 1; i <= coins.length; i++) {
      for (int j = 0; j <= amount; j++) {
        if (i == 0 && j == 0) {
          coinNum[0][0] = 0;
        }
        coinNum[i][j] = Integer.MAX_VALUE;
        for (int k = 0; k <= j / coins[i - 1]; k++) {
          if (coinNum[i - 1][j - k * coins[i - 1]] != Integer.MAX_VALUE) {
            coinNum[i][j] = Math.min(coinNum[i][j], coinNum[i - 1][j - k * coins[i - 1]] + k);
          }
        }
      }
    }
    return coinNum[coins.length][amount] == Integer.MAX_VALUE ? -1 : coinNum[coins.length][amount];
  }


  public static int findTargetSumWays(int[] nums, int S) {
      int[] dp =  new int[2001];
      dp[nums[0] + 1000] = 1;
      dp[1000 - nums[0]] += 1;
      for (int i = 1; i < nums.length; i++) {
        // int[] next = new int[2001];
        for (int j = -1000; j <= 1000; j++) {
          if (dp[j + 1000] > 0) {
            dp[j + nums[i] + 1000] += dp[j + 1000];
            dp[j - nums[i] + 1000] += dp[j + 1000];
          }
        }
        // dp = next;
      }
      return S > 1000 ? 0 : dp[S + 1000];
  }




  public static void main (String[] args) {
    //code
    int weight = 11;
    int[] weights = {1, 2, 5};
//    System.out.println(navieKnapsack(weight, weights));
//    System.out.println(coinChange(weights, weight));
    System.out.println(findTargetSumWays(new int[] {1, 1, 1, 1, 1}, 3));
  }
}
