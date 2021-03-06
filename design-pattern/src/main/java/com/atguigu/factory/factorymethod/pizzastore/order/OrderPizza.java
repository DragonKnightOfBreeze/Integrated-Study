package com.atguigu.factory.factorymethod.pizzastore.order;

import com.atguigu.factory.factorymethod.pizzastore.pizza.Pizza;

import java.io.*;


public abstract class OrderPizza {

    // ������
    public OrderPizza() {
        Pizza pizza = null;
        String orderType; // ��������������
        do {
            orderType = getType();
            pizza = createPizza(orderType); //���󷽷����ɹ����������
            //���pizza ��������
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();

        } while(true);
    }

    //����һ�����󷽷���createPizza , �ø������������Լ�ʵ��
    abstract Pizza createPizza(String orderType);

    // дһ�����������Ի�ȡ�ͻ�ϣ����������������
    private String getType() {
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizza ����:");
            String str = strin.readLine();
            return str;
        } catch(IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}
