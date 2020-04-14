package com.windea.study.interview.concurrent;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        var phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendSms();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}

class Phone {
    public synchronized void sendSms() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t send SMS.");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t send Email.");
    }
}
