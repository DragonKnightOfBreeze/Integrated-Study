package com.windea.study.designpattern.strategy;

public class ToyDuck extends Duck {
	public ToyDuck() {
	}

	@Override
	public void display() {
		System.out.println("这是一只玩具鸭。");
	}
}
