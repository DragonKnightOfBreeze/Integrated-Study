package com.windea.study.datastructure.sparsearray;

//稀疏数组 课后练习

//要求：
//在前面的基础上，将稀疏数组保存到磁盘，比如map.data
//恢复原来的数组时，读取map.data进行恢复

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class SparseArray1 {
	public static void main(String[] args) throws IOException {
		//创建一个原始的11*11二维数组
		//0表示没有棋子，1表示黑子，2表示白子
		int[][] chessArray = new int[11][11];
		final int BLACK = 1;
		final int WHITE = 2;

		chessArray[1][2] = BLACK;
		chessArray[2][3] = WHITE;

		//输出原始的二维数组
		System.out.println("原始的二维数组：");
		for(var row : chessArray) {
			for(var data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}

		//将二维数组转化为稀疏数组
		//遍历二维数组，得到非0数据的个数
		int totalRow = chessArray.length;
		int totalCol = chessArray[0].length;
		int totalCount = 0;
		for(int i = 0; i < totalRow; i++) {
			for(var j = 0; j < totalCol; j++) {
				if(chessArray[i][j] != 0) {
					totalCount++;
				}
			}
		}

		//创建对应的稀疏数组
		int[][] sparseArray = new int[totalCount + 1][3];
		//为稀疏数组的第一行元素赋值
		sparseArray[0][0] = totalRow;
		sparseArray[0][1] = totalCol;
		sparseArray[0][2] = totalCount;

		//遍历二维数组，将元素及其位置信息存入稀疏数组
		//count用于记录是第几个非0数据
		int count = 1;
		for(int i = 0; i < totalRow; i++) {
			for(int j = 0; j < totalCol; j++) {
				if(chessArray[i][j] != 0) {
					sparseArray[count][0] = i;
					sparseArray[count][1] = j;
					sparseArray[count][2] = chessArray[i][j];
					count++;
				}
			}
		}

		//输出稀疏数组
		System.out.println("创建的稀疏数组：");
		for(var row : sparseArray) {
			for(var data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}

		//将稀疏数组转化为以逗号分隔的多行字符串
		var sparseArrayString = Arrays.stream(sparseArray)
			.map(it -> Arrays.stream(it).mapToObj(s -> String.valueOf(s)).reduce((a, b) -> a + " " + b).orElseThrow())
			.reduce((a, b) -> a + "\n" + b).orElseThrow();

		//存入数据
		var path = Path.of("data-structure", "map.data");
		Files.writeString(path, sparseArrayString);

		//================================================

		//读取数据
		var sparseArrayStringLines = Files.readAllLines(path);
		var sparseArray1 = sparseArrayStringLines.stream()
			.map(it -> Arrays.stream(it.split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray()).toArray(int[][]::new);

		//将稀疏数组恢复成原始的二维数组
		int totalRow1 = sparseArray1[0][0];
		int totalCol1 = sparseArray1[0][1];
		int[][] chessArray1 = new int[totalRow1][totalCol1];


		//遍历稀疏数组（从第二行开始），将数据加入原始数组
		for(int i = 1; i < sparseArray.length; i++) {
			int row = sparseArray[i][0];
			int col = sparseArray[i][1];
			int data = sparseArray[i][2];
			chessArray1[row][col] = data;
		}

		//输出从稀疏数组读取的的二维数组
		System.out.println("原始的二维数组：");
		for(var row : chessArray1) {
			for(var data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}
	}
}
