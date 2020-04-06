package com.windea.study.designpattern.abstractfactory;

import java.util.Objects;

class AppleFactory extends FruitFactory {
    @Override
    public Fruit getFruit(String size) {
        if(Objects.equals(size, "small")) {
            return new SmallApple();
        } else {
            return new Apple();
        }
    }
}
