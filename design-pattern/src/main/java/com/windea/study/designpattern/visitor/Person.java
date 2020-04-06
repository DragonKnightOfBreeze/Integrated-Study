package com.windea.study.designpattern.visitor;

public abstract class Person {
    public abstract String getName();

    public abstract Action getAction();

    public abstract void accept(Action action);

    public abstract void display();
}
