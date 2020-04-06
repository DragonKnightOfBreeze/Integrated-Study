package com.windea.study.datastructure.hashtable;

import com.windea.study.datastructure.linkedlist.LinkedList;
import com.windea.study.datastructure.linkedlist.SingleLinkedList;

/**
 * 哈希表。
 */
@SuppressWarnings("unchecked")
public class HashTable<K, V> {
    private int maxSize;
    //这个链表可以没有头结点，head直接指向第一个元素
    private LinkedList<V>[] buckets;

    public HashTable(int maxSize) {
        this.maxSize = maxSize;
        buckets = (SingleLinkedList<V>[]) new SingleLinkedList[maxSize];
        //需要初始化数组中的每个链表，这里可以延迟加载
        //for(int i = 0; i < maxSize; i++) {
        //	buckets[i]=new SingleLinkedList<>();
        //}
    }

    public V get(K key) {
        var hash = hash(key);
        //发生异常时总会返回null
        try {
            //哈希码可能重复
            return buckets[hash].get(0);
        } catch(Exception e) {
            return null;
        }
    }

    public void put(K key, V value) {
        //根据key决定value应该加入到哪条链表
        var hash = hash(key);
        //如果为null，则需要先初始化
        if(buckets[hash] == null) {
            buckets[hash] = new SingleLinkedList<>();
        }
        buckets[hash].add(value);
    }

    public void remove(K key) {
        var hash = hash(key);
        //如果为null，则直接返回
        if(buckets[hash] == null) {
            return;
        }
        //哈希码可能重复
        buckets[hash].removeAt(0);
    }

    //散列函数，这里使用一个简单取模法
    private int hash(K key) {
        return key.hashCode() % maxSize;
    }
}
