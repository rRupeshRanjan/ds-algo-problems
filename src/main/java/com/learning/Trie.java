package com.learning;

import com.learning.DataStructures.TrieNode;

public class Trie {
    TrieNode trieNode;

    public Trie() {
        this.trieNode = new TrieNode('*');
    }

    public void insert(String word) {
        TrieNode parent = trieNode;
        for(int i=0; i<word.length(); i++){
            if (parent.children[word.charAt(i)-'a'] == null)
                parent.children[word.charAt(i)-'a'] = new TrieNode(word.charAt(i));
            parent = parent.children[word.charAt(i)-'a'];
            if(i==word.length()-1)
                parent.isEnd = true;
        }
    }

    public boolean search(String word) {
        TrieNode parent = trieNode;
        for(int i=0; i<word.length(); i++){
            if (parent.children[word.charAt(i)-'a']==null || (i==word.length()-1 && !parent.children[word.charAt(i)-'a'].isEnd))
                return false;
            parent = parent.children[word.charAt(i)-'a'];
        }

        return true;
    }

    public boolean startsWith(String prefix) {
        TrieNode parent = trieNode;
        for(int i=0; i<prefix.length(); i++){
            if (parent.children[prefix.charAt(i)-'a']==null)
                return false;
            parent = parent.children[prefix.charAt(i)-'a'];
        }

        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("ab");
        trie.insert("abc");
        System.out.println(trie.search("abc"));
        System.out.println(trie.startsWith("m"));
    }
}
