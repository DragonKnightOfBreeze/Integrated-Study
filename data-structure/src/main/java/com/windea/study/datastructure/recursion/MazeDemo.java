package com.windea.study.datastructure.recursion;

//迷宫问题（递归回溯问题）

//讨论：如何求出最短路径（根策略有关）
//思路：暴力枚举所有策略

public class MazeDemo {
    public static void main(String[] args) {
        //使用一个二维数组模拟迷宫
        int[][] map = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 1, 1, 0, 1},
            {1, 1, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 1, 0, 1, 1, 1},
            {1, 0, 1, 1, 0, 1, 0, 1},
            {1, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1}
        };
        //打印原本的地图
        printMap(map);
        //使用递归回溯算法给小球找路
        setWay(map, 1, 1);
        //打印修改后的地图
        printMap(map);
    }

    private static void printMap(int[][] map) {
        System.out.println("************");
        for(var row : map) {
            for(var grid : row) {
                System.out.print(grid);
            }
            System.out.println();
        }
        System.out.println("************");
    }

    //使用递归回溯来给小球找路。
    //
    //说明：
    //1. map 表示地图
    //2. i,j 表示起始坐标
    //3. 如果小球能够走到map[6][5]的位置，说明通路找到
    //4. 0表示该点没有走过，1表示墙，2表示走过，3表示走过但走不通
    //5. 在走迷宫时，需要定一个策略，这里是下右上左
    //6. 如果该点走不通，再回溯
    private static boolean setWay(int[][] map, int i, int j) {
        if(map[6][6] == 2) {
            //说明通路已经找到
            //NOTE 这里是递归方法的出口
            return true;
        } else {
            if(map[i][j] == 0) {
                //如果当前点没有走过，则先按照策略走
                //假定该点可以走通
                //寻路策略：下右上左
                map[i][j] = 2;
                if(setWay(map, i + 1, j)) {
                    //向下走
                    return true;
                } else if(setWay(map, i, j + 1)) {
                    //向右走
                    return true;
                } else if(setWay(map, i - 1, j)) {
                    //向下走
                    return true;
                } else if(setWay(map, i, j - 1)) {
                    //向左走
                    return true;
                } else {
                    //说明该点走不通
                    map[i][j] = 3;
                }
            } else {
                //如果该点走不通或者已走过或者是死路
                return false;
            }
        }
        return false;
    }
}
