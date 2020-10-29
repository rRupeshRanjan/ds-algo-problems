package com.learning.DataStructures;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    Map<Integer, ListNode> map;
    int capacity;
    ListNode start, end;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if(map.containsKey(key)) {
            ListNode temp = map.get(key);
            removeNode(temp);
            addAtFront(temp);
            return temp.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            ListNode temp = map.get(key);
            removeNode(temp);
            temp.val = value;
            addAtFront(temp);
        } else {
            ListNode entry = new ListNode(key, value);
            if(map.size()==capacity){
                map.remove(end.key);
                removeNode(end);
            }
            map.put(key, entry);
            addAtFront(entry);
        }
    }

    public void addAtFront(ListNode node) {
        node.next=start;
        node.prev=null;
        if(start!=null) start.prev = node;
        start = node;
        if(end==null) end = start;
    }

    public void removeNode(ListNode node) {
        if(node.prev!=null) node.prev.next = node.next;
        else start = node.next;

        if(node.next!=null) node.next.prev = node.prev;
        else end = node.prev;
    }
}
