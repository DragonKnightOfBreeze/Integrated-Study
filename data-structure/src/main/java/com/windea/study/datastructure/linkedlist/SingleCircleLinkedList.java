package com.windea.study.datastructure.linkedlist;

import java.util.Iterator;
import java.util.Objects;

/**
 * 单向循环链表。
 */
public class SingleCircleLinkedList<T> implements LinkedList<T> {
    private Node<T> first;
    private Node<T> last;
    private int size = 0;

    @Override
    public boolean isEmpty() {
        //return first != null;
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
        while(tempIndex < size) {
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
        while(tempIndex < size) {
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
        if(isEmpty()) {
            first = new Node<>(value);
            first.next = first;
            last = first;
        } else {
            last.next = new Node<>(value);
            last = last.next;
            last.next = first;
        }
        size++;
    }

    @Override
    public void add(int index, T value) {
        if(index < 0)
            throw new IndexOutOfBoundsException();

        if(isEmpty() && index == 0) {
            first = new Node<>(value);
            first.next = first;
            last = first;
            size++;
        } else if(isEmpty() && index != 0) {
            throw new IndexOutOfBoundsException();
        } else {
            var tempIndex = 0;
            var temp = last;
            while(tempIndex < size) {
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
    }

    @Override
    public void remove(T value) {
        //如果链表为空，则抛出异常
        if(isEmpty())
            throw new IndexOutOfBoundsException();

        if(size == 1 && first.value == value) {
            first = null;
            last = null;
            size--;
        } else {
            //重点：找到要删除的节点的前一个节点
            var tempIndex = 0;
            var temp = last;
            while(tempIndex < size) {
                if(Objects.equals(temp.next.value, value)) {
                    temp.next = temp.next.next;
                    //尾节点可能需要前移
                    if(tempIndex == size - 1) {
                        last = temp;
                    }
                    size--;
                    return;
                }
                tempIndex++;
                temp = temp.next;
            }
        }
    }

    @Override
    public void removeAt(int index) {
        if(isEmpty() || index < 0)
            throw new IndexOutOfBoundsException();

        if(size == 1 && index == 0) {
            first = null;
            last = null;
            size--;
        } else {
            //重点：找到要删除的节点的前一个节点
            var tempIndex = 0;
            var temp = last;
            while(tempIndex < size) {
                if(tempIndex == index) {
                    temp.next = temp.next.next;
                    //尾节点可能需要前移
                    if(tempIndex == size - 1) {
                        last = temp;
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
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
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
}
