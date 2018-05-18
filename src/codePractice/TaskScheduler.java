/*
 * Copyright (C) 2018 Minted Inc.
 */
package codePractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
/*
key point: always consume the most significant task first. since if we are not consuming the most
 significant task first, later it will introduce more idle slot.
 */

public class TaskScheduler {
  static int leastInterval1(char[] tasks, int n) {
    int[] map = new int[26];
    for (char task : tasks) {
      map[task - 'A']++;
    }
    Arrays.sort(map);
    int time = 0;
    while (map[25] > 0) {
      int i = 0;
      while (i <= n) {
        if (map[25] == 0) {
          break;
        }
        if (i < 26 && map[25 - i] > 0) {
          map[25 - i]--;
        }
        i++;
        time++;
      }
      Arrays.sort(map);
    }
    return time;
  }

  /*
  use priority queue
   */
  static int leastInterval2(char[] tasks, int n) {
    int[] map = new int[26];
    for (char task : tasks) {
      map[task - 'A']++;
    }
    PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
    for (int m : map) {
      if (m > 0) {
        queue.add(m);
      }
    }
    int time = 0;
    while (!queue.isEmpty()) {
      int i = 0;
      List<Integer> temp = new ArrayList<>();
      while (i <= n) {
        if (!queue.isEmpty()) {
          if (queue.peek() > 1) {
            temp.add(queue.poll() - 1);
          }
          else {
            queue.poll();
          }
        }
        time++;
        i++;
        if (queue.isEmpty() && temp.size() == 0) {
          break;
        }
      }
      temp.stream().forEach(e -> queue.add(e));
    }
    return time;
  }

  public static void main(String[] args) {
    char[] tasks = new char[]{'A', 'A', 'A', 'B', 'B', 'B'};
    int n = 2;
    System.out.println(leastInterval1(tasks, n));
    System.out.println(leastInterval2(tasks, n));
  }
}
