package com.windea.study.datastructure.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

public class SingleHeroLinkedListDemo {
    public static void main(String[] args) {
        var hero1 = new HeroNode(1, "宋江", "及时雨");
        var hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        var hero3 = new HeroNode(3, "吴用", "智多星");

        var linkedList = new SingleHeroLinkedList();
        linkedList.add(hero2);
        linkedList.add(hero1);
        linkedList.add(hero3);
        linkedList.show();

        System.out.println();

        reverse(linkedList);
        linkedList.show();

        System.out.println();

        showReversed(linkedList);
        System.out.println(linkedList.size());

        System.out.println();

        var otherLinkedList = new SingleHeroLinkedList();
        var otherHero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        var otherHero4 = new HeroNode(4, "林冲", "豹子头");
        otherLinkedList.add(otherHero2);
        otherLinkedList.add(otherHero4);
        concatSorted(linkedList, otherLinkedList);
        linkedList.show();

        //var linkedList1 = new SingleHeroLinkedList();
        //linkedList1.addSorted(hero2);
        //linkedList1.addSorted(hero2);
        //linkedList1.addSorted(hero1);
        //linkedList1.addSorted(hero4);
        //linkedList1.addSorted(hero3);
        //linkedList1.show();
        //
        //System.out.println();
        //
        //var updatedHero2 = new HeroNode(2,"*卢俊义*","*玉麒麟*");
        //linkedList1.update(updatedHero2);
        //linkedList1.show();
        //
        //System.out.println();
        //
        //linkedList1.remove(1);
        //linkedList1.remove(4);
        //linkedList1.show();
        //
        //System.out.println();
        //
        //System.out.println(getSize(linkedList1));
        //
        //System.out.println(getLastNode(linkedList,1));
    }

    //NOTE 面试题部分

    /**
     * 获取到单链表的节点个数。
     */
    public static int getSize(SingleHeroLinkedList linkedList) {
        var head = linkedList.getHead();
        //如果头结点的下一个节点为null，则返回0
        if(head.next == null)
            return 0;

        //遍历并递增
        var size = 0;
        var temp = head.next;
        while(temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }

    /**
     * 查找单链表中的倒数第k个节点［新浪面试题］。
     * 注意：倒数第1个是最后一个元素。
     */
    public static HeroNode getLastNode(SingleHeroLinkedList linkedList, int index) {
        var head = linkedList.getHead();
        //如果链表为空，则返回null
        if(head.next == null)
            return null;

        //遍历链表，得到链表的总长度
        var size = getSize(linkedList);

        //如果索引不合法，则抛出异常
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        var temp = head.next;
        //size - index其实就是正向索引
        for(int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 反转链表［腾讯面试题］。
     */
    public static void reverse(SingleHeroLinkedList linkedList) {
        //思路：
        //定义一个新的节点reversedHead，并实例化
        //从头到尾遍历原来的链表，将其取出并放在新的链表的最前端
        //原链表的head.next = reversedHead.next

        var head = linkedList.getHead();

        //如果链表为空，则直接返回
        if(head.next == null)
            return;

        HeroNode reversedHead = new HeroNode();
        HeroNode current = head.next;
        HeroNode next; //指向当前节点（辅助节点）的下一个节点

        //遍历原来的链表，每遍历一个节点，就将其取出
        //并放在新的链表的头节点的最前端
        while(current != null) {
            //暂时保存当前节点的下一个节点
            next = current.next;

            //这两句其实就是是非常常见的链表插入逻辑

            //将当前节点的下一个节点指向新的链表的最前端
            current.next = reversedHead.next;
            //将当前节点连接到新的链表上
            reversedHead.next = current;

            //让当前节点后移
            current = next;
        }

        //将head.next指向reversedHead.next，实现链表反转
        head.next = reversedHead.next;
    }

    /**
     * 连接两个链表。
     */
    public static void concatSorted(SingleHeroLinkedList linkedList1, SingleHeroLinkedList linkedList2) {
        var head1 = linkedList1.getHead();
        var head2 = linkedList2.getHead();
        var tempLinkedList = new SingleHeroLinkedList();

        //直接创建一个新的线性表，然后遍历两个链表中的节点，调用addSorted方法加入到新的线性表中
        //然后关联第一个线性表的头结点下一个节点和新的线性表的头结点下一节点
        //注意需要暂存当前元素的下一个元素

        var current1 = head1.next;
        HeroNode next1;
        while(current1 != null) {
            next1 = current1.next;
            tempLinkedList.addSorted(current1);
            current1 = next1;
        }

        var current2 = head2.next;
        HeroNode next2;
        while(current2 != null) {
            next2 = current2.next;
            tempLinkedList.addSorted(current2);
            current2 = next2;
        }
        head1.next = tempLinkedList.getHead().next;
    }

    /**
     * 逆向打印单链表。
     */
    public static void showReversed(SingleHeroLinkedList linkedList) {
        //方式1：首先反转单链表，然后再遍历，不建议
        //方式2：使用栈，将各个节点压入栈中，然后利用栈的FILO特点实现
        //var stack = new Stack<HeroNode>();
        Deque<HeroNode> stack = new ArrayDeque<>();

        var head = linkedList.getHead();
        var current = head.next;
        while(current != null) {
            stack.push(current);
            current = current.next;
        }

        while(!stack.isEmpty()) {
            var value = stack.pop();
            System.out.println(value);
        }
    }
}
