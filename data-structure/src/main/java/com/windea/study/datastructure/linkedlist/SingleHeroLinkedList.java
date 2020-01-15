package com.windea.study.datastructure.linkedlist;

/**
 * 单链表。
 */
public class SingleHeroLinkedList {
	//缺点：
	//查找方向只能是一个方向，而双向链表可以向前/向后查找。
	//不能自我删除，需要靠辅助节点，而双向链表可以实现自我删除

	//TODO 改进：保存尾节点

	private HeroNode head = new HeroNode();

	HeroNode getHead() {
		return head;
	}

	public boolean isEmpty() {
		return head.next == null;
	}

	public int size() {
		var size = 0;
		var temp = head.next;
		while(temp != null) {
			size++;
			temp = temp.next;
		}
		return size;
	}

	/**
	 * 添加数据到链表的最后。
	 */
	public void add(HeroNode heroNode) {
		//找到最后一个节点
		//因为头结点不能动，所以需要一个临时变量
		var temp = head;
		//遍历链表，找到最后的节点
		while(temp.next != null) {
			//如果没有找到，就将temp后移
			temp = temp.next;
		}
		//将最后一个节点的next指向新的节点
		temp.next = heroNode;
	}

	/**
	 * 根据编号顺序添加数据到链表的对应的位置。
	 */
	public void addSorted(HeroNode heroNode) {
		//因为头结点不能动，所以需要一个临时变量
		//寻找的temp是位于添加位置的前一个节点，否则无法插入
		var temp = head;
		var flag = false; //标志添加的编号是否存在
		while(true) {
			//如果temp已经在链表最后
			if(temp.next == null)
				break;
				//位置找到，在temp后面添加
			else if(temp.next.no > heroNode.no)
				break;
				//如果编号存在
			else if(temp.next.no == heroNode.no) {
				flag = true;
				break;
			}
			//继续向下寻找
			temp = temp.next;
		}

		//如果flag为true，则数据重复，不添加
		//否则加入数据
		if(flag) {
			System.out.println("要插入的英雄已存在。");
		} else {
			//将heroNode插入到temp后面
			heroNode.next = temp.next;
			temp.next = heroNode;
		}
	}

	/**
	 * 根据编号修改节点信息。
	 */
	public void update(HeroNode heroNode) {
		//判断是否为空
		if(checkEmpty())
			return;

		var temp = head;
		var flag = false; //表示是否找到该节点
		while(true) {
			if(temp == null)
				break;

			temp = temp.next;
			if(temp.no == heroNode.no) {
				flag = true;
				break;
			}
		}

		//根据flag判断是否找到了要修改的节点
		if(flag) {
			temp.name = heroNode.name;
			temp.nickname = heroNode.nickname;
		} else {
			System.out.println("没有需要修改的节点。");
		}
	}

	/**
	 * 根据编号删除节点。
	 */
	public void remove(int no) {
		//判断是否为空
		if(checkEmpty())
			return;

		//找到需要删除的这个节点的前一个节点
		var temp = head;
		var flag = false; //是否找到待删除节点的前一个节点
		while(true) {
			//注意这里是对temp.next进行null判断
			if(temp.next == null)
				break;

			if(temp.next.no == no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}

		if(flag) {
			//删除目标结点
			temp.next = temp.next.next;
		} else {
			System.out.println("没有需要删除的节点。");
		}

		//被删除的节点，因为没有其他引用指向，所以会被gc
	}

	/**
	 * 显示链表。
	 */
	public void show() {
		//判断是否为空
		if(checkEmpty())
			return;

		//使用一个临时变量遍历链表
		var temp = head;
		while(temp.next != null) {
			//后移temp并打印节点信息
			temp = temp.next;
			System.out.println(temp);
		}
	}

	private boolean checkEmpty() {
		if(head.next == null) {
			System.out.println("链表为空。");
			return true;
		}
		return false;
	}


	/**
	 * 反转链表。
	 */
	public void reverse() {
		//如果链表为空，则直接返回
		if(head.next == null)
			return;

		HeroNode reversedHead = new HeroNode(); //定义一个新的节点reversedHead，并实例化
		HeroNode current = head.next; //用于保存当前节点
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
}

//定义HeroNode，每个即是一个节点
class HeroNode {
	public int no;
	public String name;
	public String nickname;
	public HeroNode next; //指向下一个节点

	public HeroNode() {
		this.no = 0;
		this.name = "";
		this.nickname = "";
	}

	public HeroNode(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "HeroNode{" +
			"no=" + no +
			", name='" + name + '\'' +
			", nickname='" + nickname + '\'' +
			'}';
	}
}
