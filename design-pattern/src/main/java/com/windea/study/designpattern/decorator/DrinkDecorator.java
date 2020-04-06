package com.windea.study.designpattern.decorator;

public class DrinkDecorator extends Drink {
    private Drink drink;

    public DrinkDecorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " " + drink.getDescription();
    }

    @Override
    public float cost() {
        return super.getPrice() + drink.cost();
    }
}
