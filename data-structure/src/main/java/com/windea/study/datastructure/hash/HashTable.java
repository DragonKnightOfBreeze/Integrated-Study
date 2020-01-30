package com.windea.study.datastructure.hash;

import com.windea.study.datastructure.linkedlist.LinkedList;
import com.windea.study.datastructure.linkedlist.SingleLinkedList;

/**
 * 哈希表。
 */
@SuppressWarnings("unchecked")
public class HashTable<K, V> {
	private int maxSize;
	private Object[] keys = new Object[maxSize];
	//这个链表可以没有头结点，head直接指向第一个元素
	private LinkedList<V>[] buckets = (SingleLinkedList<V>[]) new SingleLinkedList[maxSize];

	public HashTable(int maxSize) {
		this.maxSize = maxSize;
	}

	public V get(K key) {
		var hash = hash(key);
		var bucket = buckets[hash];
		//发生异常时总会返回null
		try {
			//TODO
			return bucket.get(hash);
		} catch(Exception e) {
			return null;
		}
	}

	public void put(K key, V value) {
		//根据key决定value应该加入到哪条链表
		var hash = hash(key);
		var bucket = buckets[hash];
		//如果为null，则需要先初始化
		if(bucket == null) {
			bucket = new SingleLinkedList<>();
		}
		bucket.add(value);
	}

	public void remove(K key) {
		var hash = hash(key);
		var bucket = buckets[hash];
		//如果为null，则直接返回
		if(bucket == null) {
			return;
		}
		//TODO
		bucket.removeAt(hash);
	}

	//散列函数，这里使用一个简单取模法
	private int hash(K key) {
		return key.hashCode() % maxSize;
	}
}
