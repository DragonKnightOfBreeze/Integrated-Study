package com.windea.study.datastructure.linkedlist;

import java.util.Iterator;

/**
 * 单向链表。
 */
public class SingleLinkedList<T> implements LinkedList<T> {
	private Node<T> head = new Node<>(null);

	@Override
	public boolean isEmpty() {
		return head.next == null;
	}

	@Override
	public int size() {
		var size = 0;
		var temp = head.next;
		while(temp != null) {
			size++;
			temp = temp.next;
		}
		return size;
	}

	@Override
	public T get(int index) {
		//如果链表为空或者索引为负数，则抛出异常
		if(isEmpty() || index < 0)
			throw new IndexOutOfBoundsException();

		var tempIndex = 0;
		var temp = head.next;
		while(temp != null) {
			if(tempIndex == index) {
				return temp.current;
			}
			tempIndex++;
			temp = temp.next;
		}
		//如果这时还没有找到，说明索引越界，则抛出异常
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void add(T value) {
		var temp = head;
		while(temp.next != null) {
			temp = temp.next;
		}
		temp.next = new Node<>(value);
	}

	@Override
	public void add(int index, T value) {
		//如果链表为空或者索引为负数，则抛出异常
		if(isEmpty() || index < 0)
			throw new IndexOutOfBoundsException();

		//重点：找到要插入的节点的前一个节点
		var tempIndex = 0; //实际应该初始化为-1，比较时比索引小1
		var temp = head;
		while(temp.next != null) {
			if(tempIndex == index) {
				var node = new Node<>(value);
				node.next = temp.next;
				temp.next = node;
				return;
			}
			tempIndex++;
			temp = temp.next;
		}
		//如果这时还没有找到，说明索引越界，则抛出异常
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void set(int index, T value) {
		//如果链表为空或者索引为负数，则抛出异常
		if(isEmpty() || index < 0)
			throw new IndexOutOfBoundsException();

		var tempIndex = 0;
		var temp = head.next;
		while(temp != null) {
			if(tempIndex == index) {
				temp.current = value;
				return;
			}
			tempIndex++;
			temp = temp.next;
		}
		//如果这时还没有找到，说明索引越界，则抛出异常
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void remove(int index) {
		//如果链表为空或者索引为负数，则抛出异常
		if(isEmpty() || index < 0)
			throw new IndexOutOfBoundsException();

		//重点：找到要删除的节点的前一个节点
		var tempIndex = 0; //实际应该初始化为-1，比较时比索引小1
		var temp = head;
		while(temp.next != null) {
			if(tempIndex == index) {
				temp.next = temp.next.next;
				return;
			}
			tempIndex++;
			temp = temp.next;
		}
		//如果这时还没有找到，说明索引越界，则抛出异常
		throw new IndexOutOfBoundsException();
	}

	@Override
	public Iterator<T> iterator() {
		return new Itr();
	}


	private class Itr implements Iterator<T> {
		private Node<T> currentNode;

		private Itr() {
			this.currentNode = head;
		}

		@Override
		public boolean hasNext() {
			return currentNode.next != null;
		}

		@Override
		public T next() {
			currentNode = currentNode.next;
			return currentNode.current;
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
