package com.windea.study.datastructure.hash;

import com.windea.study.datastructure.linkedlist.LinkedList;
import com.windea.study.datastructure.linkedlist.SingleLinkedList;

@SuppressWarnings("unchecked")
public class HashTable<K, V> {
	private int maxSize;
	private Object[] keys = new Object[maxSize];
	private LinkedList<V>[] buckets = (SingleLinkedList<V>[]) new SingleLinkedList[maxSize];

	public HashTable(int maxSize) {
		this.maxSize = maxSize;
	}
}
