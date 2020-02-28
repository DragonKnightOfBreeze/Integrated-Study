package com.windea.study.stringtable;

import java.lang.invoke.*;

@SuppressWarnings("ALL")
public class StringConcatDemo1 {
	public static void main(String[] args) throws Throwable {
		String x = "hello";
		String y = "world";

		//MethodHandle可用来放射调用一个已知信息的方法
		MethodHandle methodHandle = MethodHandles.lookup().findStatic(StringConcatDemo1.class, "concat",
			MethodType.methodType(String.class, String.class, String.class));
		String s = (String) methodHandle.invoke(x, y);

		System.out.println(s);
	}

	public static String concat(String a, String b) {
		return new StringBuilder().append(a).append(b).toString();
	}
}
