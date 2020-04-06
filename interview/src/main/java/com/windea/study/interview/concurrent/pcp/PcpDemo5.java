package com.windea.study.interview.concurrent.pcp;

//使用管道解决生产者/消费者问题。

//一种特殊的流，用于不同线程间直接传送数据，一个线程发送数据到输出管道，另一个线程从输入管道中读数据。
//inputStream.connect(outputStream)或outputStream.connect(inputStream)的作用
//是使两个Stream之间产生通信链接，这样才可以将数据进行输出与输入。
//这种方式只适用于两个线程之间通信，不适合多个线程之间通信。

import java.io.*;

public class PcpDemo5 {
    public static void main(String[] args) {
        Producer p = new Producer();
        Consumer c = new Consumer();
        Thread t1 = new Thread(p);
        Thread t2 = new Thread(c);
        try {
            p.getPipedOutputStream().connect(c.getPipedInputStream());
            t1.start();
            t2.start();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static class Consumer implements Runnable {
        private PipedInputStream pipedInputStream = new PipedInputStream();

        public PipedInputStream getPipedInputStream() {
            return pipedInputStream;
        }

        @Override
        public void run() {
            int len;
            byte[] buffer = new byte[1024];
            try {
                while((len = pipedInputStream.read(buffer)) != -1) {
                    System.out.println(new String(buffer, 0, len));
                }
                pipedInputStream.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Producer implements Runnable {
        private PipedOutputStream pipedOutputStream;

        public Producer() {
            pipedOutputStream = new PipedOutputStream();
        }

        public PipedOutputStream getPipedOutputStream() {
            return pipedOutputStream;
        }

        @Override
        public void run() {
            try {
                for(int i = 1; i <= 5; i++) {
                    pipedOutputStream.write(("This is a test, Id=" + i + "!\n").getBytes());
                }
                pipedOutputStream.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

}

