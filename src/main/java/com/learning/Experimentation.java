package com.learning;

import com.learning.DataStructures.TreeNode;

public class Experimentation {
    public static void main(String[] args) {
        System.out.println(findMaximumXOR(new int[]{14,70,53,83,49,91,36,80,92,51,66,70}));
    }

    public static int findMaximumXOR(int[] nums) {
        TreeNode root = new TreeNode('*');
        String[] strs = new String[nums.length];
        int i = 0, max = Integer.MIN_VALUE;

        for (int num : nums) {
            String binary = decimalToBinary(num);
            strs[i++] = binary;
            TreeNode curr = root;
            for (char ch : binary.toCharArray()) {
                if (ch == '0') {
                    if (curr.left == null) curr.left = new TreeNode(0);
                    curr = curr.left;
                } else if (ch == '1') {
                    if (curr.right == null) curr.right = new TreeNode(1);
                    curr = curr.right;
                }
            }
        }

        StringBuilder maxString = new StringBuilder("00000000000000000000000000000000");
        for(String str: strs) {
            StringBuilder sb = new StringBuilder();
            TreeNode curr = root;
            for(char ch: str.toCharArray()) {
                if(ch=='0') {
                    sb.append((curr.right == null) ? 0 : 1);
                    curr = (curr.right == null) ? curr.left : curr.right;
                } else {
                    sb.append((curr.left == null) ? 0 : 1);
                    curr = (curr.left == null) ? curr.right : curr.left;
                }
            }

            if(sb.toString().compareTo(maxString.toString()) > 0) {
                maxString = sb;
            }
        }

//        for(String str: strs) {
//            StringBuilder sb = new StringBuilder();
//            TreeNode curr = root;
//            for(char ch: str.toCharArray()) {
//                if(ch=='0') {
//                    sb.append((curr.right == null) ? 0 : 1);
//                    curr = (curr.right == null) ? curr.left : curr.right;
//                } else if(ch=='1') {
//                    sb.append((curr.left == null) ? 0 : 1);
//                    curr = (curr.left == null) ? curr.right : curr.left;
//                }
//            }
//
//            max = Math.max(max, binaryToDecimal(sb));
//        }

        return binaryToDecimal(maxString);
    }
    private static String decimalToBinary(int num) {
        StringBuilder sb = new StringBuilder();
        while(num > 0) {
            sb.append(num%2);
            num /= 2;
        }

        while(sb.length() < 32)
            sb.append(0);

        return sb.reverse().toString();
    }

    private static int binaryToDecimal(StringBuilder binary) {
        int num = 0, i = 0;

        for(char ch: binary.reverse().toString().toCharArray()) {
            int digit = Character.getNumericValue(ch);
            num += digit << i;
            i++;
        }

        return num;
    }
}
