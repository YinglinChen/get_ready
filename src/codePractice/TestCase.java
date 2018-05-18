package codePractice;
import java.util.*;

public class TestCase {
  public static void main (String[] args) {
    DFS test = new DFS();
    int [] nums = {1, 5, 1};
//    test.nextPermutation(nums);
//    List<String> res = test.removeInvalidParentheses(")(");
    Solution s = new Solution();
    List<String> wordDict = new ArrayList<>();
    String[] otherList = new String[] {"cat", "cats", "and", "sand", "dog"};
    wordDict.addAll(Arrays.asList(otherList));
    System.out.println(s.isMatch("ab", "ab*"));

  }
}
