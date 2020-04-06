package com.windea.study.designpattern.proxy.jdk;

public class Client {
    public static void main(String[] args) {
        TeacherDao target = new TeacherDao();
        ITeacherDao proxy = ((ITeacherDao) new ProxyFactory(target).getProxyInstance());

        proxy.teach();

        System.out.println(proxy.getClass());
    }
}
