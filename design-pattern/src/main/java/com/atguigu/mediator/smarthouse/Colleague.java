package com.atguigu.mediator.smarthouse;

//ͬ�³�����
public abstract class Colleague {
    public String name;
    private Mediator mediator;

    public Colleague(Mediator mediator, String name) {

        this.mediator = mediator;
        this.name = name;

    }

    public Mediator GetMediator() {
        return this.mediator;
    }

    public abstract void SendMessage(int stateChange);
}
