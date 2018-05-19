/*
 * Copyright (C) 2018 Minted Inc.
 */
package codePractice;

import java.util.ArrayList;
import java.util.Arrays;

public class MoveZeros {
  private  static void swap(int[] nums, int left, int right) {
    int temp = nums[left];
    nums[left] = nums[right];
    nums[right] = temp;
  }
  private static void moveZeros(int[] nums) {
    int lastNoneZeroIndex = 0;
    for (int curr = 0; curr < nums.length; curr++) {
      if (nums[curr] != 0) {
        swap(nums, lastNoneZeroIndex, curr);
        lastNoneZeroIndex++;
      }
    }
  }

  public static void main(String[] args) {
    int[] nums = new int[]{1, 2, 0, 3};
    moveZeros(nums);
    System.out.println(Arrays.toString(nums));
  }
}
