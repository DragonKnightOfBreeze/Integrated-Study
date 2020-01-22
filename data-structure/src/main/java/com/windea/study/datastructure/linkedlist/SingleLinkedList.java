package com.windea.study.datastructure.linkedlist;

import java.util.Iterator;
import java.util.Objects;

/**
 * 单向链表。
 */
public class SingleLinkedList<T> implements LinkedList<T> {
	private Node<T> head = new Node<>(null);
	private Node<T> tail = head;
	private int size = 0;

	@Override
	public boolean isEmpty() {
		//return head.next == null;
		return size == 0;
	}

	@Override
	public int size() {
		//var size = 0;
		//var temp = head.next;
		//while(temp != null) {
		//	size++;
		//	temp = temp.next;
		//}
		//return size;
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
		//如果链表为空或者索引为负数，则抛出异常
		if(isEmpty() || index < 0)
			throw new IndexOutOfBoundsException();

		var tempIndex = 0;
		var temp = head.next;
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
		//var temp = head;
		//while(temp.next != null) {
		//	temp = temp.next;
		//}
		//temp.next = new Node<>(value);
		tail.next = new Node<>(value);
		tail = tail.next;
		size++;
	}

	@Override
	public void add(int index, T value) {
		//如果索引为负数，则抛出异常
		if(index < 0)
			throw new IndexOutOfBoundsException();

		//重点：找到要插入的节点的前一个节点
		var tempIndex = 0; //实际应该初始化为-1，比较时比索引小1
		var temp = head;
		while(temp.next != null) {
			if(tempIndex == index) {
				var node = new Node<>(value);
				node.next = temp.next;
				temp.next = node;
				size++;
				return;
			}
			tempIndex++;
			temp = temp.next;
		}
		//如果这时还没有找到，说明索引越界，则抛出异常
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void remove(T value) {
		//如果链表为空，则抛出异常
		if(isEmpty())
			throw new IndexOutOfBoundsException();

		//重点：找到要删除的节点的前一个节点
		var temp = head;
		while(temp.next != null) {
			if(Objects.equals(temp.next.value, value)) {
				temp.next = temp.next.next;
				//尾节点可能需要前移
				if(temp.next == null) {
					tail = temp;
				}
				size--;
				return;
			}
			temp = temp.next;
		}
	}

	@Override
	public void removeAt(int index) {
		//如果链表为空或者索引为负数，则抛出异常
		if(isEmpty() || index < 0)
			throw new IndexOutOfBoundsException();

		//重点：找到要删除的节点的前一个节点
		var tempIndex = 0; //实际应该初始化为-1，比较时比索引小1
		var temp = head;
		while(temp.next != null) {
			if(tempIndex == index) {
				temp.next = temp.next.next;
				//尾节点可能需要前移
				if(temp.next == null) {
					tail = temp;
				}
				size--;
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
		private Node<T> current = head;

		private Itr() {
		}

		@Override
		public boolean hasNext() {
			return current.next != null;
		}

		@Override
		public T next() {
			current = current.next;
			return current.value;
		}

		@Override
		public void remove() {
			//不能直接移除当前节点
			SingleLinkedList.this.remove(current.value);
		}
	}

	private static class Node<T> {
		private T value;
		private Node<T> next;

		private Node(T value) {
			this.value = value;
		}
	}
}
