package com.windea.study.datastructure.linkedlist;

import java.util.Iterator;
import java.util.Objects;

/**
 * 双向链表。
 */
public class DoubleLinkedList<T> implements LinkedList<T> {
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
        //与单向链表的代码逻辑一样

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
        ////不仅需要将尾节点的next指向新的节点
        ////还要将新的节点的prev指向尾节点
        //var node = new Node<>(value);
        //temp.next = node;
        //node.prev = temp;
        //不仅需要将尾节点的next指向新的节点
        //还要将新的节点的prev指向尾节点
        var node = new Node<>(value);
        tail.next = node;
        node.prev = tail;
        tail = tail.next;
        size++;
    }

    @Override
    public void add(int index, T value) {
        //如果索引为负数，则抛出异常
        if(index < 0)
            throw new IndexOutOfBoundsException();

        var tempIndex = 0;
        var temp = head.next;
        while(temp != null) {
            //这里的代码逻辑发生了变化
            if(tempIndex == index) {
                //将node插入到temp的前面
                var node = new Node<>(value);
                temp.prev.next = node;
                node.prev = temp.prev;
                temp.prev = node;
                node.next = temp;
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
        //可以直接找到要删除的这个节点，找到后自我删除即可

        //如果链表为空，则抛出异常
        if(isEmpty())
            throw new IndexOutOfBoundsException();

        var temp = head.next;
        while(temp != null) {
            //这里的代码逻辑发生了变化
            if(Objects.equals(temp.value, value)) {
                temp.prev.next = temp.next;
                //这里temp.next可能为空
                if(temp.next != null) {
                    temp.next.prev = temp.prev;
                }
                //尾节点可能需要前移
                if(temp.prev.next == null) {
                    tail = temp.prev;
                }
                size--;
                return;
            }
            temp = temp.next;
        }
    }

    @Override
    public void removeAt(int index) {
        //可以直接找到要删除的这个节点，找到后自我删除即可

        //如果链表为空或者索引为负数，则抛出异常
        if(isEmpty() || index < 0)
            throw new IndexOutOfBoundsException();

        var tempIndex = 0;
        var temp = head.next;
        while(temp != null) {
            //这里的代码逻辑发生了变化
            if(tempIndex == index) {
                temp.prev.next = temp.next;
                //这里temp.next可能为空
                if(temp.next != null) {
                    temp.next.prev = temp.prev;
                }
                //尾节点可能需要前移
                if(temp.prev.next == null) {
                    tail = temp.prev;
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

    private static class Node<T> {
        private T value;
        private Node<T> prev;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
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
            //可以直接移除当前节点
            current.prev.next = current.next;
            //这里temp.next可能为空
            if(current.next != null) {
                current.next.prev = current.prev;
            }
            size--;
        }
    }
}
