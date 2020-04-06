package com.windea.study.designpattern.factorymethod;

import org.junit.Test;

public class FruitFactoryTest {
    @Test
    public void test() {
        var apple = new AppleFactory().getFruit();
        apple.eat();

        var orange = new OrangeFactory().getFruit();
        orange.eat();
    }
}
