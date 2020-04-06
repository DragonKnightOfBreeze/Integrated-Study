package com.windea.study.designpattern.visitor;

import java.util.LinkedList;
import java.util.List;

public class ObjectStructure {
    private List<Person> persons = new LinkedList<>();

    public void attach(Person person, Action action) {
        person.accept(action);
        persons.add(person);
    }

    public void detach(Person person) {
        persons.remove(person);
    }

    public void display() {
        for(var person : persons) {
            person.display();
        }
    }
}
