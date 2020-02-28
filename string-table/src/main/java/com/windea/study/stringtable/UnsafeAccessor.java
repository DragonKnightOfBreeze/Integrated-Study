package com.windea.study.stringtable;

import sun.misc.Unsafe;

//命令行参数参考：java -Xbootclasspath/a:<path_to>/unsafeaccessor.jar -cp

public class UnsafeAccessor {
	public static Unsafe getUnsafe() {
		return Unsafe.getUnsafe();
	}
}
