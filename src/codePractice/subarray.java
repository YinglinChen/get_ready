package codePractice;
import java.util.*;

public class subarray {
  public static int subarraySum(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    Map<Integer, Integer> sums = new HashMap<>();
    int currSum = 0;
    sums.put(0, 1);
    int res = 0;
    for (int i = 0; i < nums.length; i++) {
      currSum += nums[i];
      if (sums.containsKey(currSum - k)) {
        res += sums.get(currSum - k);
      }
      if (!sums.containsKey(currSum)) {
        sums.put(currSum, 1);
      } else {
        sums.put(currSum, sums.get(currSum) + 1);
      }

    }
    return res;
  }

  public static boolean checkSubarraySum(int[] nums, int k) {
    Map<Integer, Integer> remainders = new HashMap<Integer, Integer>();
    remainders.put(0, -1);
    int currSum =  0;
    for (int i = 0; i < nums.length; i++) {
      currSum += nums[i];
      int currRemainder = currSum;
      if (k != 0) {
        currRemainder = currSum % k;
      }
      Integer prev = remainders.get(currRemainder);
      if (prev != null) {
        if (i - prev > 2) {
          return true;
        }
      }
      if (!remainders.containsKey(currRemainder)) {
        remainders.put(currRemainder, i);
      }
    }
    return false;
  }

  public static void main(String[] args) {
    int[] nums = new int[]{0,0};
    System.out.println(checkSubarraySum(nums, 0));
  }
}
