package com.windea.study.designpattern.visitor;

public class Man extends Person {
    private String name;
    private Action action;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Action getAction() {
        return action;
    }

    @Override
    public void accept(Action action) {
        this.action = action;
    }

    @Override
    public void display() {
        action.getManResult(this);
    }
}
