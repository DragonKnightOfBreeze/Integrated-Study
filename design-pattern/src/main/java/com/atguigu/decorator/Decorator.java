package com.atguigu.decorator;

public class Decorator extends Drink {
    private Drink obj;

    public Decorator(Drink obj) { //���
        // TODO Auto-generated constructor stub
        this.obj = obj;
    }

    @Override
    public String getDes() {
        // TODO Auto-generated method stub
        // obj.getDes() �����װ���ߵ���Ϣ
        return des + " " + getPrice() + " && " + obj.getDes();
    }

    @Override
    public float cost() {
        // TODO Auto-generated method stub
        // getPrice �Լ��۸�
        return super.getPrice() + obj.cost();
    }


}
