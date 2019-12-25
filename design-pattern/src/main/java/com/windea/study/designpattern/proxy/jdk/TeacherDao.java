package com.windea.study.designpattern.proxy.jdk;

public class TeacherDao implements ITeacherDao {
	@Override
	public void teach() {
		System.out.println("teach.");
	}
}
