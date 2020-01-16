package com.windea.study.datastructure.linkedlist;

import java.util.Iterator;

//TODO

public class SingleCircleLinkedList<T> implements LinkedList<T> {
	private Node<T> first = new Node<>(null);

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public T get(int index) {
		return null;
	}

	@Override
	public void add(T value) {

	}

	@Override
	public void add(int index, T value) {

	}

	@Override
	public void set(int index, T value) {

	}

	@Override
	public void remove(int index) {

	}

	@Override
	public Iterator<T> iterator() {
		return null;
	}

	private class Itr implements Iterator<T> {
		private Itr() {
		}

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public T next() {
			return null;
		}
	}

	private static class Node<T> {
		private T current;
		private Node<T> next;

		private Node(T current) {
			this.current = current;
		}
	}
}
