package codePractice;

import java.util.LinkedList;
import java.util.Queue;

public class Tree {
  public static int getNodeLevel(TreeNode root, TreeNode node, int level) {
    if (root == null) {
      return 0;
    }
    level ++;
    if (root.val == node.val) {
      return level;
    }
    int leftLevel = getNodeLevel(root.left, node, level);
    int rightLevel = getNodeLevel(root.right, node, level);
    return leftLevel == 0 ? rightLevel : leftLevel;
  }
  public static void getNodeLevel2(TreeNode root, TreeNode node, int level, int[] res) {
    if (root == null) {
      return;
    }
    level ++;
    if (root.val == node.val) {
      res[0] = level;
      return;
    }
    getNodeLevel2(root.left, node, level, res);
    getNodeLevel2(root.right, node, level, res);
  }

  public static int getNodeLevelIterative(TreeNode root, TreeNode node) {
    if (root == null) {
      return 0;
    }
    if (root == node) {
      return 1;
    }
    int level = 1;
    Queue<TreeNode> q1 = new LinkedList<>();
    Queue<TreeNode> q2= new LinkedList<>();
    q1.add(root);
    while (!q1.isEmpty() || !q2.isEmpty()) {
      while (!q1.isEmpty()) {
        TreeNode curr = q1.poll();
        if (curr.val == node.val) {
          return level;
        }
        if (curr.left != null) {
          q2.add(curr.left);
        }
        if (curr.right != null) {
          q2.add(curr.right);
        }
      }
      while (!q2.isEmpty()) {
        TreeNode curr = q2.poll();
        if (curr.val == node.val) {
          return level;
        }
        if (curr.left != null) {
          q1.add(curr.left);
        }
        if (curr.right != null) {
          q1.add(curr.right);
        }
      }
      level ++;
    }
    return -1;
  }
  public static int maxPathSum(TreeNode root) {
    int[] res = new int[] {Integer.MIN_VALUE};
    helper(root, res);
    return res[0];
  }
  private static int helper(TreeNode root, int[] res) {
    if (root == null) {
      return 0;
    }
    int leftSum = helper(root.left, res);
    int rightSum = helper(root.right, res);

    res[0] = Math.max(res[0], leftSum + rightSum + root.val);

    return root.val + Math.max(leftSum, rightSum);
  }

  public static int maxPathSumI(TreeNode root) {
    int[] res = new int[] {Integer.MIN_VALUE};
    maxPathSumHelper(root, 0, res);
    return res[0];
  }
  private static void maxPathSumHelper(TreeNode root, int sum, int[] res) {
    if (root == null) {
      return;
    }
    sum += root.val;
    if (root.left == null && root.right == null) {
      res[0] = Math.max(res[0], sum);
    }
    maxPathSumHelper(root.left, sum, res);
    maxPathSumHelper(root.right, sum, res);
  }

    private static TreeNode prev = null;

    public static void flatten(TreeNode root) {
      if (root == null)
        return;
      flatten(root.right);
      flatten(root.left);
      root.right = prev;
      root.left = null;
      prev = root;
    }
    private static TreeNode head = null;
    public static void flatten2(TreeNode root) {
      if (root == null) {
        return;
      }
      if (prev == null) {
        head = root;
      } else {
        root.left = prev;
        prev.right = root;
      }
      prev = root;
      flatten2(root.left);
      flatten2(root.right);
    }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
//    root.left.left = new TreeNode(3);
//    root.left.right = new TreeNode(4);
//    root.right = new TreeNode(5);
    TreeNode temp = new TreeNode(4);
    int[] res = new int[]{0};
//    getNodeLevel2(root, temp, 0, res);
//    System.out.println(res[0]);
    flatten2(root);
    System.out.println(head);
//    System.out.println(getNodeLevelIterative(root, temp));
//    System.out.println(maxPathSumI(root));
  }
}
