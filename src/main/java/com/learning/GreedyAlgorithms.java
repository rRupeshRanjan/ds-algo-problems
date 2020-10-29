package com.learning;

import java.util.Deque;
import java.util.LinkedList;

public class GreedyAlgorithms {
    public static void main(String[] args) {
        System.out.println(removeKdigits("10200", 1));
    }

    public static String removeKdigits(String num, int k) {
        // Greedy algorithm.
        // I dont care how it will be later,
        // But I want best solution as of now

        int size = num.length();
        if(k==size) return "0";
        Deque<Character> deque = new LinkedList<>();
        int i=0;
        StringBuilder result = new StringBuilder();

        while(i<size){
            char temp = num.charAt(i);
            while(k>0 && !deque.isEmpty() && deque.peekFirst() > temp){
                deque.removeFirst();
                k--;
            }
            deque.addFirst(temp);
            i++;
        }

        while(k-->0) deque.removeFirst();
        while(!deque.isEmpty() && deque.peekLast()=='0') deque.removeLast();
        while(!deque.isEmpty()) result.append(deque.removeLast());

        return result.toString();
    }
}
