package com.windea.study.hashmap;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

public class HashMapTest {
    @Test
    public void test1() {
        Map<String, Integer> map = Map.of("a", 1, "b", 2, "c", 3);

        //遍历entrySet
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        //分别遍历keySet和values
        for(String key : map.keySet()) {
            System.out.println(key);
        }
        for(Integer value : map.values()) {
            System.out.println(value);
        }

        //使用迭代器迭代
        for(Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        //通过get方式迭代（不建议使用）
        for(String key : map.keySet()) {
            System.out.println(key + "=" + map.get(key));
        }

        //使用Java8的forEach迭代
        map.forEach((k, v) ->
            System.out.println(k + "=" + v)
        );
    }
}
