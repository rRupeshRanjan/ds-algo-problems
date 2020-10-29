package com.learning;

import java.util.ArrayList;
import java.util.List;

public class Backtracking {
    public static void main(String[] args) {
        System.out.println(findAllPermutation("a1b2"));
    }

    private static List<String> findAllPermutation(String input) {
        List<String> returnList = new ArrayList<>();

        findAllPermutationHelper(returnList, input, 0);
        return returnList;
    }

    private static void findAllPermutationHelper(List<String> returnList, String input, int start) {
        returnList.add(input);

        for(int i = start; i<input.length(); i++){
            char[] array = input.toCharArray();
            if(Character.isLetter(input.charAt(i))) {
                array[i] = (Character.isUpperCase(input.charAt(i)))
                        ? Character.toLowerCase(input.charAt(i))
                        : Character.toUpperCase(input.charAt(i));
                findAllPermutationHelper(returnList, String.valueOf(array), i+1);
            }
        }
    }
}
