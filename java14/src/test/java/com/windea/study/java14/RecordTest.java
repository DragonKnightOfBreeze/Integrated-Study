package com.windea.study.java14;

import org.junit.Test;

public class RecordTest {
    @Test
    public void test1() {
        var person = new Person("Windea", 3500);
        System.out.println(person.name());
        System.out.println(person.age());
        System.out.println(person);
        System.out.println(new Foo());
    }
}

record Person(
    String name,
    Integer age
) {}

record Foo() {}
