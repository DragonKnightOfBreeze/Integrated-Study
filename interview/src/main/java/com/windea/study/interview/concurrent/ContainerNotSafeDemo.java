package com.windea.study.interview.concurrent;

import java.util.concurrent.CopyOnWriteArrayList;

public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        listTest();
    }

    public static void listTest() {
        //var list = new ArrayList<Integer>();
        //var list =  Collections.synchronizedList(new ArrayList<Integer>());
        var list = new CopyOnWriteArrayList<Integer>();

        for(int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(1);
                System.out.println(list); //可能会输出null
            }, String.valueOf(i)).start();
        }
    }
}
