package com.windea.study.designpattern.decorator;

public class Coffee extends Drink {
    @Override
    public float cost() {
        return this.getPrice();
    }
}
