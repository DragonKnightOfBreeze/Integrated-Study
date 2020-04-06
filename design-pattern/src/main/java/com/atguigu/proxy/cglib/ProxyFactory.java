package com.atguigu.proxy.cglib;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

public class ProxyFactory implements MethodInterceptor {

    //ά��һ��Ŀ�����
    private Object target;

    //������������һ��������Ķ���
    public ProxyFactory(Object target) {
        this.target = target;
    }

    //����һ���������:  �� target ����Ĵ������
    public Object getProxyInstance() {
        //1. ����һ��������
        Enhancer enhancer = new Enhancer();
        //2. ���ø���
        enhancer.setSuperclass(target.getClass());
        //3. ���ûص�����
        enhancer.setCallback(this);
        //4. ����������󣬼��������
        return enhancer.create();

    }


    //��д  intercept �����������Ŀ�����ķ���
    @Override
    public Object intercept(Object arg0, Method method, Object[] args, MethodProxy arg3) throws Throwable {
        // TODO Auto-generated method stub
        System.out.println("Cglib����ģʽ ~~ ��ʼ");
        Object returnVal = method.invoke(target, args);
        System.out.println("Cglib����ģʽ ~~ �ύ");
        return returnVal;
    }

}
