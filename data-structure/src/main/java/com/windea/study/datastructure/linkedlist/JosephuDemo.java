package com.windea.study.datastructure.linkedlist;

//约瑟夫问题
//设编号为1, 2, ...n的n个人围坐在一起，
//约定编号为k（1<=k<=n）的人从1开始报数，
//数到m的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列。
//依此类推，直到所有人出列为止，由此又产生一个出队编号的序列。

//提示
//用一个不带头节点的循环链表来处理约瑟夫问题，
//先构成一个有n节点的单循环链表，然后由k节点起从1开始计数，
//记到m时，对应节点从链表中删除，然后再从下一个节点又从1开始计数，
//直到最后一个节点从链表中删除，算法结束。

public class JosephuDemo {
	public static void main(String[] args) {
		var linkedList = new SingleCircleBoyLinkedList();
		linkedList.addBoys(125);
		linkedList.showBoy();
		linkedList.countBoy(10, 20, 125);
	}
}

class SingleCircleBoyLinkedList {
	private Boy first;

	public void addBoys(int nums) {
		if(nums < 1) {
			System.out.println("参数不正确。");
			return;
		}
		Boy temp = null; //辅助指针，构建环形链表
		for(int i = 1; i <= nums; i++) {
			//根据编号创建节点
			var boy = new Boy(i);
			//如果是第一个节点
			if(i == 1) {
				first = boy;
				first.next = first; // 自身构成环
			} else {
				temp.next = boy;
				boy.next = first;
			}
			temp = boy; //让temp指向下一个节点
		}
	}

	public void showBoy() {
		//判断链表是否为空
		if(first == null) {
			System.out.println("链表为空。");
			return;
		}

		Boy temp = first;
		while(true) {
			System.out.println("编号：" + temp.no);

			//这个不能写在条件之中
			if(temp.next == first)
				break;
			temp = temp.next;
		}
	}

	/**
	 * 根据用户的输入，计算出小孩出圈的顺序
	 * @param start 从第几个小孩开始数数
	 * @param count 数几下
	 * @param nums 最初有多少个小孩在圈中
	 */
	public void countBoy(int start, int count, int nums) {
		if(first == null || start < 1 || start > nums) {
			System.out.println("参数输入有误。");
			return;
		}

		//创建一个帮助节点指向最后一个节点
		var help = first;
		while(help.next != first) {
			help = help.next;
		}

		//报数之前，让first和help同时移动k-1次
		for(int i = 0; i < start - 1; i++) {
			first = first.next;
			help = help.next;
		}

		//循环直到圈中只有一个节点
		while(help != first) {
			//当小孩报数时，让first和help同时移动m-1次
			for(int j = 0; j < count - 1; j++) {
				first = first.next;
				help = help.next;
			}

			//移除first原本指向的节点（即为要出圈的消耗节点）
			System.out.println("小孩出圈：" + first.no);
			first = first.next;
			help.next = first;
		}
		System.out.println("最后留在圈中的小孩编号：" + help.no);
	}
}

class Boy {
	public int no;
	public Boy next;

	public Boy(int no) {
		this.no = no;
	}
}
