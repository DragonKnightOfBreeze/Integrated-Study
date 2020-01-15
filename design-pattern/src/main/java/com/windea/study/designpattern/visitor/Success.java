package com.windea.study.designpattern.visitor;

public class Success extends Action {
	@Override
	public void getManResult(Man man) {
		System.out.println("男评委" + man.getName() + "的评测结果：成功！");
	}

	@Override
	public void getWomanResult(Woman woman) {
		System.out.println("女评委" + woman.getName() + "的评测结果：成功！");
	}
}