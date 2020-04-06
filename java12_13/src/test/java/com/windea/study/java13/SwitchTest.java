package com.windea.study.java13;

import org.junit.Test;

public class SwitchTest {
    @Test
    public void test1() {
        var direction = Direction.LEFT;
        var str = switch(direction) {
            case LEFT -> {
                System.out.println("Direction:");
                yield "left";
            }
            case RIGHT -> {
                System.out.println("Direction:");
                yield "right";
            }
            case UP -> "up";
            case DOWN -> "down";
        };
        System.out.println(str);
    }

    @Test
    public void test2() {
        var num = 1;
        var str = switch(num) {
            case 1:
                yield "1";
            case 2:
                yield "2";
            default:
                yield "n";
        };
        System.out.println(str);
    }

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }
}
