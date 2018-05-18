/*
 * Copyright (C) 2018 Minted Inc.
 */
package codePractice;

public class IntegerToEnglishWords {
  private static String[] belowTwenty = new String[] {"","One", "Two", "Three", "Four", "Five",
      "Six",
      "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
      "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

  private static String[] belowHundred = new String[] {"", "Ten", "Twenty", "Thirty", "Fourty",
      "Fifty",
      "Sixty",
      "Seventy", "Eighty", "Ninty"};

  private static String helper(int num) {
    String res = "";
    if (num < 20) {
      res = belowTwenty[num];
    } else if (num < 100) {
      res = belowHundred[num / 10] + " " + belowTwenty[num % 10];
    } else if (num < 1000) {
      res = helper(num / 100) + " Hundred " + helper(num % 100);
    } else if (num < 1000000) {
      res = helper(num / 1000) + " Thousand " + helper(num % 1000);
    } else if (num < 1000000000) {
      res = helper(num / 1000000) + " Million " + helper(num % 1000000);
    } else {
      res = helper(num / 1000000000) + " Billion " + helper(num % 1000000000);
    }
    return res;
  }

  private static String numberToWords(int num) {
    if (num == 0) {
      return "Zero";
    }
    return helper(num);
  }


  public static void main(String[] arg) {
    System.out.println(numberToWords(10010));
  }
}
