package com.learning;

import com.learning.DataStructures.LRUCache;

public class Implementations {
    public static void main(String[] args) {
        lruImplementation();
    }

    private static void lruImplementation() {
        LRUCache lruCache = new LRUCache(2);

        lruCache.put(1,1);
        lruCache.put(2,2);
        System.out.println(lruCache.get(1));
        lruCache.put(3,3);
        System.out.println(lruCache.get(2));
        lruCache.put(4,4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}
