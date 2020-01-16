package com.windea.study.datastructure.linkedlist;

public class LinkedListDemo {
	public static void main(String[] args) {
		//var linkedList = new SingleLinkedList<Integer>();
		var linkedList = new DoubleLinkedList<Integer>();
		linkedList.add(1);
		linkedList.add(3);
		linkedList.add(1, 2);
		linkedList.forEach(System.out::println);
		System.out.println(linkedList.get(1));
		linkedList.set(2, 333);
		linkedList.forEach(System.out::println);
		linkedList.remove(2);
		linkedList.forEach(System.out::println);
		System.out.println(linkedList.size());
	}
}
