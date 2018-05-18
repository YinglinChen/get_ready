package codePractice;
import java.util.*;

public class DFS {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (nums == null || nums.length == 0) {
      return res;
    }
    DFS(nums, new ArrayList<Integer>(), res, 0);
    return res;
  }
  private void DFS(int[] nums, List<Integer> subset, List<List<Integer>> res, int start) {
    res.add(new ArrayList<>(subset));
    for(int i = start; i < nums.length; i ++) {
      if (i == start || nums[i - 1] != nums[i]) {
        subset.add(nums[i]);
        DFS(nums, subset, res, start + 1);
        subset.remove(subset.size() - 1);
      }
    }
  }
  private void swap(int[] nums, int left, int right) {
    int temp = nums[left];
    nums[left] = nums[right];
    nums[right] = temp;
  }
  private void reverse(int[] nums, int left, int right) {
    while (left < right) {
      swap(nums, left, right);
      left ++;
      right --;
    }
  }
  public void nextPermutation(int[] nums) {
    if (nums == null || nums.length == 0) {
      return;
    }
    int i = nums.length - 1;
    while (i > 0 && nums[i] < nums[i - 1]) {
      i --;
    }
    if (i == 0) {
      reverse(nums, 0, nums.length - 1);
      return;
    }
    int pivotIndex = i  - 1;
    int rightIndex = nums.length - 1;
    while (rightIndex > pivotIndex && nums[rightIndex] <= nums[pivotIndex]) {
      rightIndex --;
    }
    swap(nums, pivotIndex, rightIndex);
    reverse(nums, pivotIndex + 1, nums.length - 1);
  }
  public List<String> removeInvalidParentheses(String s) {
    int l = 0;
    int r = 0;
    char[] chars = s.toCharArray();
    for (char c : chars) {
      if (c == '(') {
        l ++;
      } else if (c == ')') {
        r ++;
      }
    }
    if (l >= r) {
      l = l - r;
      r = 0;
    } else {
      r = r - l;
      l = 0;
    }
    List<String> res = new ArrayList<>();
    removeInvalidParenthesesDfs(s, 0, l, r, res);
    return res;
  }
  private boolean isValid (String s) {
    int count = 0;
    for (char c : s.toCharArray()) {
      if (c == '(') {
        count ++;
      } else if (c == ')') {
        count --;
      }
      if (count < 0) {
        return false;
      }
    }
    return count == 0;
  }
  private void removeInvalidParenthesesDfs(String s, int start, int l, int r, List<String> res) {
    if (l == 0 && r == 0) {
      if (isValid(s)) {
        res.add(s);
      }
      return;
    }
    for (int i = start; i < s.length(); i++) {
      if (i != start && s.charAt(i) == s.charAt(i - 1)) {
        continue;
      }
      if (s.charAt(i) == '(' || s.charAt(i) == ')') {
        StringBuilder sb = new StringBuilder(s);
        sb.deleteCharAt(i);
        String curr = sb.toString();
        if (r > 0 && s.charAt(i) == ')' ) {
          removeInvalidParenthesesDfs(curr, i, l , r - 1, res);
        } else if (l > 0 && s.charAt(i) == '(' ) {
          removeInvalidParenthesesDfs(curr, i, l - 1, r, res);
        }
      }
    }
  }
}
