package com.learning.DataStructures;

public class TrieNode {
    char val;
    public TrieNode[] children;
    public boolean isEnd;

    public TrieNode(char val) {
        this.val = val;
        this.children = new TrieNode[26];
        this.isEnd = false;
    }
}
