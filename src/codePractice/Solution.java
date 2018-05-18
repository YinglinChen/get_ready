package codePractice;
import java.util.*;



class Solution {
  public boolean isMatch(String s, String p) {
    boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
    match[0][0] = true;
    // deal with case pattern like: a*, a*b*c
    for (int i = 1; i <= p.length(); i++) {
      if (p.charAt(i - 1) == '*') {
        match[0][i] = match[0][i - 2];
      }
    }
    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= p.length(); j++) {
        if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
          match[i][j] = match[i - 1][j - 1];
        } else if (p.charAt(j - 1) == '*') {
          if (j > 1) {
            match[i][j] = match[i][j - 2];
            if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
              match[i][j] = match[i - 1][j]  || match[i][j];
            }
          }
        } else {
          match[i][j] = false;
        }
      }
    }
    return match[s.length()][p.length()];
  }
}