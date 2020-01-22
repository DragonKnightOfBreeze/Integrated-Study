package com.windea.study.datastructure.linkedlist;

import java.util.Iterator;

//TODO

public class DoubleCircleLinkedList<T> implements LinkedList<T> {
	private Node<T> first;
	private Node<T> last;
	private int size = 0;

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T get(int index) {
		if(isEmpty() || index < 0)
			throw new IndexOutOfBoundsException();

		var tempIndex = 0;
		var temp = first;
		while(temp != null) {
			if(tempIndex == index) {
				return temp.value;
			}
			tempIndex++;
			temp = temp.next;
		}
		//如果这时还没有找到，说明索引越界，则抛出异常
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void set(int index, T value) {
		if(isEmpty() || index < 0)
			throw new IndexOutOfBoundsException();

		var tempIndex = 0;
		var temp = first;
		while(temp != null) {
			if(tempIndex == index) {
				temp.value = value;
				return;
			}
			tempIndex++;
			temp = temp.next;
		}
		//如果这时还没有找到，说明索引越界，则抛出异常
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void add(T value) {

	}

	@Override
	public void add(int index, T value) {

	}

	@Override
	public void remove(T value) {

	}

	@Override
	public void removeAt(int index) {

	}

	@Override
	public Iterator<T> iterator() {
		return new Itr();
	}

	private class Itr implements Iterator<T> {
		private int currentIndex = 0;
		private Node<T> current = first;

		private Itr() {
		}

		@Override
		public boolean hasNext() {
			return currentIndex < size;
		}

		@Override
		public T next() {
			var result = current.value;
			currentIndex++;
			current = current.next;
			return result;
		}
	}

	private static class Node<T> {
		private T value;
		private Node<T> next;
		private Node<T> prev;

		private Node(T value) {
			this.value = value;
		}
	}
}
