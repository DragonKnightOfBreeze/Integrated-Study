package com.windea.study.datastructure.hashtable;

public class HashTableDemo {
	public static void main(String[] args) {
		var hashTable = new HashTable<Integer, Employee>(10);

		var employee1 = new Employee(1, "张三");
		var employee2 = new Employee(2, "李四");
		var employee3 = new Employee(3, "王五");

		hashTable.put(1, employee1);
		hashTable.put(2, employee2);
		hashTable.put(3, employee3);

		System.out.println(hashTable.get(2));
		hashTable.remove(2);
		System.out.println(hashTable.get(2));
	}
}
