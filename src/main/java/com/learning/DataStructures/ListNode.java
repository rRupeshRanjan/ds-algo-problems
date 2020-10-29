package com.learning.DataStructures;

public class ListNode {
    public int key;
    public int val;
    public ListNode next;
    public ListNode prev;

    public ListNode() {

    }

    public ListNode(int val){
        this.val = val;
    }

    public ListNode(int key, int val){
        this.key = key;
        this.val = val;
    }
}
