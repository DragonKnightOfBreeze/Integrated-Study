package com.windea.study.stringtable;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示StringTable位置
 * 在jdk6下设置 -XX:MaxPermSize=10m
 * 在jdk8下设置 -Xmm10m -XX:UseGCOverheadLimit
 */
public class InternDemo {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		int i = 0;
		try {
			for(int j = 0; j < 26000; j++) {
				list.add(String.valueOf(j).intern());
				i++;
			}
		} catch(Throwable e) {
			e.printStackTrace();
		} finally {
			System.out.println(i);
		}
	}
}
