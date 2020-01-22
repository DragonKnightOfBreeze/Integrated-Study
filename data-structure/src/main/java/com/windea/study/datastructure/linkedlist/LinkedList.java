package com.windea.study.datastructure.linkedlist;

public interface LinkedList<T> extends Iterable<T> {
	boolean isEmpty();

	int size();

	T get(int index);

	void set(int index, T value);

	void add(T value);

	void add(int index, T value);

	void remove(T value);

	void removeAt(int index);
}
