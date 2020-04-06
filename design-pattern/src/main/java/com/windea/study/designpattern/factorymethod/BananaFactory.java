package com.windea.study.designpattern.factorymethod;

class BananaFactory extends FruitFactory {
    @Override
    Fruit getFruit() {
        return new Banana();
    }
}
