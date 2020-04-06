package com.windea.study.designpattern.proxy.cglib;

public class Client {
    public static void main(String[] args) {
        TeacherDao target = new TeacherDao();
        TeacherDao proxy = (TeacherDao) new ProxyFactory(target).getProxyInstance();

        proxy.teach();

        System.out.println(proxy.getClass());
    }
}
