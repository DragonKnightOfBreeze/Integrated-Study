package com.windea.study.designpattern.factorymethod;

class OrangeFactory extends FruitFactory {
    @Override
    Fruit getFruit() {
        return new Orange();
    }
}
