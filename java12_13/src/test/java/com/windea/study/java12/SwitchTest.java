package com.windea.study.java12;


import org.junit.Test;

public class SwitchTest {
	public enum Direction{
		LEFT,RIGHT,UP,DOWN
	}

	@Test
	public void test1() {
		var direction = Direction.LEFT;
		switch(direction) {
			case LEFT:
				System.out.println("left");
				break;
			case RIGHT:
				System.out.println("right");
				break;
			case UP:
				System.out.println("up");
			case DOWN:
				System.out.println("down");
		}
	}

	@Test
	public void test2(){
		var direction = Direction.LEFT;
		switch(direction) {
			case LEFT -> System.out.println("left");
			case RIGHT -> System.out.println("right");
			case UP -> System.out.println("up");
			case DOWN -> System.out.println("down");
		}
	}

	@Test
	public void test3(){
		var direction = Direction.LEFT;
		var str = switch(direction) {
			case LEFT -> "left";
			case RIGHT -> "right";
			case UP -> "up";
			case DOWN -> "down";
		};
		System.out.println(str);
	}
}
