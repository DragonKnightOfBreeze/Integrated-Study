package com.windea.study.datastructure.recursion;

//白皇后问题
//在8*8的国际象棋上摆放八个皇后，使其不能互相攻击
//即：任意两个皇后都不能处于同一行、同一列或同一斜线上
//问有多少种摆法

//思路分析
//1. 第一个皇后先放在(1,1)
//2. 第二个皇后放在(2,1)，然后判断是否OK，如果不行，则依次向下一列尝试
//3. 继续放第三个皇后，依次向下一列尝试，直到第八个皇后也能找到一个不冲突的位置，此时找到了一个正确解
//4. 当得到一个正确解时，，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放在第一列的所有正确解，全部得到
//5. 然后回头继续第一个皇后放第二列，后面依次循环123的步骤

//说明
//理论上应该创建一个二维数组表示棋盘，但是实际上可以通过算法，用一个一维数组即可解决问题。

//答案：92种

public class EightQueensProblemDemo {
	//定义共有多少个皇后
	private static final int total = 8;
	//用索引表示行数
	private static int[] map = new int[8];
	//表示摆法的个数
	private static int methodCount = 0;

	public static void main(String[] args) {
		check(0);
		System.out.println(methodCount);
	}

	private static void check(int n) {
		if(n == total) {
			//如果已经全部摆下
			//NOTE 这里是这个递归方法的出口
			printMap();
			methodCount++;
			return;
		}
		//依次放入皇后，并判断是否冲突
		//这是每一次递归时的循环，包含了第一个皇后摆放位置的循环和后续检测冲突的所有循环
		for(int i = 0; i < total; i++) {
			//尝试放置这个皇后在第i列
			map[n] = i;
			//判断是否冲突
			if(checkNoConflict(n)) {
				//如果不冲突，接着放置皇后，开始递归
				check(n + 1);
			}
			//如果冲突，则将这个皇后放置在下一列
		}
	}

	/**
	 * 查看当我们放置第n个皇后后，就去检测该皇后是否已经和前面已摆放的皇后冲突（检测所有）
	 */
	private static boolean checkNoConflict(int n) {
		for(int i = 0; i < n; i++) {
			//如果在同一列或者在对角线上
			//不需要判断是否在同一行
			if(map[i] == map[n] || Math.abs(n - i) == Math.abs(map[n] - map[i])) {
				return false;
			}
		}
		return true;
	}

	private static void printMap() {
		System.out.println("************");
		for(int grid : map) {
			for(int i = 0; i < 8; i++) {
				if(i == grid) {
					System.out.print(1);
				} else {
					System.out.print(0);
				}
			}
			System.out.println();
		}
		System.out.println("************");
	}
}
