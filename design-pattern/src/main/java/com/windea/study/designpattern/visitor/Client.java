package com.windea.study.designpattern.visitor;

public class Client {
	public static void main(String[] args) {
		var objectStructure = new ObjectStructure();

		objectStructure.attach(new Man(), new Success());
		objectStructure.attach(new Woman(), new Success());

		objectStructure.display();
	}
}
