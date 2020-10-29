package com.learning;

import java.util.*;

public class DynamicProgramming {
    private static int[][] memoPascal;
    public static void main(String[] args) {
        System.out.println(minimumDeleteSum("delete", "leet"));
//        System.out.println(minDistance("horse", "ros"));
//        System.out.println(countBits(7));
//        System.out.println(longestCommonSubsequence("horse", "ros"));
//        System.out.println(longestIncreasingSubsequence(new int[]{10,9,2,5,3,7,101,18}));
//        getPascalRow(20).forEach(System.out::println);
//        System.out.println(climbStairs(44));
    }

    public static int climbStairs(int n) {
        if(n==1)
            return 1;

        int first = 1;
        int second = 1;
        int latest = 0;

        for(int i =2; i<=n; i++){
            latest = first + second;
            second = first;
            first = latest;
        }

        return latest;
    }

    public static List<Integer> getPascalRow(int rowIndex) {
        memoPascal = new int[rowIndex+1][rowIndex+1];
        List<Integer> ans = new ArrayList<>();
        for(int i=0; i<=rowIndex; i++){
            ans.add(getPascalRowValue(rowIndex, i));
        }

        return ans;
    }

    public static int getPascalRowValue(int row, int col){
        if(memoPascal[row][col]!=0)
            return memoPascal[row][col];

        if(col==0 || row==col) {
            memoPascal[row][col] = 1;
            return 1;
        }

        int value = getPascalRowValue(row-1, col) + getPascalRowValue(row-1, col-1);
        memoPascal[row][col] = value;
        return value;
    }

    public static int longestIncreasingSubsequence(int[] nums) {
        if(nums.length==0)
            return 0;

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;

        for(int i=1; i<nums.length; i++){
            int maxval = 0;
            for(int j=0; j<i; j++){
                if(nums[i] > nums[j])
                    maxval = Math.max(maxval, dp[j]);
            }
            dp[i] = maxval+1;
            maxans = Math.max(maxans, dp[i]);
        }

        return maxans;
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];

        for(int i=1; i<=text1.length(); i++) {
            for(int j=1; j<=text2.length(); j++) {
                if(text1.charAt(i-1)==text2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1]+1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        return dp[text1.length()][text2.length()];
    }

    public static int[] countBits(int num) {
        int[] result = new int[num+1];
        int a = 1, lastPow = 0;
        while(a<=num) {
            result[a] = 1;
            a*=2;
        }

        result[0] = 0;
        for(int i=1; i<=num; i++){
            if(result[i]==1){
                lastPow = i;
            } else
                result[i] = 1 + result[i-lastPow];
        }

        return result;
    }

    public static int minDistance(String word1, String word2) {
        int l1 = word1.length(), l2 = word2.length();
        int[][] dp = new int[l1+1][l2+1];

        for(int i=0; i<=l1; i++) {
            for(int j=0; j<=l2; j++) {
                if(i*j==0)
                    dp[i][j] = i+j;
                else if(word1.charAt(i-1) == word2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
            }
        }

        return dp[l1][l2];
    }

    public static int minimumDeleteSum(String s1, String s2) {
        int l1 = s1.length(), l2 = s2.length();
        int[][] dp = new int[l1+1][l2+1];

        for(int i=0; i<=l1; i++) {
            for(int j=0; j<=l2; j++) {
                if(i==0 && j==0) continue;
                else if(i*j == 0)
                    dp[i][j] = (i==0) ? (int)s2.charAt(j-1) + dp[i][j-1] : (int) s1.charAt(i-1) + dp[i-1][j];
                else if(s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = Math.min(dp[i-1][j] + (int)s1.charAt(i-1),
                            dp[i][j-1] + (int)s2.charAt(j-1));
            }
        }

        return dp[l1][l2];
    }
}
