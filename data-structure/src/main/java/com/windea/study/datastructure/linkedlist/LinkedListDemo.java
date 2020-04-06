package com.windea.study.datastructure.linkedlist;

public class LinkedListDemo {
    public static void main(String[] args) {
        var linkedList = new SingleCircleLinkedList<Integer>();
        linkedList.add(1);
        linkedList.add(3);
        linkedList.add(1, 2);
        linkedList.forEach(System.out::println);
        System.out.println();
        System.out.println(linkedList.get(1));
        System.out.println();
        linkedList.set(2, 333);
        linkedList.forEach(System.out::println);
        System.out.println();
        linkedList.removeAt(2);
        linkedList.add(4);
        linkedList.remove(4);
        linkedList.forEach(System.out::println);
        System.out.println();
        System.out.println(linkedList.size());
    }
}
