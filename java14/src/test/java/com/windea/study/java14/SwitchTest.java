package com.windea.study.java14;

import org.junit.Test;

public class SwitchTest {
    @Test
    public void test1() {
        var str = "up";
        var result = switch(str) {
            case "up" -> "上";
            case "down" -> "下";
            case "left" -> "左";
            case "right" -> "右";
            case "center" -> {
                System.out.println("中心。");
                yield "中";
            }
            default -> throw new IllegalArgumentException();
        };
        System.out.println(result);
    }
}
